/**
 * 
 */
package com.taiji.admin.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 
 * sw-view com.taiji.admin.webservice SingleLoginService.java
 *
 * @author hsl
 *
 * 2020年4月26日 下午3:16:48
 *
 * desc:
 */
@WebService
public interface SingleLoginService {
	
	@WebMethod
	public String test(String param);  
	 
	@WebMethod
	public String getTicketByXml(String ticket);
	
}
