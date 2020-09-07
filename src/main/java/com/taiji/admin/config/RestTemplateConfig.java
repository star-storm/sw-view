package com.taiji.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * sw-view com.taiji.admin.config RestTemplateConfig.java
 *
 * @author hsl
 *
 * 2020年4月22日 下午5:17:11
 *
 * desc: 远程调用rest接口客户端注册
 */
@Configuration
public class RestTemplateConfig {
	
	// 连接超时时间
	@Value("${rest.connection.timeout}")
	private Integer connectionTimeout;
	// 信息读取超时时间
	@Value("${rest.read.timeout}")
	private Integer readTimeout;

	/**
	 * 功能描述：注册restTemplate服务
	 **/
	@Bean
	public RestTemplate registerTemplate() {
		RestTemplate restTemplate = new RestTemplate(getFactory());
		// 这个地方需要配置消息转换器，不然收到消息后转换会出现异常
//		restTemplate.setMessageConverters(getConverts());
		System.out.println("restTemplate init...");
		return restTemplate;
	}

	/**
	 * 功能描述： 初始化请求工厂
	 **/
	private SimpleClientHttpRequestFactory getFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(connectionTimeout);
		factory.setReadTimeout(readTimeout);
		return factory;
	}

	/**
	 * 功能描述： 设置数据转换器
	 **/
	@SuppressWarnings("unused")
	private List<HttpMessageConverter<?>> getConverts() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		// String转换器
		StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
		List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {
			private static final long serialVersionUID = 4626220197157847063L;
			{
				// 配置text/plain和text/html类型的数据都转成String
				add(new MediaType("text", "plain", Charset.forName("UTF-8")));
				add(MediaType.TEXT_HTML);
			}
		};
		stringConvert.setSupportedMediaTypes(stringMediaTypes);
		messageConverters.add(stringConvert);
		return messageConverters;
	}
}