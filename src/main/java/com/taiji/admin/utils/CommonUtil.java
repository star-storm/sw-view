/**
 * 
 */
package com.taiji.admin.utils;

import java.io.UnsupportedEncodingException;
import java.util.Set;


/**
 * 
 * sw-view com.taiji.admin.utils CommonUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:01:35
 *
 * desc:
 */
public class CommonUtil {
	
	/**
	 * 集合转字符串
	 */
	public static String setToString(Set<String> set) {
		StringBuffer sbf = new StringBuffer();
		for (String s : set) {
			sbf.append(",");
			sbf.append(s);
		}
		return sbf.toString().substring(1);
	}
	
	/**
	 * set集合，字符串拼接
	 */
	public static String setConcat(Set<String> set) {
		StringBuffer sbf = new StringBuffer();
		for (String s : set) {
			sbf.append(",'");
			sbf.append(s);
			sbf.append("'");
		}
		return "(" + sbf.toString().substring(1) + ")";
	}
	
	/**
	 * array数组，字符串拼接
	 */
	public static String arrayConcat(String[] arr) {
		StringBuffer sbf = new StringBuffer();
		for (String s : arr) {
			sbf.append(",'");
			sbf.append(s);
			sbf.append("'");
		}
		return "(" + sbf.toString().substring(1) + ")";
	}
	
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
//		Set<String> set = new HashSet<String>();
//		set.add("0");
//		set.add("00");
////		System.out.println(setToString(set));
//		System.out.println(setConcat(set));
//		
//		String s = "a,b,c,";
//		System.out.println(arrayConcat(s.split(",")));
		
//		List<String> list = new ArrayList<String>();
//		list.add("4");
//		list.add("1");
//		list.add("3");
//		list.add("2");
//		List<String> sub = list.subList(0, 2);
//		for (String s : sub) {
//			System.out.println(s);
//		}
		
//		String name = "清理系统垃圾.zip";
////		name = URLEncoder.encode(name, "utf-8");
//		String rename = new String(name.getBytes("gbk"), "ISO-8859-1");
//		System.out.println(rename);
////		System.out.println(URLDecoder.decode(rename));
//		
////		String fName = "zxc";
////		String fName = "zx.c";
////		String fName = "zxc.";
//		String fName = ".zxc";
//		System.out.println(fName.substring(fName.lastIndexOf(".") + 1));
		
		
//		String userCode = "";
//		Integer id = CommonUtil.getRoleId(userCode);
	}

}
