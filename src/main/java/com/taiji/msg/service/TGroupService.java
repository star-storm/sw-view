/**
 * 
 */
package com.taiji.msg.service;

import java.util.List;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.SUser;
import com.taiji.msg.model.TGroup;

/**
 * 
 * sw-view com.taiji.admin.service TGroupService.java
 *
 * @author hsl
 *
 * 2019年12月14日 下午5:38:32
 *
 * desc:
 */
public interface TGroupService {
	
	/**
	 * 计数
	 */
	long count(String name, SUser host);
	
	/**
	 * 列表
	 */
	List<TGroup> groupPage(PageInfo pageInfo, String name, SUser host);

	/**
	 * 群组信息
	 */
	TGroup getGroup(Integer id);
	
	/**
	 * 更新群组：新建，编辑
	 */
	public String updateGroup(Integer id, String toOrgIds, String toOrgNames, 
			String toUserIds, String toUserNames, String toUserPhones, String name, String ids, SUser host);

	/**
	 * 删除群组
	 */
	boolean delGroup(Integer id, SUser host);

}
