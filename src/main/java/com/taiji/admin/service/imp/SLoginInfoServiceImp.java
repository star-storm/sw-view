/**
 * 
 */
package com.taiji.admin.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.admin.mapper.SLoginInfoMapper;
import com.taiji.admin.model.SLoginInfo;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SLoginInfoService;

/**
 * 
 * teach-src com.taiji.admin.service.imp SLoginInfoServiceImp.java
 *
 * @author hsl
 *
 * 2018年1月25日 上午11:45:43
 *
 * desc:
 */
@Service
public class SLoginInfoServiceImp implements SLoginInfoService {
	
	@Autowired
	private SLoginInfoMapper loginInfoMapper;
	
	/**
	 * 用户基本信息
	 */
	@Override
	public SLoginInfo getLoginInfo(Integer userId) {
		return loginInfoMapper.selectByPrimaryKey(userId);
	}
	@Override
	public SLoginInfo getLoginInfoByName(String loginName) {
		return loginInfoMapper.selectByLoginName(loginName);
	}
	
	/**
	 * 更新用户：新建，编辑
	 */
	@Override
	public Integer updateLoginInfo(SLoginInfo loginInfo, SUser host) {
		Integer id = loginInfo.getUserId();
		if (id == null) {//新建
			loginInfo.setUserId(host.getId());
			loginInfo.setCreateBy(host.getId());
			loginInfo.setCreateDate(new Date());
			loginInfo.setDelFlg("0");
			loginInfoMapper.insertSelective(loginInfo);
			id = loginInfo.getUserId();
		}
		else {//编辑
			SLoginInfo tmp = loginInfoMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				loginInfo.setUpdateBy(host.getId());
				loginInfo.setUpdateDate(new Date());
				loginInfoMapper.updateByPrimaryKeySelective(loginInfo);
			}
			else
				return -1;
		}
		return id;
	}

}
