/**
 * 
 */
package com.taiji.admin.service;

import javax.servlet.http.HttpSession;

/**
 * 
 * sw-view com.taiji.admin.service LoginService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:21:51
 *
 * desc:
 */
public interface LoginService {
	
	int verifyUser(HttpSession session, String loginName);
	
	int verifyUser(HttpSession session, String loginName, String password);
	
	int singleLogin(HttpSession session, String authTicket);

	int verifyUserByUserCode(HttpSession session, String userCode);
	
	int checkPwd(String loginName, String password);
	
	int changePwd(HttpSession session, String password);

}
