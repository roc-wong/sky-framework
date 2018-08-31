package org.sky.framework.test.date;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author roc
 * @date 2018/08/14
 */
public class CacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheTest.class);

    public static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    private List<CacheEntity> resultList = Lists.newArrayListWithCapacity(10000);
    private Set<CacheEntity> resultSet = Sets.newHashSetWithExpectedSize(10000);

    @DataProvider(name = "getDate")
    public Iterator<Object[]> getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Object[]> iterator = Lists.newArrayList();
        for (int i = 0; i < 60; i++) {
            LocalDateTime localDateTime1 = localDateTime.plusSeconds(1);
            localDateTime = localDateTime1;
            String strDate = yyyyMMddHHmmss.print(localDateTime1);
            iterator.add(new Object[]{strDate});
//            iterator.add(new Object[]{strDate});
        }
        return iterator.iterator();
    }


    @Test(dataProvider = "getDate", threadPoolSize = 4, invocationCount = 2)
    public void test(String strDate){
        LOGGER.info("threadId={}, strDate={}", Thread.currentThread().getId(), strDate);
        CacheEntity cacheEntity = new CacheEntity("roc-" + strDate);
        if (CacheListHandler.isConcurrent(cacheEntity)) { // 含有该戳数据，丢弃。
            LOGGER.info("warning, repetition={}, array={}", cacheEntity, Arrays.toString(CacheListHandler.getList().toArray()));
            return;
        }
        LOGGER.error("threadId={}, result={}", Thread.currentThread().getId(), strDate);
        CacheListHandler.addCache(cacheEntity);
        resultList.add(cacheEntity);
        resultSet.add(cacheEntity);
    }

    @AfterTest
    public void print(){

//        LOGGER.info("list={}," , Arrays.toString(CacheListHandler.getList().toArray()));
//        LOGGER.info("TempList={}," , Arrays.toString(CacheListHandler.getTempList().toArray()));
        LOGGER.info("listSize={}, setSize={}", resultList.size(), resultSet.size());
    }
}
