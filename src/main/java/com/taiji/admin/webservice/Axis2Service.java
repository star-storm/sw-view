/**
 * 
 */
package com.taiji.admin.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 
 * sw-view com.taiji.admin.webservice SsoService.java
 *
 * @author hsl
 *
 * 2020年1月1日 下午10:22:43
 *
 * desc:
 */
@WebService
public interface Axis2Service {
	
	@WebMethod
	public String yuan(String param);  
	 
}
