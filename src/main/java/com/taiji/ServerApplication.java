package com.taiji;

import javax.servlet.MultipartConfigElement;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.taiji.admin.config.BeanConfig;
import com.taiji.admin.utils.SpringUtil;

@EnableCaching
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class ServerApplication {
	
	private static Logger log = LoggerFactory.getLogger(ServerApplication.class);
	
	@Bean
    public static SpringUtil getSpringUtil() {
        return new SpringUtil();
    }
	  
    /**  
     * 文件上传的大小配置  
     */  
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //文件最大  
        factory.setMaxFileSize("2048000000KB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("20480000000KB");  
        return factory.createMultipartConfig();  
    }
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		System.out.println("Server start running...");
		log.info("Server start running......");
		SpringApplication.run(ServerApplication.class, args);
		log.info("Server end running...");
		System.out.println("Server get ready...");
		
//		String localIP = "127.0.0.1";
		String localIP = ((BeanConfig)getSpringUtil().getBean("beanIns")).localIP;
		String localPort = ((BeanConfig)getSpringUtil().getBean("beanIns")).localPort;
		
		//ssoWebservice
        String ssoAddr = "http://" + localIP + ":" + localPort + "/services/ssoWebservice";  
        Endpoint.publish(ssoAddr, getSpringUtil().getBean("ssoServiceImp"));  
        System.out.println("发布webservice成功!service-addr: " + ssoAddr + "?wsdl");
		
		//单点服务
        String singleLoginAddr = "http://" + localIP + ":" + localPort + "/services/singleLoginService";  
        Endpoint.publish(singleLoginAddr, getSpringUtil().getBean("singleLoginServiceImp"));  
        System.out.println("发布singleLoginService成功!service-addr: " + singleLoginAddr + "?wsdl");  
        
        //同步服务
        String synInfoAddr = "http://" + localIP + ":" + localPort + "/services/synInfoService";  
        Endpoint.publish(synInfoAddr, getSpringUtil().getBean("synInfoServiceImp"));  
        System.out.println("发布synInfoService成功!service-addr: " + synInfoAddr + "?wsdl");  
	}
}
