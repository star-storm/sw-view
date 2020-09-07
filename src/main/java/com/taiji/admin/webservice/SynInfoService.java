/**
 * 
 */
package com.taiji.admin.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 
 * sw-view com.taiji.admin.webservice SynInfoService.java
 *
 * @author hsl
 *
 * 2020年4月26日 下午2:41:24
 *
 * desc:
 */
@WebService
public interface SynInfoService {
	
	@WebMethod
	public String test(String param);  
	 
	@WebMethod
	public String getDeptInfoByXml(String deptCode);
	
	@WebMethod
	public String getUserInfoByXml(String userCode);
	
//	@WebMethod
//	public String synInfoService(Integer operateId, String operateCode);
//	
//	@WebMethod
//	public String singleLoginService(Integer operateId, String operateCode);
	
}
