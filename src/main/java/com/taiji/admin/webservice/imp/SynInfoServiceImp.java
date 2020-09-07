package com.taiji.admin.webservice.imp;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.taiji.admin.config.BeanConfig;
import com.taiji.admin.mapper.SOrgMapper;
import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.model.SOrg;
import com.taiji.admin.model.SUser;
import com.taiji.admin.webservice.SynInfoService;

@WebService(targetNamespace="http://service.auth.taiji.com",name="synInfo",serviceName="synInfoService")  
public class SynInfoServiceImp implements SynInfoService {
	
	private static final Logger log = LoggerFactory.getLogger(SynInfoServiceImp.class);
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SOrgMapper orgMapper;
	
	@Autowired
	private SUserMapper userMapper;
	
	@Autowired
	BeanConfig beanIns;
	
    @Override  
    public String test(String param) {  
        return "param=" + param;  
    }

    /**
     * 组织信息
     */
	@Override
	public String getDeptInfoByXml(String deptCode) {
		log.info("deptCode=["+deptCode+"]");
		SOrg org = orgMapper.selectByCode(deptCode);
		String result = "";
		if (org != null) {
			result = orgToXml(org);
		}
		log.info("result=["+result+"]");
		return result;
	}
    
    //组织XML信息
    private String orgToXml(SOrg org) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("<userinfo>");
		sbf.append("<xml>");
		sbf.append("<orgCode>");
		sbf.append(org.getCode());
		sbf.append("</orgCode>");
		sbf.append("<parentCode>");
		sbf.append(org.getParent().getCode());
		sbf.append("</parentCode>");
		sbf.append("<name>");
		sbf.append(org.getName());
		sbf.append("</name>");
		sbf.append("</xml>");
		sbf.append("</userinfo>");
		return sbf.toString();
	}

	/**
	 * 用户信息
	 */
	@Override
	public String getUserInfoByXml(String userCode) {
		log.info("userCode=["+userCode+"]");
		SUser user = userMapper.selectByUserCode(userCode);
		String result = "";
		if (user != null) {
			result = userToXml(user);
		}
		log.info("result=["+result+"]");
		return result;
	}
    
    //用户XML信息
    private String userToXml(SUser user) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("<userinfo>");
		sbf.append("<xml>");
		sbf.append("<userCode>");
		sbf.append(user.getUserCode());
		sbf.append("</userCode>");
		sbf.append("<name>");
		sbf.append(user.getName());
		sbf.append("</name>");
		sbf.append("<orgCode>");
		sbf.append(user.getOrgCode());
		sbf.append("</orgCode>");
		sbf.append("<orgName>");
		sbf.append(user.getOrgName());
		sbf.append("</orgName>");
		sbf.append("</xml>");
		sbf.append("</userinfo>");
		return sbf.toString();
	}

}
