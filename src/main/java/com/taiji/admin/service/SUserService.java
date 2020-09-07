/**
 * 
 */
package com.taiji.admin.service;

import java.util.List;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.SUser;

/**
 * 
 * sw-view com.taiji.admin.service SUserService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:23:05
 *
 * desc:
 */
public interface SUserService {
	
//	boolean verifyUser(User user);
	
	/**
	 * 计数
	 */
	long count(String userId, String name, String depart, String roleId);
	
	/**
	 * 列表
	 */
	List<SUser> userPage(PageInfo pageInfo, String userId, String name, String dept, String roleId);
	
	/**
	 * 所有用户
	 */
	List<SUser> allUserInfo();

	/**
	 * 用户信息
	 */
	SUser getUser(Integer id);
	
	/**
	 * 用户所有信息：基本信息，角色，权限等
	 */
	SUser getUserAll(Integer id);
	
	/**
	 * 更新用户：新建，编辑
	 */
	public Integer updateUser(SUser user, SUser host);

	/**
	 * 删除用户
	 */
	boolean delUser(Integer id, SUser host);
	
	LogMsgInfo delBatch(String ids, SUser host);

	boolean updateByUserCode(SUser user, SUser sUser, long count);

	boolean deleteByUserCode(SUser user, SUser tmp);

	boolean upUserRole(Integer uid, Integer rid, SUser host);

}
