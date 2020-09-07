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
import com.taiji.admin.mapper.ODictMapper;
import com.taiji.admin.model.ODict;
import com.taiji.admin.model.ODictExample;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.DictService;

/**
 * 
 * teach-src com.taiji.admin.service.imp DictServiceImp.java
 *
 * @author hsl
 *
 * 2018年3月19日 上午11:31:52
 *
 * desc: 数据字典
 */
@Service
public class DictServiceImp implements DictService {
	
	@Autowired
	private ODictMapper dictMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String descrip, String value, String type) {
		ODictExample example = new ODictExample();
		ODictExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(descrip))
			criteria.andDescripLike("%" + descrip + "%");
		if (!StringUtils.isEmpty(value))
			criteria.andValueEqualTo(value);
		if (!StringUtils.isEmpty(type))
			criteria.andTypeEqualTo(type);
		criteria.andDelFlgEqualTo("0");//删除标识
		return dictMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
	@Override
	public List<ODict> dictPage(PageInfo pageInfo, String descrip, String value, String type) {
		ODictExample example = new ODictExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		ODictExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(descrip))
			criteria.andDescripLike("%" + descrip + "%");
		if (!StringUtils.isEmpty(value))
			criteria.andValueEqualTo(value);
		if (!StringUtils.isEmpty(type))
			criteria.andTypeEqualTo(type);
		criteria.andDelFlgEqualTo("0");//删除标识
		example.setOrderByClause(" (CASE WHEN update_date IS NULL THEN create_date ELSE update_date END) desc, id desc");
		return dictMapper.selectByExample(example);
	}

	/**
	 * 组信息
	 */
	@Override
	public List<ODict> dictList(String type) {
		ODictExample example = new ODictExample();
		example.setOrderByClause(" type, code, sort ");
		ODictExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(type))
			criteria.andTypeEqualTo(type);
		criteria.andDelFlgEqualTo("0");
		return dictMapper.selectByExample(example);
	}
	
	/**
	 * 详情
	 */
	@Override
	public ODict getDict(Integer id) {
		return dictMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 更新：新建，编辑
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public int updateDict(Integer id, String code, String value, String type, String descrip, SUser host) {
		if (StringUtils.isEmpty(descrip) || StringUtils.isEmpty(value) || StringUtils.isEmpty(type))//参数错误
			return 1;
		ODict dict = new ODict();
		dict.setCode(code);
		dict.setValue(value);
		dict.setType(type);
		dict.setDescrip(descrip);
		
		if (StringUtils.isEmpty(id)) {//新建
			dict.setCreateBy(host.getId());
			dict.setCreateDate(new Date());
			dict.setDelFlg("0");
			dictMapper.insertSelective(dict);
		}
		else {//编辑
			ODict tmp = dictMapper.selectByPrimaryKey(Integer.valueOf(id));
			if (tmp != null) {
				dict.setId(Integer.valueOf(id));
				dict.setCreateBy(tmp.getCreateBy());
				dict.setCreateDate(tmp.getCreateDate());
				dict.setDelFlg(tmp.getDelFlg());
				dict.setRemark(tmp.getRemark());
				dict.setUpdateBy(host.getId());
				dict.setUpdateDate(new Date());
				dictMapper.updateByPrimaryKey(dict);
			}
			else
				return 2;//数据不存在
		}
		return 0;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(transactionManager="adminTransactionManager")
	public LogMsgInfo delDict(Integer id, SUser host) {
		LogMsgInfo info = new LogMsgInfo();
		info.setId(String.valueOf(id));
		info.setResult(0);
		if (id == null)//参数错误
			info.setResult(1);
		else {
			//检测是否有该数据
			ODictExample example = new ODictExample();
			ODictExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(Integer.valueOf(id));
			long count = dictMapper.countByExample(example);
			if (count == 0)//数据不存在
				info.setResult(2);
			ODict tmp = dictMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				info.setName(tmp.getValue());
				tmp.setDelFlg("1");//逻辑删除
				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				dictMapper.updateByPrimaryKeySelective(tmp);
			}
			else//数据不存在
				info.setResult(3);
		}
		return info;
	}

	/**
	 * 批量删除
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
				ODictExample example = new ODictExample();
				ODictExample.Criteria criteria = example.createCriteria();
				criteria.andIdEqualTo(Integer.valueOf(id));
				long count = dictMapper.countByExample(example);
				if (count == 0)//数据不存在
					info.setResult(2);
				ODict tmp = dictMapper.selectByPrimaryKey(Integer.valueOf(id));
				if (tmp != null) {
					sbf.append(tmp.getValue() + ",");
					tmp.setDelFlg("1");//逻辑删除
					tmp.setUpdateBy(host.getId());
					tmp.setUpdateDate(new Date());
					dictMapper.updateByPrimaryKeySelective(tmp);
				}
				else//数据不存在
					info.setResult(3);
			}
		}
		info.setName(sbf.toString());
		return info;
	}

}
