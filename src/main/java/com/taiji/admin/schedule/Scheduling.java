/**
 * 
 */
package com.taiji.admin.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 
 * sw-view com.taiji.admin.schedule Scheduling.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:46:56
 *
 * desc:
 */
@EnableScheduling
@Component
public class Scheduling {
	
	/**
	 * 定时器测试
	 */
//	@Scheduled(cron = "0/2 * * * * *")
//	public void scheduleTest() {
//		System.out.println("定时器测试。。。" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
