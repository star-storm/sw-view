/**
 * 
 */
package com.taiji.msg.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.mapper.TGroupMapper;
import com.taiji.admin.model.SUser;
import com.taiji.msg.model.TGroup;
import com.taiji.msg.model.TGroupExample;
import com.taiji.msg.service.TGroupService;

/**
 * 
 * sw-view com.taiji.admin.service.imp TGroupServiceImp.java
 *
 * @author hsl
 *
 * 2019年12月14日 下午5:42:46
 *
 * desc:
 */
@Service
public class TGroupServiceImp implements TGroupService {
	
	@Autowired
	private TGroupMapper groupMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String name, SUser host) {
		TGroupExample example = new TGroupExample();
		TGroupExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		criteria.andCreateByEqualTo(host.getId());
		criteria.andDelFlgEqualTo("0");
		return groupMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
	@Override
	public List<TGroup> groupPage(PageInfo pageInfo, String name, SUser host) {
		TGroupExample example = new TGroupExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		TGroupExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		criteria.andCreateByEqualTo(host.getId());
		criteria.andDelFlgEqualTo("0");
		example.setOrderByClause(" (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc, id desc");
		return groupMapper.selectByExample(example);
	}
	
	/**
	 * 用户基本信息
	 */
	@Override
	public TGroup getGroup(Integer id) {
		return groupMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新用户：新建，编辑
	 */
	@Override
	@Transactional(transactionManager="msgTransactionManager")
	public String updateGroup(Integer id, String toOrgIds, String toOrgNames, 
			String toUserIds, String toUserNames, String toUserPhones, String name, String ids, SUser host) {
		if (StringUtils.isEmpty(name) || (StringUtils.isEmpty(toUserIds) && StringUtils.isEmpty(ids)))
			return "参数错误；必要数据为空";
		TGroup group = new TGroup();
		group.setName(name);
		group.setCreateBy(host.getId());
		group.setCreateDate(new Date());
		group.setDelFlg("0");
		if (id == null) {//新建
			groupMapper.insertSelective(group);
			Integer gid = group.getId();
			if (gid == null)
				return "保存群组失败";
		}
		else {//编辑
			TGroup tmp = groupMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				//更新群组信息
				group.setId(id);
				group.setUpdateBy(host.getId());
				group.setUpdateDate(new Date());
				groupMapper.updateByPrimaryKey(group);
			}
			else
				return "未找到要更新的数据";
		}
		return "success";
	}
	
	/**
	 * 删除用户
	 */
	@Override
	public boolean delGroup(Integer id, SUser host) {
		if (id == null)
			return false;
		else {
			TGroup tmp = groupMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				//删除群组
				groupMapper.deleteByPrimaryKey(id);
			}
			else
				return false;
		}
		return true;
	}

}
