/**
 * 
 */
package com.taiji.admin.service;

import java.util.List;
import java.util.Map;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.SPermission;
import com.taiji.admin.model.SUser;

/**
 * 
 * sw-view com.taiji.admin.service SPermissionService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:22:45
 *
 * desc:
 */
public interface SPermissionService {
	
	/**
	 * 计数
	 */
	long count(String name, String descrip);
	
	/**
	 * 列表
	 */
	List<SPermission> permissionPage(PageInfo pageInfo, String name, String descrip);

	/**
	 * 基本信息
	 */
	public SPermission getPermission(Integer id);
	
	/**
	 * 角色的权限信息
	 */
	List<SPermission> selectByRole(Integer rid);
	
	/**
	 * 查询全部权限
	 * 只查询权限Id和名称
	 */
	List<Map<String, Object>> members();

	/**
	 * 更新权限
	 */
	Integer upPermission(Integer id, String name, Integer type, String url, String method, String desc, SUser host);

	/**
	 * 删除角色
	 */
	LogMsgInfo delPermission(Integer id, SUser host);

	/**
	 * 批量删除权限
	 */
	LogMsgInfo delBatch(String ids, SUser host) throws RuntimeException;

}
