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
import com.taiji.admin.mapper.SPermissionMapper;
import com.taiji.admin.model.SPermission;
import com.taiji.admin.model.SPermissionExample;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SPermissionService;

/**
 * 
 * teach-src-manage com.taiji.admin.service.imp SPermissionServiceImp.java
 *
 * @author hsl
 *
 * 2018年3月9日 下午1:44:28
 *
 * desc:
 */
@Service
public class SPermissionServiceImp implements SPermissionService {
	
	@Autowired
	private SPermissionMapper permissionMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String name, String desc) {
		SPermissionExample example = new SPermissionExample();
		SPermissionExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(desc))
			criteria.andDescLike("%" + desc + "%");
		criteria.andDelFlgEqualTo("0");
		return permissionMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
	@Override
	public List<SPermission> permissionPage(PageInfo pageInfo, String name, String descrip) {
		SPermissionExample example = new SPermissionExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		SPermissionExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(descrip))
			criteria.andDescLike("%" + descrip + "%");
		criteria.andDelFlgEqualTo("0");
		example.setOrderByClause(" (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc, id desc");
		return permissionMapper.selectByExample(example);
	}
	
	/**
	 * 基本信息
	 */
	@Override
	public SPermission getPermission(Integer id) {
		return permissionMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 角色的权限信息
	 */
	@Override
	public List<SPermission> selectByRole(Integer rid) {
		return permissionMapper.selectByRole(rid);
	}
	
	/**
	 * 查询全部权限
	 * 只查询权限Id和名称
	 */
	@Override
	public List<Map<String, Object>> members() {
		return permissionMapper.members();
	}

	/**
	 * 更新权限权限
	 */
	@Override
	public Integer upPermission(Integer id, String name, Integer type, String url, String method, String desc, SUser host) {
		if (StringUtils.isEmpty(name))//对象为空
			return 1;
		SPermission permission = new SPermission();
		permission.setName(name);
		if (!StringUtils.isEmpty(type))
			permission.setType(type);
		if (!StringUtils.isEmpty(url))
			permission.setUrl(url);
		if (!StringUtils.isEmpty(method))
			permission.setMethod(method);
		if (!StringUtils.isEmpty(desc))
			permission.setDescrip(desc);
		if (StringUtils.isEmpty(id)) {//新建
			permission.setCreateBy(host==null?null:host.getId());
			permission.setCreateDate(new Date());
			permissionMapper.insertSelective(permission);
		}
		else {//编辑
			SPermission tmp = permissionMapper.selectByPrimaryKey(Integer.valueOf(id));
			if (tmp != null) {
				permission.setId(tmp.getId());
				permission.setUpdateBy(host==null?null:host.getId());
				permission.setUpdateDate(new Date());
				permissionMapper.updateByPrimaryKeySelective(permission);
			}
			else
				return 2;//数据不存在
		}
		return 0;
	}
	
	/**
	 * 删除权限
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public LogMsgInfo delPermission(Integer id, SUser host) {
		LogMsgInfo info = new LogMsgInfo();
		info.setId(String.valueOf(id));
		info.setResult(0);
		if (id == null)//参数错误
			info.setResult(1);
		else {
			SPermission tmp = permissionMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				info.setName(tmp.getName());
				tmp.setDelFlg("1");//逻辑删除
				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				permissionMapper.updateByPrimaryKeySelective(tmp);
			}
			else//权限不存在
				info.setResult(3);
		}
		return info;
	}

	/**
	 * 批量删除权限
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
				SPermission tmp = permissionMapper.selectByPrimaryKey(Integer.valueOf(id));
				if (tmp != null) {
					sbf.append(tmp.getName() + ",");
					tmp.setDelFlg("1");//逻辑删除
					tmp.setUpdateBy(host.getId());
					tmp.setUpdateDate(new Date());
					permissionMapper.updateByPrimaryKeySelective(tmp);
				}
				else//权限不存在
					info.setResult(3);
			}
		}
		return info;
	}

}
