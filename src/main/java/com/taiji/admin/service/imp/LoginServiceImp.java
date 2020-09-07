/**
 * 
 */
package com.taiji.admin.service.imp;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taiji.admin.constant.Constant;
import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.model.SUser;
import com.taiji.admin.model.SUserExample;
import com.taiji.admin.service.LoginService;
import com.taiji.admin.utils.encrypt.Encrypt;

/**
 * 
 * sw-view com.taiji.admin.service.imp LoginServiceImp.java
 *
 * @author hsl
 *
 * 2019年12月1日 下午2:24:08
 *
 * desc:
 */
@Service
public class LoginServiceImp implements LoginService {
	
	@Autowired
	private SUserMapper userMapper;
	
	public int singleLogin(HttpSession session, String authTicket) {
		String userCode = Encrypt.ssoEncrypt(authTicket, false);
		SUser user = userMapper.selectByUserCode(userCode);
		if (user == null)
			return 2;
		session.setAttribute(Constant.SESSION_USER_KEY, user);
		System.out.println("单点认证完成："+user.getName());
		return 0;
	}
	
	public int verifyUser(HttpSession session, String loginName) {
		SUserExample example = new SUserExample();
		SUserExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isEmpty(loginName))
			return 1;
		criteria.andLoginNameEqualTo(loginName);
//		if (StringUtils.isEmpty(password))
//			return 2;
//		criteria.andUserPwdEqualTo(password);
		criteria.andDelFlgEqualTo("0");
		long count = userMapper.countByExample(example);
		if (count == 0)
			return 3;
		SUser user = userMapper.selectUserByLoginName(loginName);
		if (user == null)
			return 4;
		session.setAttribute(Constant.SESSION_USER_KEY, user);
		return 0;
	}
	
	public int verifyUser(HttpSession session, String loginName, String password) {
		SUserExample example = new SUserExample();
		SUserExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isEmpty(loginName))
			return 1;
		criteria.andLoginNameEqualTo(loginName);
		SUser user = userMapper.selectUserByLoginName(loginName);
		if (user == null)
			return 4;
		if (StringUtils.isEmpty(password))
			return 2;
		criteria.andUserPwdEqualTo(password.equals("0")?password:Encrypt.encryptString(password));
		criteria.andDelFlgEqualTo("0");
		long count = userMapper.countByExample(example);
		if (count == 0)
			return 3;
		session.setAttribute(Constant.SESSION_USER_KEY, user);
		return 0;
	}
	
	public int verifyUserByUserCode(HttpSession session, String userCode) {
		SUserExample example = new SUserExample();
		SUserExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isEmpty(userCode))
			return 1;
		criteria.andUserCodeEqualTo(userCode);
//		if (StringUtils.isEmpty(password))
//			return 2;
//		criteria.andUserPwdEqualTo(password);
		criteria.andDelFlgEqualTo("0");
		long count = userMapper.countByExample(example);
		if (count == 0)
			return 3;
		SUser user = userMapper.selectByUserCode(userCode);
		if (user == null)
			return 4;
		session.setAttribute(Constant.SESSION_USER_KEY, user);
		return 0;
	}
	
	public int checkPwd(String loginName, String password) {
		SUserExample example = new SUserExample();
		SUserExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isEmpty(loginName))
			return -1;
		criteria.andLoginNameEqualTo(loginName);
		if (StringUtils.isEmpty(password))
			return -1;
		criteria.andUserPwdEqualTo(password.equals("0")?password:Encrypt.encryptString(password));
		criteria.andDelFlgEqualTo("0");
		long count = userMapper.countByExample(example);
		if (count == 0)
			return -1;
		SUser user = userMapper.selectUserByLoginName(loginName);
		if (user == null)
			return -1;
		return 0;
	}
	
	public int changePwd(HttpSession session, String password) {
		SUser user = (SUser) session.getAttribute(Constant.SESSION_USER_KEY);
		if (user == null)
			return -1;
		user.setUserPwd(password.equals("0")?password:Encrypt.encryptString(password));
		user.setUpdateBy(user.getId());
		user.setUpdateDate(new Date());
		userMapper.updateByPrimaryKeySelective(user);
		session.setAttribute(Constant.SESSION_USER_KEY, user);
		return 0;
	}

}
