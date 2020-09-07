/**
 * 
 */
package com.taiji.admin.webservice;

import com.taiji.admin.utils.WebserviceUtil;

/**
 * sw-view com.taiji.admin.webservice WebserviceTest.java
 *
 * @author hsl
 *
 * 2019年12月18日 下午12:44:08
 *
 * desc:
 */
public class WebserviceTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String xml = "";
		xml = "<userinfo>";
		xml += "<xml>\r\n";
		xml += "<logname>getLogName</logname>\r\n";
		xml += "<userCode>getUserCode</userCode>\r\n";
		xml += "<name>getName</name>\r\n";
		xml += "<sex>getSex</sex>\r\n";
		xml += "<address>getAddress</address>\r\n";

		// xml+="<postCode>"+tempUserc+"</postCode>\r\n";//邮编存放证书号
		xml += "<postCode>" + "123456" + "</postCode>\r\n";
		xml += "<officePhone>getOfficePhone</officePhone>\r\n";// 座机
		xml += "<mobilePhone>getMobilePhone</mobilePhone>\r\n";// 手机
		xml += "<fax>getFax</fax>\r\n";
		xml += "<email>getEmail</email>\r\n";
		xml += "<userType>" + "0" + "</userType>\r\n";
		// xml+="<userType>"+tempUser.getUserType()+"</userType>\r\n";
		xml += "<userPost>getUserPost</userPost>\r\n";
		xml += "<userDuty>getUserDuty</userDuty>\r\n"; // 职务
		xml += "<orgCode>1</orgCode>\r\n";
		xml += "<userOrder>12</userOrder>\r\n";
		xml += "<userPic>getUserPic</userPic>\r\n";// 图片
		xml += "<userPotitle>getUserPotitle</userPotitle>\r\n"; // 职称
		xml += "<roles>\r\n";
		xml += "</roles>\r\n";
		xml+="</xml>\r\n";
		xml+="</userinfo>\r\n";
		   
		String webServiceURL = "http://localhost:8081/SsoWebservice";
		String qName = "http://txl/services";
		String funcName = "updateUser";
		Object[] opGetAllLegalInforArgs = new Object[] {xml};
		@SuppressWarnings("rawtypes")
		Class[] returnTypes = new Class[] { String.class };
		Object result = WebserviceUtil.webservice(webServiceURL, qName, funcName, opGetAllLegalInforArgs, returnTypes);
		System.out.println(result);	
	}

}
