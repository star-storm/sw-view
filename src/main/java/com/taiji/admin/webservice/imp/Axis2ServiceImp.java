package com.taiji.admin.webservice.imp;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.taiji.admin.config.BeanConfig;
import com.taiji.admin.webservice.Axis2Service;

@WebService(targetNamespace="http://axis2/services",name="axis2",serviceName="axis2Webservice")  
public class Axis2ServiceImp implements Axis2Service {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	BeanConfig beanIns;
	
    @Override  
    public String yuan(String param) {  
        return "param=" + param;  
    } 

}
