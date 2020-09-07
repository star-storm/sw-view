/**
 * 
 */
package com.taiji.admin.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.mapper.SRoleMapper;
import com.taiji.admin.mapper.SRolePerMapper;
import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.mapper.SUserRoleMapper;
import com.taiji.admin.model.SRole;
import com.taiji.admin.model.SRoleExample;
import com.taiji.admin.model.SRolePer;
import com.taiji.admin.model.SRolePerExample;
import com.taiji.admin.model.SUser;
import com.taiji.admin.model.SUserExample;
import com.taiji.admin.model.SUserRole;
import com.taiji.admin.model.SUserRoleExample;
import com.taiji.admin.service.SRoleService;

/**
 * 
 * teach-src-manage com.taiji.admin.service.imp SRoleServiceImp.java
 *
 * @author hsl
 *
 * 2018年3月9日 下午1:44:28
 *
 * desc:
 */
@Service
public class SRoleServiceImp implements SRoleService {
	
	@Autowired
	private SRoleMapper roleMapper;
	@Autowired
	private SUserRoleMapper userRoleMapper;
	@Autowired
	private SRolePerMapper rolePerMapper;
	@Autowired
	private SUserMapper userMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String name, String descrip) {
		SRoleExample example = new SRoleExample();
		SRoleExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(descrip))
			criteria.andDescripLike("%" + descrip + "%");
		criteria.andDelFlgEqualTo("0");
		return roleMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
	@Override
	public List<SRole> rolePage(PageInfo pageInfo, String name, String descrip) {
		SRoleExample example = new SRoleExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		SRoleExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(descrip))
			criteria.andDescripLike("%" + descrip + "%");
		criteria.andDelFlgEqualTo("0");
		example.setOrderByClause(" (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc, id desc");
		return roleMapper.selectByExample(example);
	}
	
	/**
	 * 角色基本信息
	 */
	@Override
	public SRole getRole(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 角色所有信息：基本信息，权限等
	 */
	@Override
	public SRole getRoleAll(Integer id) {
		return roleMapper.selectRoleFullMsg(id);
	}
	
	/**
	 * 更新角色：新建，编辑
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public int updateRole(String id, String name, String descrip, String pIds, SUser host) {
		if (StringUtils.isEmpty(name))//参数错误
			return 1;
		SRole role = new SRole();
		role.setName(name);
		role.setDescrip(descrip);
		if (StringUtils.isEmpty(id)) {//新建
			role.setCreateBy(host.getId());
			role.setCreateDate(new Date());
			role.setDelFlg("0");
			roleMapper.insertSelective(role);
		}
		else {//编辑
			SRole tmp = roleMapper.selectByPrimaryKey(Integer.valueOf(id));
			if (tmp != null) {
				role.setId(Integer.valueOf(id));
				role.setName(name);
				role.setDescrip(descrip);
				role.setUpdateBy(host.getId());
				role.setUpdateDate(new Date());
				roleMapper.updateByPrimaryKeySelective(role);
			}
			else
				return 2;//数据不存在
		}
		//更新权限
		if (!StringUtils.isEmpty(pIds)) {
			if (!StringUtils.isEmpty(id)) {//新建
				//删除原数据
				SRolePerExample example = new SRolePerExample();
				SRolePerExample.Criteria criteria = example.createCriteria();
				criteria.andRidEqualTo(Integer.valueOf(id));
				rolePerMapper.deleteByExample(example);
			}
			
			//插入新数据
			for (String pId : pIds.split(",")) {
				if (!StringUtils.isEmpty(pId)) {
					SRolePer record = new SRolePer();
					record.setPid(Integer.valueOf(pId));
					record.setRid(role.getId());
					record.setDelFlg("0");
					record.setCreateBy(host.getId());
					record.setCreateDate(new Date());
					rolePerMapper.insert(record);
				}
			}
		}
		return 0;
	}
	
	/**
	 * 删除角色
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public LogMsgInfo delRole(Integer id, SUser host) {
		LogMsgInfo info = new LogMsgInfo();
		info.setId(String.valueOf(id));
		info.setResult(0);
		if (id == null)//参数错误
			info.setResult(1);
		else {
			//检测是否有用户有该角色
			long count = roleMapper.checkRole(id);
			if (count > 0)//有用户有该角色
				info.setResult(2);
			SRole tmp = roleMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				info.setName(tmp.getName());
				tmp.setDelFlg("1");//逻辑删除
				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				roleMapper.updateByPrimaryKeySelective(tmp);
				
				//删除权限数据
				SRolePer srp = new SRolePer();
				srp.setDelFlg("1");//逻辑删除
				srp.setUpdateBy(host.getId());
				srp.setUpdateDate(new Date());
				SRolePerExample example = new SRolePerExample();
				SRolePerExample.Criteria criteria = example.createCriteria();
				criteria.andRidEqualTo(Integer.valueOf(id));
				rolePerMapper.updateByExampleSelective(srp, example);
			}
			else//角色不存在
				info.setResult(3);
		}
		return info;
	}

	/**
	 * 批量删除角色
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
				//检测是否有用户有该角色
				long count = roleMapper.checkRole(Integer.valueOf(id));
				if (count > 0)//有用户有该角色
					info.setResult(2);
				SRole tmp = roleMapper.selectByPrimaryKey(Integer.valueOf(id));
				if (tmp != null) {
					sbf.append(tmp.getName() + ",");
					tmp.setDelFlg("1");//逻辑删除
					tmp.setUpdateBy(host.getId());
					tmp.setUpdateDate(new Date());
					roleMapper.updateByPrimaryKeySelective(tmp);
					
					//删除权限数据
					SRolePer srp = new SRolePer();
					srp.setDelFlg("1");//逻辑删除
					srp.setUpdateBy(host.getId());
					srp.setUpdateDate(new Date());
					SRolePerExample example = new SRolePerExample();
					SRolePerExample.Criteria criteria = example.createCriteria();
					criteria.andRidEqualTo(Integer.valueOf(id));
					rolePerMapper.updateByExampleSelective(srp, example);
				}
				else//角色不存在
					info.setResult(3);
			}
		}
		return info;
	}
	
	/**
	 * 查询全部角色
	 * 只查询角色Id和名称
	 */
	@Override
	public List<Map<String, Object>> members() {
		return roleMapper.members();
	}

	/**
	 * 更新用户角色
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
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
