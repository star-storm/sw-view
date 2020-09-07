package com.taiji.admin.webservice.imp;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.taiji.admin.config.BeanConfig;
import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.model.SUser;
import com.taiji.admin.utils.encrypt.Encrypt;
import com.taiji.admin.webservice.SingleLoginService;

@WebService(targetNamespace="http://service.auth.taiji.com",name="singleLogin",serviceName="singleLoginService")  
public class SingleLoginServiceImp implements SingleLoginService {
	
	private static final Logger log = LoggerFactory.getLogger(SingleLoginServiceImp.class);
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SUserMapper userMapper;
	
	@Autowired
	BeanConfig beanIns;
	
    @Override  
    public String test(String param) {  
        return "param=" + param;  
    } 
    
    /**
     * 根据票据返回用户信息
     */
    @Override  
    public String getTicketByXml(String ticket) {
		log.info("ticket=["+ticket+"]");
		String userCode =Encrypt.ssoEncrypt(ticket, false);
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
