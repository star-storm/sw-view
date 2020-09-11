/**
 * 
 */
package com.taiji.app.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.mapper.SPermissionMapper;
import com.taiji.admin.mapper.TAppInfoMapper;
import com.taiji.admin.model.SPermission;
import com.taiji.admin.model.SUser;
import com.taiji.app.model.TAppInfo;
import com.taiji.app.model.TAppInfoExample;
import com.taiji.app.service.TAppInfoService;

/**
 * 
 * sw-view com.taiji.app.service.imp TAppInfoServiceImp.java
 *
 * @author hsl
 *
 * 2020年5月9日 下午4:40:27
 *
 * desc:
 */
@Service
public class TAppInfoServiceImp implements TAppInfoService {
	
	@Autowired
	private TAppInfoMapper appInfoMapper;
	
	@Autowired
	private SPermissionMapper permissionMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String name, SUser host) {
		TAppInfoExample example = new TAppInfoExample();
		TAppInfoExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		criteria.andCreateByEqualTo(host.getId());
		criteria.andDelFlgEqualTo("0");
		return appInfoMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
	@Override
	public List<TAppInfo> appInfoPage(PageInfo pageInfo, String name, SUser host) {
		TAppInfoExample example = new TAppInfoExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		TAppInfoExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		criteria.andCreateByEqualTo(host.getId());
		criteria.andDelFlgEqualTo("0");
		example.setOrderByClause(" (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc, id desc");
		return appInfoMapper.selectByExample(example);
	}
	
	/**
	 * 基本信息
	 */
	@Override
	public TAppInfo getAppInfo(Integer id) {
		return appInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询全部
	 */
	@Override
	public List<TAppInfo> members() {
		return appInfoMapper.members();
	}

	/**
	 * 更新：新建，编辑
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public String updateAppInfo(Integer id, String name, String code, String url, String owner, String pid, SUser host) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(code) || (StringUtils.isEmpty(url) && StringUtils.isEmpty(pid)))
			return "参数错误；必要数据为空";
		TAppInfo appInfo = new TAppInfo();
		appInfo.setName(name);
		appInfo.setCode(code);
		appInfo.setUrl(url);
		appInfo.setOwner(owner);
		appInfo.setPid(Integer.valueOf(pid));
		appInfo.setCreateBy(host.getId());
		appInfo.setCreateDate(new Date());
		appInfo.setDelFlg("0");
		if (id == null) {//新建
			appInfoMapper.insertSelective(appInfo);
			Integer gid = appInfo.getId();
			if (gid == null)
				return "保存应用失败";

			//添加专题权限
			SPermission permission = new SPermission();
			permission.setName(code);
			permission.setDescrip(name);
			permission.setType(Integer.valueOf(pid));
			permission.setCreateBy(host==null?null:host.getId());
			permission.setCreateDate(new Date());
			permissionMapper.insertSelective(permission);
		}
		else {//编辑
			TAppInfo tmp = appInfoMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				//更新
				appInfo.setId(id);
				appInfo.setUpdateBy(host.getId());
				appInfo.setUpdateDate(new Date());
				appInfoMapper.updateByPrimaryKey(appInfo);
			}
			else
				return "未找到要更新的数据";
		}
		return "success";
	}
	
	/**
	 * 删除
	 */
	@Override
	public boolean delAppInfo(Integer id, SUser host) {
		if (id == null)
			return false;
		else {
			TAppInfo tmp = appInfoMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				//逻辑删除
				tmp.setDelFlg("1");
				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				appInfoMapper.updateByPrimaryKeySelective(tmp);
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
				TAppInfo tmp = appInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
				if (tmp != null) {
					sbf.append(tmp.getName() + ",");
					tmp.setDelFlg("1");//逻辑删除
					tmp.setUpdateBy(host.getId());
					tmp.setUpdateDate(new Date());
					appInfoMapper.updateByPrimaryKeySelective(tmp);
				}
				else//不存在
					info.setResult(3);
			}
		}
		return info;
	}

}
