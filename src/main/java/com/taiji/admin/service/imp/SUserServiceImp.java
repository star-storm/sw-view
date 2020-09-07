/**
 * 
 */
package com.taiji.admin.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.mapper.SUserRoleMapper;
import com.taiji.admin.model.SUser;
import com.taiji.admin.model.SUserExample;
import com.taiji.admin.model.SUserRole;
import com.taiji.admin.model.SUserRoleExample;
import com.taiji.admin.service.SUserService;

/**
 * 
 * teach-src com.taiji.admin.service.imp SUserServiceImp.java
 *
 * @author hsl
 *
 * 2018年1月25日 上午11:45:43
 *
 * desc:
 */
@Service
public class SUserServiceImp implements SUserService {
	
	@Autowired
	private SUserMapper userMapper;
	
	@Autowired
	private SUserRoleMapper userRoleMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String userId, String name, String depart, String roleId) {
		SUserExample example = new SUserExample();
		SUserExample.Criteria criteria = example.createCriteria();
//		if (!StringUtils.isEmpty(userId))
//			criteria.andUserIdLike("%" + userId + "%");
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(depart))
			criteria.andOrgNameLike("%" + depart + "%");
		if (!StringUtils.isEmpty(roleId))
			criteria.andRoleCondition(Integer.valueOf(roleId));
		criteria.andDelFlgEqualTo("0");
		return userMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
	@Override
	public List<SUser> userPage(PageInfo pageInfo, String userId, String name, String depart, String roleId) {
		SUserExample example = new SUserExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		SUserExample.Criteria criteria = example.createCriteria();
//		if (!StringUtils.isEmpty(userId))
//			criteria.andUserIdLike("%" + userId + "%");
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(depart))
			criteria.andOrgNameLike("%" + depart + "%");
		if (!StringUtils.isEmpty(roleId))
			criteria.andRoleCondition(Integer.valueOf(roleId));
		criteria.andDelFlgEqualTo("0");
		example.setOrderByClause(" (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc, id desc");
		return userMapper.selectByExample(example);
	}
	
	/**
	 * 所有用户
	 */
	@Override
	public List<SUser> allUserInfo() {
		return userMapper.allUserInfo();
	}
	
	/**
	 * 用户基本信息
	 */
	@Override
	public SUser getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 用户所有信息：基本信息，角色，权限等
	 */
	@Override
	public SUser getUserAll(Integer id) {
		return userMapper.selectUserFullMsg(id);
	}
	
	/**
	 * 更新用户：新建，编辑
	 */
	@Override
	public Integer updateUser(SUser user, SUser host) {
		Integer id = user.getId();
		if (id == null) {//新建
			user.setCreateBy(host.getId());
			user.setCreateDate(new Date());
			user.setDelFlg("0");
			userMapper.insertSelective(user);
			id = user.getId();
		}
		else {//编辑
			SUser tmp = userMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				user.setUpdateBy(host.getId());
				user.setUpdateDate(new Date());
				user.setUserPwd(tmp.getUserPwd());
				userMapper.updateByPrimaryKeySelective(user);
			}
			else
				return -1;
		}
		return id;
	}
	
	/**
	 * 删除用户
	 */
	@Override
	public boolean delUser(Integer id, SUser host) {
		if (id == null)
			return false;
		else {
			SUser tmp = userMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				tmp.setDelFlg("1");//逻辑删除
				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				userMapper.updateByPrimaryKeySelective(tmp);
				
				//删除原用户角色
				SUserRoleExample example = new SUserRoleExample();
				SUserRoleExample.Criteria criteria = example.createCriteria();
				criteria.andUidEqualTo(tmp.getId());
				userRoleMapper.deleteByExample(example);
			}
			else
				return false;
		}
		return true;
	}

	/**
	 * 批量删除用户
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public LogMsgInfo delBatch(String ids, SUser host) throws RuntimeException {
		LogMsgInfo info = new LogMsgInfo();
		info.setId(ids);
		info.setResult(0);
		StringBuffer sbf = new StringBuffer();
		
		if (StringUtils.isEmpty(ids))
			info.setResult(1);
		for (String id : ids.split(",")) {
			if (id == null)//参数错误
				info.setResult(1);
			else {
				SUser tmp = userMapper.selectByPrimaryKey(Integer.valueOf(id));
				if (tmp != null) {
					sbf.append(tmp.getName() + ",");
					tmp.setDelFlg("1");//逻辑删除
					tmp.setUpdateBy(host.getId());
					tmp.setUpdateDate(new Date());
					userMapper.updateByPrimaryKeySelective(tmp);
					
					//删除原用户角色
					SUserRoleExample example = new SUserRoleExample();
					SUserRoleExample.Criteria criteria = example.createCriteria();
					criteria.andUidEqualTo(tmp.getId());
					userRoleMapper.deleteByExample(example);
				}
				else//用户不存在
					info.setResult(3);
			}
		}
		return info;
	}

	/**
	 * 通过userCode更新用户
	 */
	public boolean updateByUserCode(SUser user, SUser host, long count) {
		String userCode = user.getUserCode();
		if (count == 0) {//新建
//			user.setCreateBy(host.getId());
			user.setCreateDate(new Date());
			user.setDelFlg("0");
			userMapper.insertSelective(user);
		}
		else {//编辑
			SUser tmp = userMapper.selectByUserCode(userCode);
			if (tmp != null) {
				user.setId(tmp.getId());
//				user.setUpdateBy(host.getId());
				user.setUpdateDate(new Date());
				userMapper.updateByPrimaryKeySelective(user);
				
				//删除原用户角色
				SUserRoleExample example = new SUserRoleExample();
				SUserRoleExample.Criteria criteria = example.createCriteria();
				criteria.andUidEqualTo(tmp.getId());
				userRoleMapper.deleteByExample(example);
			}
			else
				return false;
		}
		return true;
	}

	/**
	 * 通过userCode删除用户
	 */
	public boolean deleteByUserCode(SUser user, SUser host) {
		String userCode = user.getUserCode();
		if (StringUtils.isEmpty(userCode))
			return false;
		else {
			SUser tmp = userMapper.selectByUserCode(userCode);
			if (tmp != null) {
				user.setId(tmp.getId());
				user.setDelFlg("1");//逻辑删除
//				user.setUpdateBy(host.getId());
				user.setUpdateDate(new Date());
				userMapper.updateByPrimaryKeySelective(user);
				
				//删除原用户角色
				SUserRoleExample example = new SUserRoleExample();
				SUserRoleExample.Criteria criteria = example.createCriteria();
				criteria.andUidEqualTo(tmp.getId());
				userRoleMapper.deleteByExample(example);
			}
			else
				return false;
		}
		return true;
	}

	/**
	 * 更新用户角色
	 */
	@Override
	public boolean upUserRole(Integer uid, Integer rid, SUser host) {
		//删除原用户角色
		SUserRoleExample example = new SUserRoleExample();
		SUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		userRoleMapper.deleteByExample(example);
		
		//新建用户角色
		SUserRole po = new SUserRole();
		po.setUid(uid);
		po.setRid(rid);
		po.setDelFlg("0");
		po.setCreateBy(host.getId());
		po.setCreateDate(new Date());
		userRoleMapper.insertSelective(po);
		
		//更新用户最后修改时间
		SUser user = new SUser();
		user.setUpdateBy(host.getId());
		user.setUpdateDate(new Date());
		SUserExample usreExample = new SUserExample();
		SUserExample.Criteria criteria2 = usreExample.createCriteria();
		criteria2.andIdEqualTo(uid);
		userMapper.updateByExampleSelective(user, usreExample);
		return true;
	}

}
