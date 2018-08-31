package org.sky.framework.test.date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author roc
 * @date 2018/04/25
 */
public class Timezone {

    private static final Logger logger = LoggerFactory.getLogger(Timezone.class);

    public static void main(String[] args) {
        Date date = new Date();

        Date datePartDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseLocalDateTime(args[0]).toDate();
        logger.info("datePartDate: {}", datePartDate);

        Date timePartDate = DateTimeFormat.forPattern("HH:mm:ss").parseLocalDateTime(args[1]).toDate();
        logger.info("timePartDate: {}", timePartDate);

        LocalTime triggerTime = LocalTime.fromDateFields(timePartDate);
        logger.info("triggerTime: {}", triggerTime);

        Period periodFromMidnight = Period.fieldDifference(LocalTime.MIDNIGHT, triggerTime);
        logger.info("periodFromMidnight: {}", periodFromMidnight);

        LocalDateTime triggerDateTime = LocalDateTime.fromDateFields(datePartDate).plus(periodFromMidnight);
        logger.info("triggerDateTime: {}", triggerDateTime);

        Duration duration = new Duration(triggerDateTime.toDateTime(), DateTime.now());
        logger.info("duration: {}", duration);

        long millis = duration.getMillis();
        logger.info("millis: {}", millis);
    }

}
