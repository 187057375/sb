package com.sb.hyh.taskexecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * fixedRate = 5000表示每隔5000ms，Spring scheduling会调用一次该方法，不论该方法的执行时间是多少
 * 
 * fixedDelay = 5000表示当方法执行完毕5000ms后，Spring scheduling会再次调用该方法
 */
@Component
public class ScheduledTask {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer count0 = 1;
	private Integer count1 = 1;
	private Integer count2 = 1;

	// @Scheduled(fixedRate = 5000)
	// public void reportCurrentTime() {
	// System.out.println(String.format("---第%s次执行，当前时间为：%s", count0++,
	// dateFormat.format(new Date())));
	// }

	@Scheduled(fixedDelay = 5000)
	public void reportCurrentTimeAfterSleep() {
		System.out.println(Thread.currentThread().getName());
		System.out.println(String.format("===第%s次执行，当前时间为：%s", count1++, dateFormat.format(new Date())));
	}

	@Scheduled(cron = "*/5 * * * * *")
	public void reportCurrentTimeCron() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(String.format("+++第%s次执行，当前时间为：%s", count2++, dateFormat.format(new Date())));
	}
}