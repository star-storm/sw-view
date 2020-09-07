/**
 * 
 */
package com.taiji.admin.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taiji.admin.filter.RequestFilter;

/**
 * teach-src com.taiji.admin.config FilterConfig.java
 *
 * @author hsl
 *
 * 2018年4月8日 下午1:21:59
 *
 * desc:
 */
@Configuration
public class FilterConfig {

	/**
     * 配置过滤器
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(requestFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("requestFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "requestFilter")
    public Filter requestFilter() {
        return new RequestFilter();
    }

}
