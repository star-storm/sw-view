/**
 * 
 */
package com.taiji.app.service;

import java.util.List;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.SUser;
import com.taiji.app.model.TAppInfo;

/**
 * 
 * sw-view com.taiji.app.service TAppInfoService.java
 *
 * @author hsl
 *
 * 2020年5月9日 下午4:39:29
 *
 * desc:
 */
public interface TAppInfoService {
	
	/**
	 * 计数
	 */
	long count(String name, SUser host);
	
	/**
	 * 列表
	 */
	List<TAppInfo> appInfoPage(PageInfo pageInfo, String name, SUser host);

	/**
	 * 基本信息
	 */
	TAppInfo getAppInfo(Integer id);
	
	/**
	 * 查询全部
	 */
	List<TAppInfo> members();
	
	/**
	 * 更新：新建，编辑
	 */
	public String updateAppInfo(Integer id, String name, String code, String url, String owner, String pid, SUser host);

	/**
	 * 删除
	 */
	boolean delAppInfo(Integer id, SUser host);
	
	/**
	 * 批量删除组织
	 */
	LogMsgInfo delBatch(String ids, SUser host);

}
