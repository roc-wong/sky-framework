package org.roc.curator.leader;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author roc
 * @date 2018/08/21
 */
public class PathChildrenCacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathChildrenCacheTest.class);

    private static final String ADDR = "10.0.29.29:2181";
    private static final String PATH = "/leaderLatch1";

    private static final ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("ZK-%d").build();

    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(3, 10,
            30, TimeUnit.MINUTES,
            new LinkedBlockingDeque<Runnable>(1024),
            NAMED_THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws Exception {


        CuratorFramework client = getClient();
        client.start();

        final String clientId = "client#" + "_" + InetAddress.getLocalHost().getHostAddress();
        final LeaderLatch latch = new LeaderLatch(client, PATH, clientId);
        latch.addListener(new LeaderLatchListener() {

            @Override
            public void isLeader() {

                ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(PATH);
                LOGGER.info("node={}, path={}", pathAndNode.getNode(), pathAndNode.getPath());
                String nodeFromPath = ZKPaths.getNodeFromPath(PATH);
                LOGGER.info("I am Leader, id={}, nodeFromPath={}", latch.getId(), nodeFromPath);
            }

            @Override
            public void notLeader() {
                String nodeFromPath = ZKPaths.getNodeFromPath(PATH);
                LOGGER.info("I am not a Leader, id={}, nodeFromPath={}", latch.getId(), nodeFromPath);
            }

        });

        latch.start();

        registerWatcher(client, clientId);

        Thread.sleep(Integer.MAX_VALUE);
        client.close();
    }


    /**
     * 注册监听器，当前节点不存在，创建该节点：未抛出异常及错误日志
     * 注册子节点触发type=[CHILD_ADDED]
     * 更新触发type=[CHILD_UPDATED]
     * <p>
     * zk挂掉type=CONNECTION_SUSPENDED,，一段时间后type=CONNECTION_LOST
     * 重启zk：type=CONNECTION_RECONNECTED, data=null
     * 更新子节点：type=CHILD_UPDATED, data=ChildData{path='/zktest111/tt1', stat=4294979983,4294979993,1501037475236,1501037733805,2,0,0,0,6,0,4294979983
     * , data=[55, 55, 55, 55, 55, 55]}
     * ​
     * 删除子节点type=CHILD_REMOVED
     * 更新根节点：不触发
     * 删除根节点：不触发  无异常
     * 创建根节点：不触发
     * 再创建及更新子节点不触发
     * <p>
     * 重启时，与zk连接失败
     *
     * @param client
     * @param clientId
     */
    private static void registerWatcher(CuratorFramework client, final String clientId) throws Exception {

        final PathChildrenCache watcher = new PathChildrenCache(client, PATH, true, false, EXECUTOR_SERVICE);
        watcher.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                LOGGER.info("PathChildrenCacheEvent={}", pathChildrenCacheEvent);

                ChildData childData = pathChildrenCacheEvent.getData();
                if (childData != null) {
                    String childDataPath = childData.getPath();
                    String data = new String(childData.getData());
                    LOGGER.info("childDataPath={}, data={}", childDataPath, data);
                }
                for (int i = 0, size = watcher.getCurrentData().size(); i < size; i++) {
                    ChildData childData1 = watcher.getCurrentData().get(i);
                    String data = new String(childData1.getData());
                    LOGGER.info("currentClientId={}, clientId=,{}, num={},", clientId, data, i);
                }

               /* PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                switch (type) {
                    case CHILD_ADDED:
                        LOGGER.info("-----> CHILD_ADDED");
                        break;
                    case CHILD_UPDATED:
                        LOGGER.info("-----> CHILD_UPDATED");
                        break;
                    case CHILD_REMOVED:
                        LOGGER.info("-----> CHILD_REMOVED");
                        break;
                    case CONNECTION_SUSPENDED:
                        LOGGER.info("-----> CONNECTION_SUSPENDED");
                        break;
                    case CONNECTION_RECONNECTED:
                        LOGGER.info("-----> CONNECTION_RECONNECTED");
                        break;
                    case CONNECTION_LOST:
                        LOGGER.info("-----> CONNECTION_LOST");
                        break;
                    case INITIALIZED:
                        LOGGER.info("-----> INITIALIZED");
                        break;
                    default:
                        break;
                }*/
            }
        });

        /**PathChildrenCache.StartMode说明如下
         *POST_INITIALIZED_EVENT
         *1、在监听器启动的时候即，会枚举当前路径所有子节点，触发CHILD_ADDED类型的事件
         * 2、同时会监听一个INITIALIZED类型事件
         * NORMAL异步初始化cache
         * POST_INITIALIZED_EVENT异步初始化，初始化完成触发事件PathChildrenCacheEvent.Type.INITIALIZED
         /*NORMAL只和POST_INITIALIZED_EVENT的1情况一样，不会ALIZED类型事件触发

         /*BUILD_INITIAL_CACHE 不会触发上面两者事件,同步初始化客户端的cache，及创建cache后，就从服务器端拉入对应的数据         */
        watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        LOGGER.info("注册watcher成功...");
    }

    private static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.0.29.29:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("roc")
                .build();
        return client;
    }

}
