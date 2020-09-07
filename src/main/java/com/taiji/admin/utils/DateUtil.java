/**
 * 
 */
package com.taiji.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * sw-view com.taiji.admin.utils DateUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:03:26
 *
 * desc:
 */
public class DateUtil {
	
	/**
	 * pattern:
	 * 
	 * yyyy-MM-dd E kk:mm:ss  24h
	 * 
	 * yyyy-MM-dd E HH:mm:ss  12h
	 * 
	 */
	
	public static SimpleDateFormat getFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	/**
	 *  get string of DATE [yyyy-MM-dd] 
	 */
	public static String getDay() {
		return getFormat("yyyy-MM-dd").format(new Date());
	}
	
	/**
	 *  get string of DATE [yyyy-MM-dd kk:mm:ss] 
	 */
	public static String getTime() {
		return getFormat("yyyy-MM-dd kk:mm:ss").format(new Date());
	}
	
	/**
	 * java.util.Date --> String
	 */
	public static String dateToStr1(Date date) {
		return date==null?"":getFormat("yyyy-MM-dd kk:mm:ss").format(date);
	}
	public static String dateToStr2(String pattern) {
		return getFormat(pattern).format(new Date());
	}
	public static String dateToStr3() {
		return getFormat("yyyy-MM-dd kk:mm:ss").format(new Date());
	}	
	
	/**
	 * String --> java.util.Date
	 */
	public static Date strToDate1(String str) throws ParseException {
		return str==null?null:getFormat("yyyy-MM-dd kk:mm:ss").parse(str);
	}
	public static Date strToDate2(String str, String pattern) throws ParseException {
		return str==null?null:getFormat(pattern).parse(str);
	}
	
	/**
	 * String --> java.sql.Date
	 * @throws ParseException 
	 */
	public static java.sql.Date strToSqlDt(String str) throws ParseException {
		return str==null?null:new java.sql.Date(getFormat("yyyy-MM-dd kk:mm:ss").parse(str).getTime());
	}
	
	
	public static int getMonthDiff(Date dt1, Date dt2) {
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(dt1);
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(dt2);
		int count = -1;
		
		while (!ca1.after(ca2)) {
			ca2.add(Calendar.MONTH, -1);
			count++;
		}
		return count;
	}
	
	public static int getYearDiff(Date dt1, Date dt2) {
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(dt1);
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(dt2);
		int count = -1;
		
		while (!ca1.after(ca2)) {
			ca2.add(Calendar.YEAR, -1);
			count++;
		}
		return count;
	}
	
	public static String getTimeDiff(Date dt1, Date dt2) throws Exception {
		if (dt1 == null || dt2 == null)
			return null;
		long diff = (dt2.getTime() - dt1.getTime()) / 1000;
		if (diff == 0 )
			return "刚发布";
		else if (diff < 60 )
			return (int) diff + "秒前";
		else if (diff >= 60 && diff < 60 * 60)
			return (int) (diff / 60) + "分钟前";
		else if (diff >= 60 * 60 && diff < 60 * 60 * 24)
			return (int) (diff / (60 * 60)) + "小时前";
		else {
			Calendar ca = Calendar.getInstance();
			ca.setTime(dt2);
			ca.add(Calendar.MONTH, -1);
			if (ca.getTime().before(dt1)) {
				return (int) (diff / (60 * 60 * 24)) + "天前";
			}
			else {
				ca.setTime(dt2);
				ca.add(Calendar.YEAR, -1);
				if (ca.getTime().before(dt1))
					return getMonthDiff(dt1, dt2) + "月前";
				else
					return getYearDiff(dt1, dt2) + "年前";
			}
		}
	}

	
	
	public static void main(String[] args) throws Exception {
//		DateUtil util=new DateUtil();
//		System.out.println(util.dateToStr1(new Date()));
//		System.out.println("2014-07-17 18:50:47.0".split("\\.")[0]);
//		Date dt1 = util.strToDate1("2014-11-24 07:07:08");
//		Date dt2 = util.strToDate1("2014-11-24 07:07:07");
//		System.out.println(util.getYearDiff(dt1, dt2));
		
//		Calendar ca1 = Calendar.getInstance();
//		ca1.setTime(dt1);
//		Calendar ca2 = Calendar.getInstance();
//		ca2.setTime(dt2);
//		
//		System.out.println(ca1.after(ca2));
//		System.out.println(ca1.getTime().after(dt2));
	}
	
}
