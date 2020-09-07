/**
 * 
 */
package com.taiji.admin.utils;

import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.taiji.admin.model.SUser;

/**
 * 
 * jgj-txl com.taiji.admin.utils WebserviceUtil.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:51:13
 *
 * desc:
 */
@Component
public class WebserviceUtil {
	
	/**
	 * webservice同步用户数据
	 */
	public static String synUserWebservice(String url, String qName, int operateId, String operateCode) throws AxisFault {
		 String webServiceURL=url;
		 RPCServiceClient serviceClient;
         serviceClient = new RPCServiceClient();
         Options options = serviceClient.getOptions();
         options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT, new Integer(30000));
         EndpointReference targetEPR = new EndpointReference(webServiceURL + "?temp=00");
         options.setTo(targetEPR);
         QName opGetAllLegalInfor = new QName(qName, "getUserInfoByXml");
         Object[] opGetAllLegalInforArgs = new Object[] { operateCode };
         @SuppressWarnings("rawtypes")
         Class[] returnTypes = new Class[] { String.class };
         Object[] response = serviceClient.invokeBlocking(opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
 		String result = String.valueOf(response[0]);
 		return result;
	}
	
	/**
	 * webservice同步组织数据
	 */
	public static String synOrgWebservice(String url, String qName, int operateId, String operateCode) throws AxisFault {
		String webServiceURL=url;
		RPCServiceClient serviceClient;
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT, new Integer(30000));
		EndpointReference targetEPR = new EndpointReference(webServiceURL + "?temp=00");
		options.setTo(targetEPR);
		QName opGetAllLegalInfor = new QName(qName, "getDeptInfoByXml");
		Object[] opGetAllLegalInforArgs = new Object[] { operateCode };
		@SuppressWarnings("rawtypes")
		Class[] returnTypes = new Class[] { String.class };
		Object[] response = serviceClient.invokeBlocking(opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
		String result = String.valueOf(response[0]);
		return result;
	}
	
	/**
	 * webservice请求登录结果
	 * 返回登录结果XMl
	 */
	public static String loginWebservice(String url, String qName, String authTicket) throws AxisFault {
		String webServiceURL = url;
		RPCServiceClient serviceClient;
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT, new Integer(30000));
		EndpointReference targetEPR = new EndpointReference(webServiceURL + "?temp=00");
		options.setTo(targetEPR);

		QName opGetAllLegalInfor = new QName(qName, "getTicketByXml");
		Object[] opGetAllLegalInforArgs = new Object[] { authTicket };
		@SuppressWarnings("rawtypes")
		Class[] returnTypes = new Class[] { String.class };
		Object[] response = serviceClient.invokeBlocking(opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
		String result = String.valueOf(response[0]);
		return result;
	}
	
	/**
	 * webservice请求登录结果
	 * 返回登录结果XMl
	 */
	public static String loginWebservice(String addr, String port, String loginName, String password) throws AxisFault {
		String webServiceURL = "http://" + addr + ":" + port + "/tyrz/services/singleLoginService";
		String qName = "http://service.auth.taiji.com";
		RPCServiceClient serviceClient;
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT, new Integer(30000));
		EndpointReference targetEPR = new EndpointReference(webServiceURL + "?temp=00");
		options.setTo(targetEPR);
		
		QName opGetAllLegalInfor = new QName(qName, "getUserCode");
		Object[] opGetAllLegalInforArgs = new Object[] { loginName, password };
		@SuppressWarnings("rawtypes")
		Class[] returnTypes = new Class[] { String.class, String.class };
		Object[] response = serviceClient.invokeBlocking(opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
		String result = String.valueOf(response[0]);
		return result;
	}
	
