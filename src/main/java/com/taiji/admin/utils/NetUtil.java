/**
 * 
 */
package com.taiji.admin.utils;

import java.io.IOException;
import java.net.InetAddress;

import org.dom4j.DocumentException;

/**
 * 
 * sw-view com.taiji.admin.utils NetUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:04:52
 *
 * desc:
 */
public class NetUtil {
	
	/**
	 * 获取当前IP
	 */
	public static String getLocalIP() throws IOException {
		String addr = InetAddress.getLocalHost().getHostAddress();
		System.out.println("ip: " + addr);
		return addr;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws IOException, DocumentException {
//		getLocalIP();
//		String s = "<userinfo><xml>"
//+ "<logname>wangz34</logname>"
//+ "<userCode>56c1acf815d943e98114ba9bd435bc94</userCode>"
//+ "<name>王志宇</name>"
//+ "<sex>null</sex>"
//+ "<address>null</address>"
//+ "<postCode>123456</postCode>"
//+ "<officePhone>null</officePhone>"
//+ "<mobilePhone>null</mobilePhone>"
//+ "<fax>null</fax>"
//+ "<email>null</email>"
//+ "<userType>null</userType>"
//+ "<userPost>null</userPost>"
//+ "<userDuty>null</userDuty>"
//+ "<orgCode>d4a9921b7d024d0da1c3a598aca2760c</orgCode>"
//+ "<userOrder>null</userOrder>"
//+ "<roles>"
//+ "<role>20538e88ff264f3d90275ef698937a09</role>"
//+ "<role>20538e88ff264f3d90275ef698937a09</role>"
//+ "</roles>"
//+ "</xml>"
//+ "</userinfo>";
//		SUser user = WebserviceUtil.parseUser(s);
//		System.out.println(JSONObject.fromObject(user).toString());
		
		String s = "ms windows 7";
		System.out.println(s.contains("windows"));
	}

}
