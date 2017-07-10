package org.sky.framework.test.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCron {

	public static void main(String[] args) throws Exception {
		Logger log = LoggerFactory.getLogger(TestCron.class);
		// String crons = "*/5 * * * * ?";
		// "0/5 30-59 9-10 ? * MON-FRI | 0/5 * 13-14 ? * MON-FRI | 0/30 0 15 ? * MON-FRI";
//		String crons = "0/5 30-59 9-10 ? * MON-FRI | 0/5 * 13-14 ? * MON-FRI | 0/30 0 15 ? * MON-FRI";
//		String crons = "0/5 30-59 9,10 ? * MON-FRI | 0/5 0-30 10,11 ? * MON-FRI | 0/5 * 13-14 ? * MON-FRI | 30 0 15 ? * MON-FRI";
		String crons = "0 */1 * ? * MON-FRI";
		
		
		log.info("------- Initializing -------------------");

		String[] cron = crons.split("\\|");

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		for (int i = 0; i < cron.length; i++) {
			//System.out.println(cron[i].trim());
			JobDetail job = JobBuilder.newJob(JobTest.class)
					.withIdentity("job : " + cron[i].trim() + " 序号 ：" + i, "group" + i).build();

			CronTrigger trigger = (CronTrigger) TriggerBuilder
					.newTrigger()
					.withIdentity("trigger" + i, "group" + i)
					.withSchedule(
							CronScheduleBuilder.cronSchedule(cron[i].trim()))
					.build();

			Date ft = sched.scheduleJob(job, trigger);
			log.info(job.getKey() + " has been scheduled to run at: " + ft
					+ " and repeat based on expression: "
					+ trigger.getCronExpression());
		}
		sched.start();

		log.info("------- Started Scheduler -----------------");

		log.info("------- Waiting five minutes... ------------");
		try {
			// Thread.sleep(300000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");

		// sched.shutdown(true);

		log.info("------- Shutdown Complete -----------------");

		SchedulerMetaData metaData = sched.getMetaData();
		log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");

	}

}
