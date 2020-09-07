/**
 * 
 */
package com.taiji.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;  

/**
 * 
 * sw-view com.taiji.admin.config WebConfig.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:27:45
 *
 * desc: 配置静态资源访问路径
 */
@SuppressWarnings("deprecation")
@Configuration  
public class WebConfig extends WebMvcConfigurerAdapter { 

    @Value("${web.avatar-path}")
    public String avatarPath;
    @Value("${web.upload-path}")
    public String uploadPath;
  
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    	registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    	registry.addResourceHandler("/avatar/**").addResourceLocations("file:/"+avatarPath+"/");
    	registry.addResourceHandler("/upload/**").addResourceLocations("file:/"+uploadPath+"/");
    	super.addResourceHandlers(registry);
    } 
}  
