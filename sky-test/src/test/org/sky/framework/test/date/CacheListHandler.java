package org.sky.framework.test.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danmy
 * @description：缓存操作类，对缓存进行管理，采用处理队列，定时循环清理的方式
 */
public class CacheListHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheListHandler.class);
    private static final long SECOND_TIME = 1000;
    private static final SimpleList<CacheEntity> list;
    private static final List<CacheEntity> tempList;

    static {
        tempList = new ArrayList<>();//临时副备，减少频繁check真体开销
        list = new SimpleList<>(new ArrayList<CacheEntity>(1 << 18));
        new Thread(new TimeoutTimerThread()).start();
    }

    /**
     * 增加缓存对象
     *
     * @param key
     * @param ce
     */
    public static void addCache(CacheEntity ce) {
        addCache(ce, ce.getValidityTime());
    }

    /**
     * 增加缓存对象
     *
     * @param key
     * @param ce
     * @param validityTime 有效时间
     */
    public static synchronized void addCache(CacheEntity ce, int validityTime) {
        ce.setTimeoutStamp(System.currentTimeMillis() + validityTime * SECOND_TIME);
        list.add(ce);
        //添加到过期处理队列
        tempList.add(ce);
    }

    /**
     * 获取缓存对象
     *
     * @param key
     * @return
     */
    public static synchronized CacheEntity getCache(CacheEntity ce) {
        return list.get(list.indexOf(ce));
    }

    /**
     * 检查是否含有制定key的缓冲
     *
     * @param key
     * @return
     */
    public static synchronized boolean isConcurrent(CacheEntity ce) {
        return list.contains(ce);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static synchronized void removeCache(CacheEntity ce) {
        list.remove(ce);
    }

    /**
     * 获取缓存大小
     *
     * @param key
     */
    public static int getCacheSize() {
        return list.size();
    }

    /**
     * 清除全部缓存
     */
    public static synchronized void clearCache() {
        tempList.clear();
        list.clear();
        LOGGER.info("clear cache");
    }

    static class TimeoutTimerThread implements Runnable {
        public void run() {
            while (true) {
                try {
                    checkTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 过期缓存的具体处理方法
         *
         * @throws Exception
         */
        private void checkTime() throws Exception {
            //"开始处理过期 ";
            CacheEntity tce = null;
            long timoutTime = 1000L;

            //" 过期队列大小 : "+tempList.size());
            if (1 > tempList.size()) {
                LOGGER.info("过期队列空，开始轮询");
                timoutTime = 1000L;
                Thread.sleep(timoutTime);
                return;
            }

            tce = tempList.get(0);
            timoutTime = tce.getTimeoutStamp() - System.currentTimeMillis();
            //" 过期时间 : "+timoutTime);
            if (0 < timoutTime) {
                //设定过期时间
                Thread.sleep(timoutTime);
                return;
            }
            LOGGER.info(" 清除过期缓存 ： " + tce.getCacheKey());
            //清除过期缓存和删除对应的缓存队列
            tempList.remove(tce);
            removeCache(tce);
        }
    }

    public static SimpleList<CacheEntity> getList() {
        return list;
    }

    public static List<CacheEntity> getTempList() {
        return tempList;
    }
}

