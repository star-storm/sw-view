/**
 * 
 */
package com.taiji.admin.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.mapper.SOrgMapper;
import com.taiji.admin.model.SOrg;
import com.taiji.admin.model.SOrgExample;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SOrgService;
import com.taiji.admin.utils.CommonUtil;

/**
 * 
 * train-portal com.taiji.admin.service.imp SOrgServiceImp.java
 *
 * @author hsl
 *
 * 2018年8月5日 下午5:47:38
 *
 * desc: 组织管理
 */
@Service
public class SOrgServiceImp implements SOrgService {
	
	@Autowired
	private SOrgMapper orgMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String id, String name, String code) {
		SOrgExample example = new SOrgExample();
		SOrgExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(id))
			criteria.andPIdEqualTo(Integer.valueOf(id));
//			criteria.andIdIn(unionId(id));
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(code))
			criteria.andCodeLike("%" + code + "%");
		criteria.andDelFlgEqualTo("0");
		return orgMapper.countByExample(example);
	}
	
	//查询子节点，转成list
	@SuppressWarnings("unused")
	private List<Integer> unionId(String id) {
		List<Integer> list = new ArrayList<Integer>();
		String s = orgMapper.orgChildren(id.toString());
		for (String p : s.split(",")) {
			if (StringUtils.isEmpty(p))
				continue;
			list.add(Integer.valueOf(p));
		}
		return list;
	}

	/**
	 * 列表
	 */
	@Override
	public List<SOrg> orgPage(PageInfo pageInfo, String id, String name, String code) {
		SOrgExample example = new SOrgExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		SOrgExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(id))
			criteria.andPIdEqualTo(Integer.valueOf(id));
		if (!StringUtils.isEmpty(name))
			criteria.andNameLike("%" + name + "%");
		if (!StringUtils.isEmpty(code))
			criteria.andCodeLike("%" + code + "%");
		criteria.andDelFlgEqualTo("0");
		example.setOrderByClause(" sort asc, id desc, (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc ");
		return orgMapper.selectByExample(example);
	}
	
	/**
	 * 组织基本信息
	 */
	@Override
	public SOrg getOrg(Integer id) {
		return orgMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 组织基本信息
	 */
	@Override
	public SOrg selectByCode(String code) {
		return orgMapper.selectByCode(code);
	}
	
	/**
	 * 更新组织：新建，编辑
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public int updateOrg(SOrg org, SUser host) {
		if (org == null)//对象为空
			return 1;
		if (StringUtils.isEmpty(org.getId())) {//新建
			org.setCreateBy(host==null?null:host.getId());
			org.setCreateDate(new Date());
			org.setDelFlg("0");
			orgMapper.insertSelective(org);
		}
		else {//编辑
			if (checkPCode(org.getCode(), org.getPCode()))
				return 3;
			SOrg tmp = orgMapper.selectByPrimaryKey(org.getId());
			if (tmp != null) {
				org.setUpdateBy(host==null?null:host.getId());
				org.setUpdateDate(new Date());
				orgMapper.updateByPrimaryKeySelective(org);
			}
			else
				return 2;//数据不存在
		}
		return 0;
	}
	
	//校验组织父ID的合法性
	private boolean checkPCode(String code, String pCode) {
		return false;
//		String s = orgMapper.orgChildren(code);
//		boolean exist = false;
//		for (String p : s.split(",")) {
//			if (StringUtils.isEmpty(p))
//				continue;
//			if (pCode.equals(p)) {
//				exist = true;
//				break;
//			}
//		}
//		return exist;
	}
	
	/**
	 * 删除组织
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public int deleteByCode(String code, SUser host) {
		if (code == null)//参数错误
			return 1;
		else {
			//检测是否有该数据
			SOrgExample example = new SOrgExample();
			SOrgExample.Criteria criteria = example.createCriteria();
			criteria.andCodeEqualTo(code);
			long count = orgMapper.countByExample(example);
			if (count == 0)//数据不存在
				return 2;
			SOrg tmp = orgMapper.selectByCode(code);
			if (tmp != null) {
				tmp.setDelFlg("1");//逻辑删除
//				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				orgMapper.updateByPrimaryKeySelective(tmp);
			}
			else//数据不存在
				return 3;
			
			//删除子类
			String s = orgMapper.orgChildren(String.valueOf(code));
			for (String p : s.split(",")) {
				if (StringUtils.isEmpty(p))
					continue;
				SOrg sub = orgMapper.selectByCode(code);
				if (sub != null) {
					sub.setDelFlg("1");//逻辑删除
//					tmp.setUpdateBy(host.getId());
					sub.setUpdateDate(new Date());
					orgMapper.updateByPrimaryKeySelective(sub);
				}
			}
		}
		return 0;
	}
	
	/**
	 * 删除组织
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public int delOrg(Integer id, SUser host) {
		if (id == null)//参数错误
			return 1;
		else {
			//检测是否有该数据
			SOrgExample example = new SOrgExample();
			SOrgExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(Integer.valueOf(id));
			long count = orgMapper.countByExample(example);
			if (count == 0)//数据不存在
				return 2;
			SOrg tmp = orgMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				tmp.setDelFlg("1");//逻辑删除
				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				orgMapper.updateByPrimaryKeySelective(tmp);
			}
			else//数据不存在
				return 3;
			
			//删除子类
			String s = orgMapper.orgChildren(id.toString());
			for (String p : s.split(",")) {
				if (StringUtils.isEmpty(p))
					continue;
				SOrg sub = orgMapper.selectByPrimaryKey(Integer.valueOf(p));
				if (sub != null) {
					sub.setDelFlg("1");//逻辑删除
					tmp.setUpdateBy(host.getId());
					sub.setUpdateDate(new Date());
					orgMapper.updateByPrimaryKeySelective(sub);
				}
			}
		}
		return 0;
	}

	/**
	 * 批量删除组织
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public int delBatch(String ids, SUser host) throws RuntimeException {
		if (StringUtils.isEmpty(ids))
			return 1;
		
		HashSet<String> set = new HashSet<String>();
		for (String id : ids.split(",")) {
			if (StringUtils.isEmpty(id))
				continue;
			set.add(id);
			
			String s = orgMapper.orgChildren(id.toString());
			for (String p : s.split(",")) {
				if (StringUtils.isEmpty(p))
					continue;
				set.add(p);
			}
		}
		System.out.println(CommonUtil.setToString(set));
		
		for (String id : set) {
			if (id == null)//参数错误
				return 1;
			else {
				//检测是否有该数据
				SOrgExample example = new SOrgExample();
				SOrgExample.Criteria criteria = example.createCriteria();
				criteria.andIdEqualTo(Integer.valueOf(id));
				long count = orgMapper.countByExample(example);
				if (count == 0)//数据不存在
					return 2;
				SOrg tmp = orgMapper.selectByPrimaryKey(Integer.valueOf(id));
				if (tmp != null) {
					tmp.setDelFlg("1");//逻辑删除
					tmp.setUpdateBy(host.getId());
					tmp.setUpdateDate(new Date());
					orgMapper.updateByPrimaryKeySelective(tmp);
				}
				else//数据不存在
					return 3;
			}
		}
		return 0;
	}
	
	/**
	 * 查询全部组织
	 */
	@Override
	public List<SOrg> subOrgs(String pid) {
		if (!StringUtils.isEmpty(pid))
			pid = Constant.ORG_ROOT_PARENT;
		return orgMapper.subOrgs(pid);
	}
	
	/**
	 * 条件查询组织
	 */
	@Override
	public List<SOrg> queryOrgs(String name) {
		SOrgExample example = new SOrgExample();
		SOrgExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			criteria.andIdIn(unionPid(name));
		criteria.andDelFlgEqualTo("0");
		example.setOrderByClause(" sort asc, id desc, (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc ");
		return orgMapper.selectByExample(example);
	}
	
	//查询父节点，转成list
	private List<Integer> unionPid(String name) {
		List<Integer> list = new ArrayList<Integer>();
		String ids = "";
		String s = orgMapper.queryOrgsByName(name);
		if (!StringUtils.isEmpty(s)) {
			for (String p : s.split(",")) {
				if (!StringUtils.isEmpty(p)) {
					ids += orgMapper.orgParents(p);
					System.out.println("ids=" + ids);
				}
			}
			for (String p : ids.split(",")) {
				if (!StringUtils.isEmpty(p)) {
					if (StringUtils.isEmpty(p))
						continue;
					list.add(Integer.valueOf(p));
				}
			}
		}
		else
			list.add(null);
		return list;
	}
	
	/**
	 * 根据id查询全部子类
	 */
	@Override
	public String orgChildren(String p) {
		return orgMapper.orgChildren(p);
	}
	
	/**
	 * 根据id查询全部父类
	 */
	@Override
	public String orgParents(String p) {
		return orgMapper.orgParents(p);
	}

	/**
	 * 根据id查询最高级父类
	 */
	@Override
	public String orgParent(String p) {
		return orgMapper.orgParent(p);
	}

	/**
	 * 根据名称查询下级组织和该组织下用户
	 */
	public List<Map<String, Object>> nextLevel(String name) {
		return orgMapper.nextLevel(name);
	}

}
