package org.roc.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collection;

/**
 * @author roc
 * @date 2018/08/10
 */
public class CuratorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorTest.class);

    private CuratorFramework client;

    @BeforeTest
    public void init() {
        client = getClient();
        client.start();
    }

    private CuratorFramework getClient() {
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
/*

    @DataProvider(name = "client")
    public Object[][] createClient() {
        CuratorFramework client = getClient();
        return new Object[][]{
                new Object[]{client},
        };
    }
*/


    @Test
    public void testCreate() throws Exception {

        //创建永久节点
        client.create().creatingParentContainersIfNeeded().forPath("/create/persistent", "I love you, joy".getBytes());

        //创建永久有序节点
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/create/persistent_sequential", "/curator_sequential data".getBytes());

        //创建临时节点
        client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/create/ephemeral/node_");

        //创建临时有序节点
        client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/create/ephemeral_sequential/node_");
        Thread.sleep(10000);
    }


    @Test
    public void testCheck() throws Exception {
        Stat stat = client.checkExists().forPath("/create/persistent");
        Assert.assertTrue(stat != null);
    }

    /**
     * 测试获取和设置节点数据
     *
     * @throws Exception
     */
    @Test
    public void testGetAndSet() throws Exception {
        //获取某个节点的所有子节点
        LOGGER.info("children = {}", client.getChildren().forPath("/"));

        Stat stat = client.checkExists().forPath("/create/persistent");
        if (stat != null) {
            client.delete().forPath("/create/persistent");
        }

        client.create().creatingParentContainersIfNeeded().forPath("/create/persistent");

        //设置某个节点数据
        client.setData().forPath("/create/persistent", "I love you 1314, joy.".getBytes());

        //获取某个节点数据
        LOGGER.info(new String(client.getData().forPath("/create/persistent")));
    }


    /**
     * 测试异步设置节点数据
     *
     * @throws Exception
     */
    @Test
    public void testSetDataAsync() throws Exception {
        //创建监听器
        CuratorListener listener = new CuratorListener() {

            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event)
                    throws Exception {
                System.out.println(event.getPath());
            }
        };

        //添加监听器
        client.getCuratorListenable().addListener(listener);

        //异步设置某个节点数据
        client.setData().inBackground().forPath("/curator", "/curator modified data with Async".getBytes());

        //为了防止单元测试结束从而看不到异步执行结果，因此暂停10秒
        Thread.sleep(10000);
    }

    /**
     * 测试另一种异步执行获取通知的方式
     *
     * @throws Exception
     */
    @Test
    public void testSetDataAsyncWithCallback() throws Exception {
        BackgroundCallback callback = new BackgroundCallback() {

            @Override
            public void processResult(CuratorFramework client, CuratorEvent event)
                    throws Exception {
                System.out.println(event.getPath());
            }
        };

        //异步设置某个节点数据
        client.setData().inBackground(callback).forPath("/curator", "/curator modified data with Callback".getBytes());

        //为了防止单元测试结束从而看不到异步执行结果，因此暂停10秒
        Thread.sleep(10000);
    }


    /**
     * 测试删除节点
     *
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        //创建测试节点
        client.create().creatingParentContainersIfNeeded()
                .forPath("/curator/del_key1", "/curator/del_key1 data".getBytes());

        client.create().creatingParentContainersIfNeeded()
                .forPath("/curator/del_key2", "/curator/del_key2 data".getBytes());

        client.create().forPath("/curator/del_key2/test_key", "test_key data".getBytes());


        //删除该节点
        client.delete().forPath("/curator/del_key1");

        //级联删除子节点
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator/del_key2");
    }


    /**
     * 测试事务管理：碰到异常，事务会回滚
     *
     * @throws Exception
     */
    @Test
    public void testTransaction() throws Exception {
        CuratorTransaction curatorTransaction = client.inTransaction();
        Collection<CuratorTransactionResult> commit = curatorTransaction.create().forPath("/transaction/one_path", "some data".getBytes())
                .and().setData().forPath("/transaction/two_path", "other data".getBytes())
                .and().check().forPath("/transaction/two_path")
                .and().commit();


        //遍历输出结果
        for (CuratorTransactionResult result : commit) {
            System.out.println("执行结果是： " + result.getForPath() + "--" + result.getType());
        }
    }


    @Test
    public void testPathChildrenCache() throws Exception {
        String PATH = "/example/pathCache";
        PathChildrenCache cache = new PathChildrenCache(client, PATH, true);
        cache.start();
        PathChildrenCacheListener cacheListener = new PathChildrenCacheListener() {

            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("事件类型：" + event.getType());
                if (null != event.getData()) {
                    System.out.println("节点数据：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
                }
            }
        };
        cache.getListenable().addListener(cacheListener);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test01", "01".getBytes());
        Thread.sleep(10);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test02", "02".getBytes());
        Thread.sleep(10);
        client.setData().forPath("/example/pathCache/test01", "01_V2".getBytes());
        Thread.sleep(10);
        for (ChildData data : cache.getCurrentData()) {
            System.out.println("getCurrentData:" + data.getPath() + " = " + new String(data.getData()));
        }
        client.delete().forPath("/example/pathCache/test01");
        Thread.sleep(10);
        client.delete().forPath("/example/pathCache/test02");
        Thread.sleep(1000 * 5);
        cache.close();
        client.close();
        System.out.println("OK!");
    }


    @Test
    public void testNodeCache() throws Exception {
        String PATH = "/example/pathCache";
        client.create().creatingParentsIfNeeded().forPath(PATH);
        final NodeCache cache = new NodeCache(client, PATH);
        NodeCacheListener listener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData data = cache.getCurrentData();
                if (null != data) {
                    System.out.println("节点数据：" + new String(cache.getCurrentData().getData()));
                } else {
                    System.out.println("节点被删除!");
                }
            }
        };
        cache.getListenable().addListener(listener);
        cache.start();
        client.setData().forPath(PATH, "01".getBytes());
        Thread.sleep(5000);
        client.setData().forPath(PATH, "4".getBytes());
        Thread.sleep(5000);
        client.delete().deletingChildrenIfNeeded().forPath("/example/pathCache/test04");
        Thread.sleep(5000 * 2);
        cache.close();
        client.close();
        System.out.println("OK!");

    }


    @Test
    public void testTreeCache() throws Exception {
        String PATH = "/example/pathCache";
//        client.create().creatingParentsIfNeeded().forPath(PATH);
        TreeCache cache = new TreeCache(client, PATH);
        TreeCacheListener listener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("事件类型：" + event.getType() +
                        " | 路径：" + (null != event.getData() ? event.getData().getPath() : null));
            }
        };

        cache.getListenable().addListener(listener);
        cache.start();
        client.setData().forPath(PATH, "01".getBytes());

        Thread.sleep(4000);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test04", "04".getBytes());
        client.setData().forPath(PATH, "02".getBytes());
        Thread.sleep(4000);
        client.delete().deletingChildrenIfNeeded().forPath("/example/pathCache/test04");
        Thread.sleep(2000 * 2);
        cache.close();
        client.close();
        System.out.println("OK!");

    }
}
