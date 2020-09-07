/**
 * 
 */
package com.taiji.admin.webservice;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.axis2.AxisFault;
import org.dom4j.DocumentException;

/**
 * 
 * sw-view com.taiji.admin.webservice SsoService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:53:37
 *
 * desc:
 */
@WebService
public interface SsoService {
	
	@WebMethod
	public String yuan(String param);  
	 
	@WebMethod
	public String login(String authTicket) throws AxisFault, DocumentException;
	 
	@WebMethod
	public String SynchronizedInfo(int operateId, String operateCode) throws AxisFault, DocumentException, IOException;
	
	@WebMethod
	public String updateOrg(String s) throws AxisFault, DocumentException, IOException;
	
	@WebMethod
	public String updateUser(String s) throws AxisFault, DocumentException, IOException;
	
}
