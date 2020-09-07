/**
 * 
 */
package com.taiji.admin.service;

import com.taiji.admin.model.SLoginInfo;
import com.taiji.admin.model.SUser;

/**
 * 
 * sw-view com.taiji.admin.service SLoginInfoService.java
 *
 * @author hsl
 *
 * 2020年8月17日 下午4:29:19
 *
 * desc:
 *
 */
public interface SLoginInfoService {

	/**
	 * 登录信息
	 */
	SLoginInfo getLoginInfo(Integer userId);
	SLoginInfo getLoginInfoByName(String loginName);
	
	/**
	 * 更新登录信息：新建，编辑
	 */
	public Integer updateLoginInfo(SLoginInfo loginInfo, SUser host);
	
}
