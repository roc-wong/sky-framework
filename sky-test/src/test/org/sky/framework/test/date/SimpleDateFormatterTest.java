package org.sky.framework.test.date;

import com.google.common.collect.Lists;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author roc
 * @date 2018/08/14
 */
public class SimpleDateFormatterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDateFormatterTest.class);

    public static final String pattern = "yyyyMMddHHmmss";

    public static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    public static Date parseDate(String strDate, String pattern) {
        Date date = null;

        try {
            if (pattern == null) {
                pattern = "yyyy-MM-dd";
            }

            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(strDate);
        } catch (Exception var4) {
            var4.printStackTrace();
        }
        return date;
    }

    @DataProvider(name = "getDate")
    public Iterator<Object[]> getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Object[]> iterator = Lists.newArrayList();
        for (int i = 0; i < 50; i++) {
            String strDate = yyyyMMddHHmmss.print(localDateTime.plusDays(1));
            iterator.add(new Object[]{strDate});
        }
        return iterator.iterator();
    }

    @Test(threadPoolSize = 8, invocationCount = 50, dataProvider = "getDate")
    public void testSimpleDateFormat(String strDate) {
        Date date = parseDate(strDate, pattern);
        String really = yyyyMMddHHmmss.print(date.getTime());
        if (!strDate.equals(really)) {
            LOGGER.error("input={}, output={}", strDate, really);
        } else {
            LOGGER.info("input={}, output={}", strDate, really);
        }
    }
}