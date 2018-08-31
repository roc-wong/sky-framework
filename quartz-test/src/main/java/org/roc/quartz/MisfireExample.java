package org.roc.quartz;

import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DailyTimeIntervalTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TimeOfDay;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.FRIDAY;
import static org.quartz.DateBuilder.MONDAY;
import static org.quartz.DateBuilder.THURSDAY;
import static org.quartz.DateBuilder.TUESDAY;
import static org.quartz.DateBuilder.WEDNESDAY;
import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Demonstrates the behavior of <code>StatefulJob</code>s, as well as how misfire instructions affect the firings of triggers of <code>StatefulJob</code> s - when the jobs take longer to execute that the frequency of the trigger's repitition.
 * <p>
 * <p> While the example is running, you should note that there are two triggers with identical schedules, firing identical jobs. The triggers "want" to fire every 3 seconds, but the jobs take 10 seconds to execute. Therefore, by the time the jobs complete their execution, the triggers have already "misfired" (unless the scheduler's "misfire threshold" has been set to more than 7 seconds). You should see that one of the jobs has its misfire instruction set to <code>SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT</code>, which causes it to fire immediately, when the misfire is detected. The other trigger uses the default "smart policy" misfire instruction, which causes the trigger to advance to its next fire time (skipping those that it has missed) - so that it does not refire immediately, but rather at the next scheduled time. </p>
 *
 * @author <a href="mailto:bonhamcm@thirdeyeconsulting.com">Chris Bonham</a>
 */
public class MisfireExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(MisfireExample.class);

    public void run() throws Exception {

        LOGGER.info("------- Initializing -------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory stdSchedulerFactory = new StdSchedulerFactory("quartz.properties");
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        LOGGER.info("------- Initialization Complete -----------");

        LOGGER.info("------- Scheduling Jobs -----------");

        // jobs can be scheduled before start() has been called

        // get a "nice round" time a few seconds in the future...
        Date startTime = nextGivenSecondDate(null, 15);

        // statefulJob1 will run every three seconds
        // (but it will delay for ten seconds)
        JobDetail job = newJob(StatefulDumbJob.class).withIdentity("statefulJob1", "group1").usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L).build();

        SimpleScheduleBuilder simpleScheduleBuilder = simpleSchedule().withIntervalInSeconds(3).repeatForever();

        DailyTimeIntervalScheduleBuilder mutableTrigger = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0))
                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(19, 41))
                .onDaysOfTheWeek(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
                .withIntervalInSeconds(3);

        DailyTimeIntervalTrigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(mutableTrigger).build();


        Date date = scheduler.scheduleJob(job, trigger);
        LOGGER.info(job.getKey() + " will run at: " + date + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() + " seconds");

        LOGGER.info("------- Starting Scheduler ----------------");

        // jobs don't start firing until start() has been called...
        scheduler.start();

        LOGGER.info("------- Started Scheduler -----------------");

        try {
            // sleep for ten minutes for triggers to file....
            Thread.sleep(600L * 1000L);
        } catch (Exception e) {
        }

        LOGGER.info("------- Shutting Down ---------------------");

        scheduler.shutdown(true);

        LOGGER.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        LOGGER.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws Exception {

        MisfireExample example = new MisfireExample();
        example.run();
    }

}