/**
 * 
 */
package com.taiji.admin.service;

import java.util.List;
import java.util.Map;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.SOrg;
import com.taiji.admin.model.SUser;

/**
 * 
 * sw-view com.taiji.admin.service SOrgService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:22:38
 *
 * desc:
 */
public interface SOrgService {
	
	/**
	 * 计数
	 */
	long count(String id, String name, String code);
	
	/**
	 * 列表
	 */
	List<SOrg> orgPage(PageInfo pageInfo, String id, String name, String code);

	/**
	 * 组织信息
	 */
	SOrg getOrg(Integer id);
	
	/**
	 * 组织信息
	 */
	SOrg selectByCode(String code);

	/**
	 * 更新组织：新建，编辑
	 */
	int updateOrg(SOrg org, SUser host);

	/**
	 * 删除组织
	 */
	int deleteByCode(String code, SUser host);
	
	/**
	 * 删除组织
	 */
	int delOrg(Integer id, SUser host);
	
	/**
	 * 批量删除组织
	 */
	int delBatch(String ids, SUser host);
	
	/**
	 * 查询全部子组织
	 */
	List<SOrg> subOrgs(String pid);
	
	/**
	 * 条件查询组织
	 */
	List<SOrg> queryOrgs(String name);

	/**
	 * 根据id查询全部子类
	 */
	String orgChildren(String p);

	/**
	 * 根据id查询全部父类
	 */
	String orgParents(String p);
	
	/**
	 * 根据id查询最高级父类
	 */
	String orgParent(String p);

	/**
	 * 根据名称查询下级组织和该组织下用户
	 */
	List<Map<String, Object>> nextLevel(String name);

}
