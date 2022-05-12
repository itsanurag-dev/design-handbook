package com.codex.system.design.timer;

public class Timer {

	public static void main(String... args) {

	}

//	public void scheduleJob(String job, int noOfMinutes) {
//		CountDownLatch latch = new CountDownLatch(1);
//		// JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("CronQuartzJob", "Group").build();
//
//		// Create a Trigger that fires every 5 minutes.
//		Trigger trigger = newTrigger().withIdentity("TriggerName", "Group")
//				.withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();
//
//		// Setup the Job and Trigger with Scheduler & schedule jobs
//		final Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//		scheduler.start();
//		scheduler.scheduleJob(job, trigger);
//		latch.await();
//		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					scheduler.shutdown();
//					latch.countDown();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}));
//
//	}

}
