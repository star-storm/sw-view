/**
 * 
 */
package com.taiji.admin.utils.http;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.taiji.admin.model.SUser;
import com.taiji.admin.utils.JsonObjectUtil;

import net.sf.json.JSONObject;

/**
 * sw-view com.taiji.admin.utils.http RestTemplateUtil.java
 *
 * @author hsl
 *
 * @2020年4月22日 下午5:23:27
 *
 * @desc: RestTemplate服务调用
 */
@Component
public class RestTemplateUtil {

	@Autowired
	private RestTemplate restTemplate0;

	private static RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		System.out.println("init static restTemplate...");
		if (restTemplate0 == null)
			System.out.println("init static restTemplate...null");
		restTemplate = restTemplate0;
	}

	// RestTemplate调用测试
	public static void test() {
		String url = "http://127.0.0.1:8080/rest/";

		// GET---无参
		// String requestName = "getNoParam";
		// String result = restTemplate.getForObject(url+requestName,
		// String.class);

		// GET---有参-字符串
		// String requestName = "getStringParam";
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("id", 0);
		// params.put("name", "name");
		// String result = restTemplate.getForObject(url + requestName +
		// "?id={id}&name={name}", String.class, params);

		// POST---无参
		// String requestName = "postNoParam";
		// HttpEntity<String> httpEntity = new HttpEntity<String>(null, null);
		// String result = restTemplate.postForObject(url + requestName,
		// httpEntity, String.class);

		// POST---有参-字符串
		// String requestName = "postStringParam";
		// MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		// params.add("id", 1);
		// params.add("name", "名称");
		// String result = restTemplate.postForObject(url+requestName, params,
		// String.class);

		// POST---有参-对象
//		String requestName = "postObjectParam";
//		HttpHeaders headers = new HttpHeaders();
//		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//		headers.setContentType(type);
//		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//		SUser user = new SUser();
//		user.setId(1);
//		user.setName("名称0");
//		JSONObject json = JSONObject.fromObject(user);
//		HttpEntity<String> httpEntity = new HttpEntity<String>(JsonObjectUtil.jsonToString(json), headers);
//		String result = restTemplate.postForObject(url + requestName, httpEntity, String.class);
		
		// POST---有参-字符串+对象
		String requestName = "postStringAndObjectParam?name=名称zx";
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
//		params.add("name", "名称zx");
		SUser user = new SUser();
		user.setId(12);
		user.setName("名称012");
		params.add("user", user);
		JSONObject json = JSONObject.fromObject(user);
		params.add("user", JsonObjectUtil.jsonToString(json));
		HttpEntity<String> httpEntity = new HttpEntity<String>(JsonObjectUtil.jsonToString(json), headers);
//		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
		String result = restTemplate.postForObject(url + requestName, httpEntity, String.class);

		System.out.println("result=[" + result + "]");
	}

	@ExceptionHandler(RestClientResponseException.class)
	public String exceptionHandler(HttpClientErrorException e) throws IOException {
		System.out.println("RestTemplateUtil error..." + e.getResponseBodyAsString());
		return e.getResponseBodyAsString();
	}

}
