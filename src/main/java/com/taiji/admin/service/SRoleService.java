/**
 * 
 */
package com.taiji.admin.service;

import java.util.List;
import java.util.Map;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.SRole;
import com.taiji.admin.model.SUser;

/**
 * 
 * sw-view com.taiji.admin.service SRoleService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:22:50
 *
 * desc:
 */
public interface SRoleService {
	
	/**
	 * 计数
	 */
	long count(String name, String descrip);
	
	/**
	 * 列表
	 */
	List<SRole> rolePage(PageInfo pageInfo, String name, String descrip);

	/**
	 * 角色信息
	 */
	SRole getRole(Integer id);
	
	/**
	 * 角色所有信息：基本信息，角色，权限等
	 */
	SRole getRoleAll(Integer id);

	/**
	 * 更新角色：新建，编辑
	 */
	int updateRole(String id, String name, String descrip, String pIds, SUser host);

	/**
	 * 删除角色
	 */
	LogMsgInfo delRole(Integer id, SUser host);
	
	/**
	 * 批量删除角色
	 */
	LogMsgInfo delBatch(String ids, SUser host);

	/**
	 * 查询全部角色
	 * 只查询角色Id和名称
	 */
	List<Map<String, Object>> members();

	/**
	 * 更新用户角色
	 */
	boolean upUserRole(Integer uid, Integer rid, SUser host);


}
