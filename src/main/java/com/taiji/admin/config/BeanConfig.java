/**
 * 
 */
package com.taiji.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taiji.admin.utils.LogUtil;
import com.taiji.admin.utils.RedisUtil;
import com.taiji.admin.webservice.Axis2Service;
import com.taiji.admin.webservice.SingleLoginService;
import com.taiji.admin.webservice.SsoService;
import com.taiji.admin.webservice.SynInfoService;
import com.taiji.admin.webservice.imp.Axis2ServiceImp;
import com.taiji.admin.webservice.imp.SingleLoginServiceImp;
import com.taiji.admin.webservice.imp.SsoServiceImp;
import com.taiji.admin.webservice.imp.SynInfoServiceImp;

/**
 * 
 * sw-view com.taiji.admin.config BeanConfig.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:01:45
 *
 * desc:
 */
@Configuration
public class BeanConfig {

	@Value("${web.upload-path}")
    public String uploadPath;

    @Value("${admin.datasource.username}")
    public String dbUname;
    
    @Value("${admin.datasource.password}")
    public String dbPwd;
    
    @Value("${admin.datasource.name}")
    public String dbIns;
    
    @Value("${webservice.local-ip}")
    public String localIP;
    
    @Value("${webservice.local-port}")
    public String localPort;
    
    @Value("${webservice.zcpt-syn-url}")
    public String zcptSynUrl;
    
    @Value("${webservice.zcpt-syn-qname}")
    public String zcptSynQname;
    
    @Value("${webservice.zcpt-login-url}")
    public String zcptLoginUrl;
    
    @Value("${webservice.zcpt-login-qname}")
    public String zcptLoginQname;
    
	@Bean(name = "beanIns")
	public BeanConfig getBeanIns() {
		BeanConfig bean = new BeanConfig();
		return bean;
	}
	
	@Bean(name = "redisUtil")
	public RedisUtil getRedisUtil() {
		RedisUtil redisUtil = new RedisUtil();
		return redisUtil;
	}
	
	@Bean(name = "logUtil")
	public LogUtil getLogUtil() {
		LogUtil logUtil = new LogUtil();
		return logUtil;
	}
	
	@Bean(name = "axis2ServiceImp")
	public Axis2Service getAxis2Service() {
		Axis2Service axis2Service = new Axis2ServiceImp();
		return axis2Service;
	}
	
	@Bean(name = "ssoServiceImp")
	public SsoService getSsoServiceImp() {
		SsoService ssoService = new SsoServiceImp();
		return ssoService;
	}
	
	@Bean(name = "singleLoginServiceImp")
	public SingleLoginService getSingleLoginService() {
		SingleLoginService singleLoginServiceImp = new SingleLoginServiceImp();
		return singleLoginServiceImp;
	}
	
	@Bean(name = "synInfoServiceImp")
	public SynInfoService getSynInfoService() {
		SynInfoService synInfoServiceImp = new SynInfoServiceImp();
		return synInfoServiceImp;
	}
	
}
