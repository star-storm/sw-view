/**
 * 
 */
package com.taiji.admin.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * sw-view com.taiji.admin.utils PropertiesUtil.java
 *
 * @author hsl
 *
 * 2020年8月15日 下午6:42:49
 *
 * desc:
 *
 */
public class PropertiesUtil {
	
	/**
	 * 写入配置文件
	 * @throws IOException 
	 */
	public static void writeProperties(String name, String key, Object value) throws IOException {
		Properties prop = new Properties(); 
		InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
		prop.load(is);
		prop.put(key, value);
		prop.store(new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(name).getFile()), "");
	}
	
	/**
	 * 读取配置文件
	 * @throws IOException 
	 */
	public static String readProperties(String name, String key) throws IOException {
		Properties prop = new Properties(); 
		InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
		prop.load(is);
		String value = prop.getProperty(key);
		return value;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String name = "config/sys.properties";
		String key = "threshold";
		String value = readProperties(name, key);
		System.out.println(key+"="+value);
		writeProperties(name, key, "1221");
	}

}
