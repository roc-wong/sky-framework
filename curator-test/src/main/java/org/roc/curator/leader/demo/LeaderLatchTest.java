package org.roc.curator.leader.demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzs on 2017/4/17.
 */
public class LeaderLatchTest {

    private static final String PATH = "/leaderLatch";

    public static void main(String[] args) {

        List<LeaderLatch> latchList = new ArrayList<>();
        List<CuratorFramework> clients = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                CuratorFramework client = getClient();
                clients.add(client);

                final LeaderLatch leaderLatch = new LeaderLatch(client, PATH, "client#" + i);

                leaderLatch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.out.println(leaderLatch.getId() + ":I am leader. I am doing jobs!");
                    }

                    @Override
                    public void notLeader() {
                        System.out.println(leaderLatch.getId() + ":I am not leader. I will do nothing!");
                    }
                });
                latchList.add(leaderLatch);
                leaderLatch.start();
            }
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }

            for (LeaderLatch leaderLatch : latchList) {
                CloseableUtils.closeQuietly(leaderLatch);
            }
        }
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
        client.start();
        return client;
    }


    ConnectionStateListener clientListener = new ConnectionStateListener() {

        @Override
        public void stateChanged(CuratorFramework client,
                                 ConnectionState newState) {
            if (newState == ConnectionState.CONNECTED) {
                System.out.println("connected established");

            } else if (newState == ConnectionState.LOST) {
                System.out.println("connection lost,waiting for reconection");
                try {
                    System.out.println("reinit---");
                    System.out.println("inited---");
                } catch (Exception e) {
                    System.err.println("re-inited failed");
                }
            }

        }
    };


}