	/**
	 * 解析XML
	 * 返回usercode
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static String getUserCode(String str) throws DocumentException{
		Document doc = DocumentHelper.parseText(str);
		Element rootElement = doc.getRootElement();
		for (Iterator iter = rootElement.elementIterator();iter.hasNext();) {
			Element element = (Element) iter.next();
			String logname = (element.elementText("logname")==null||element.elementText("logname").equals("null"))?"":element.elementText("logname");
			String userCode = (element.elementText("userCode")==null||element.elementText("userCode").equals("null"))?"":element.elementText("userCode");
			String rl = (element.elementText("userCode")==null||element.elementText("userCode").equals("null"))?"":element.elementText("userCode");
			//roles =new HashSet( Arrays.asList(rl.split(","))) ;
			//String sql
			for (Iterator it = element.elementIterator();it.hasNext();) {
				Element ele = (Element) it.next();
				String tagName = ele.getName();
				
				String tagContent = ele.getTextTrim();
				System.out.println(tagName+":"+tagContent);
				
				if (tagName.equals("userCode"))
					return tagContent;
			}
		}
		return "";
	}
	
	/**
	 * 解析XML
	 * 返回user
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static SUser parseUser(String str) throws DocumentException{
		SUser user = new SUser();
		Document doc = DocumentHelper.parseText(str);
		Element rootElement = doc.getRootElement();
		for (Iterator iter = rootElement.elementIterator();iter.hasNext();) {
			Element element = (Element) iter.next();
			String logname = (element.elementText("logname")==null||element.elementText("logname").equals("null"))?"":element.elementText("logname");
			String userCode = (element.elementText("userCode")==null||element.elementText("userCode").equals("null"))?"":element.elementText("userCode");
			String name = (element.elementText("userCode")==null||element.elementText("userCode").equals("null"))?"":element.elementText("userCode");
			for (Iterator it = element.elementIterator();it.hasNext();) {
				Element ele = (Element) it.next();
				String tagName = ele.getName();
				String tagContent = ele.getTextTrim();
				System.out.println(tagName+":"+tagContent);
				if (tagName.equals("userCode"))
					user.setUserCode(tagContent);
				else if (tagName.equals("logname"))
					user.setLoginName(tagContent);
				else if (tagName.equals("name"))
					user.setName(tagContent);
				else if (tagName.equals("role"))
					user.setRole(tagContent);
			}
		}
		return user;
	}
	
	/**
	 * webservice-util
	 */
	public static Object webservice(String webServiceURL, String qName, String funcName, Class<?>[] returnTypes) throws AxisFault {
		RPCServiceClient serviceClient;
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT, new Integer(30000));
		EndpointReference targetEPR = new EndpointReference(webServiceURL);
		options.setTo(targetEPR);
		
		QName opGetAllLegalInfor = new QName(qName, funcName);
		Object[] opGetAllLegalInforArgs = new Object[] { };
//		Class[] returnTypes = new Class[] { String.class };
		Object[] response = serviceClient.invokeBlocking(opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
		return response[0];
	}
	
	/**
	 * webservice-util
	 */
	public static Object webservice(String webServiceURL, String qName, String funcName, Object[] opGetAllLegalInforArgs, Class<?>[] returnTypes) throws AxisFault {
		RPCServiceClient serviceClient;
		serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT, new Integer(30000));
		EndpointReference targetEPR = new EndpointReference(webServiceURL);
		options.setTo(targetEPR);
		
		QName opGetAllLegalInfor = new QName(qName, funcName);
//		Object[] opGetAllLegalInforArgs = new Object[] { };
//		Class[] returnTypes = new Class[] { String.class };
		Object[] response = serviceClient.invokeBlocking(opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
		return response[0];
	}

	/**
	 * @param args
	 * @throws AxisFault 
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws AxisFault, DocumentException {
		//查询-角色
		String webServiceURL = "http://localhost:8080/newplatform/services/singleLoginService";
		String qName = "http://service.auth.taiji.com";
		String funcName = "getUserCode";
		Object[] opGetAllLegalInforArgs = new Object[] {"admin", "admin"};
		@SuppressWarnings("rawtypes")
		Class[] returnTypes = new Class[] { String.class };
		Object result = webservice(webServiceURL, qName, funcName, opGetAllLegalInforArgs, returnTypes);
		System.out.println(result);	
	}

}
