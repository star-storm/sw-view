/**
 * 
 */
package com.taiji.admin.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.mapper.SLogMapper;
import com.taiji.admin.model.SLog;
import com.taiji.admin.model.SLogExample;
import com.taiji.admin.service.SLogService;

/**
 * 
 * teach-src com.taiji.admin.service.imp SLogServiceImp.java
 *
 * @author hsl
 *
 * 2018年4月19日 上午11:08:45
 *
 * desc:
 */
@Service
public class SLogServiceImp implements SLogService {
	
	@Autowired
	private SLogMapper logMapper;

	/**
	 * 计数
	 */
	@Override
	public long count(String modelId, String content, String userName, String roleName) {
		SLogExample example = new SLogExample();
		SLogExample.Criteria criteria = example.createCriteria();
//		System.out.println("com.taiji.admin.service.imp.SLogServiceImp: modelId = " + modelId + ", content = " + content + ", userName = " + userName);
		if (!StringUtils.isEmpty(modelId))
			criteria.andModelIdEqualTo(Integer.valueOf(modelId));
		if (!StringUtils.isEmpty(content))
			criteria.andOptionsLike("%" + content + "%");
		if (!StringUtils.isEmpty(userName))
			criteria.andUserNameLike("%" + userName + "%");
		criteria.andDelFlgEqualTo("0");
		criteria.andRoleManeger();
		return logMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
	@Override
	public List<SLog> logPage(PageInfo pageInfo, String modelId, String content, String userName, String roleName) {
		SLogExample example = new SLogExample();
		example.setFrom(pageInfo.getFrom());
		example.setSize(pageInfo.getSize());
		SLogExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(modelId))
			criteria.andModelIdEqualTo(Integer.valueOf(modelId));
		if (!StringUtils.isEmpty(content))
			criteria.andOptionsLike("%" + content + "%");
		if (!StringUtils.isEmpty(userName))
			criteria.andUserNameLike("%" + userName + "%");
		criteria.andDelFlgEqualTo("0");
		criteria.andRoleManeger();
		example.setOrderByClause(" option_date desc, id desc");
		return logMapper.selectByExample(example);
	}
	
	/**
	 * 详情
	 */
	@Override
	public SLog getLog(Integer id) {
		return logMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新：新建，编辑
	 */
	@Override
	public int addLog(String modelId, String userId, String options, String content, String result) {
		SLog log = new SLog();
		if (!StringUtils.isEmpty(modelId))
			log.setModelId(Integer.valueOf(modelId));
		if (!StringUtils.isEmpty(userId))
			log.setUserId(Integer.valueOf(userId));
		if (!StringUtils.isEmpty(options))
			log.setOptions(options);
		if (!StringUtils.isEmpty(content))
			log.setContent(content);
		if (!StringUtils.isEmpty(result))
			log.setResult(result);
		log.setDelFlg("0");
		log.setOptionDate(new Date());
		logMapper.insert(log);
		return 0;
	}
	
}
