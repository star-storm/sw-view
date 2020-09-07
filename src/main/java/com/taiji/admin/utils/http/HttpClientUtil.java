/**
 * 
 */
package com.taiji.admin.utils.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.taiji.admin.utils.JsonObjectUtil;

import net.sf.json.JSONObject;

/**
 * sw-view com.taiji.admin.utils HttpClient.java
 *
 * @author hsl
 *
 * 2020年4月21日 下午6:07:56
 *
 * desc: httpclient服务调用
 */
public class HttpClientUtil {

	/**
	 * GET---无参
	 */
	public static String httpGet(String url) throws ParseException, IOException {
		// 获得Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Get请求
		HttpGet httpGet = new HttpGet(url);
		// 响应模型 发送Get请求
		CloseableHttpResponse response = httpClient.execute(httpGet);
		// 从响应模型中获取响应实体
		HttpEntity responseEntity = response.getEntity();
		System.out.println("响应状态为:" + response.getStatusLine());
		String result = "";
		if (responseEntity != null) {
			System.out.println("响应内容长度为:" + responseEntity.getContentLength());
			result = EntityUtils.toString(responseEntity);
			System.out.println("响应内容为:" + result);
		}
		// 释放资源
		if (httpClient != null) {
			httpClient.close();
		}
		if (response != null) {
			response.close();
		}
		return result;
	}

	/**
	 * GET---有参-字符串
	 */
	public static String httpGet(String url, String params) throws ClientProtocolException, IOException {
		// 获得Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Get请求
		HttpGet httpGet = new HttpGet(url + "?" + params);
		// 响应模型
		CloseableHttpResponse response = null;
		// 配置信息
		RequestConfig requestConfig = RequestConfig.custom()
				// 设置连接超时时间(单位毫秒)
				.setConnectTimeout(5000)
				// 设置请求超时时间(单位毫秒)
				.setConnectionRequestTimeout(5000)
				// socket读写超时时间(单位毫秒)
				.setSocketTimeout(5000)
				// 设置是否允许重定向(默认为true)
				.setRedirectsEnabled(true).build();
		// 将上面的配置信息 运用到这个Get请求里
		httpGet.setConfig(requestConfig);
		// 由客户端执行(发送)Get请求
		response = httpClient.execute(httpGet);
		// 从响应模型中获取响应实体
		HttpEntity responseEntity = response.getEntity();
		System.out.println("响应状态为:" + response.getStatusLine());
		String result = "";
		if (responseEntity != null) {
			System.out.println("响应内容长度为:" + responseEntity.getContentLength());
			result = EntityUtils.toString(responseEntity);
			System.out.println("响应内容为:" + result);
		}
		// 释放资源
		if (httpClient != null) {
			httpClient.close();
		}
		if (response != null) {
			response.close();
		}
		return result;
	}

	/**
	 * GET---有参-集合
	 */
	public static String httpGet(String scheme, String host, Integer port, String path, List<NameValuePair> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		// 获得Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Get请求
		URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath(path)
				.setParameters(params).build();
		HttpGet httpGet = new HttpGet(uri);
		// 响应模型
		CloseableHttpResponse response = null;
		// 配置信息
		RequestConfig requestConfig = RequestConfig.custom()
				// 设置连接超时时间(单位毫秒)
				.setConnectTimeout(5000)
				// 设置请求超时时间(单位毫秒)
				.setConnectionRequestTimeout(5000)
				// socket读写超时时间(单位毫秒)
				.setSocketTimeout(5000)
				// 设置是否允许重定向(默认为true)
				.setRedirectsEnabled(true).build();
		// 将上面的配置信息 运用到这个Get请求里
		httpGet.setConfig(requestConfig);
		// 由客户端执行(发送)Get请求
		response = httpClient.execute(httpGet);
		// 从响应模型中获取响应实体
		HttpEntity responseEntity = response.getEntity();
		System.out.println("响应状态为:" + response.getStatusLine());
		String result = "";
		if (responseEntity != null) {
			System.out.println("响应内容长度为:" + responseEntity.getContentLength());
			result = EntityUtils.toString(responseEntity);
			System.out.println("响应内容为:" + result);
		}
		// 释放资源
		if (httpClient != null) {
			httpClient.close();
		}
		if (response != null) {
			response.close();
		}
		return result;
	}

	/**
	 * POST---无参
	 */
	public static String httpPost(String url) throws ClientProtocolException, IOException {
		// 获得Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		HttpPost httpPost = new HttpPost(url);
		// 响应模型 发送Post请求
		CloseableHttpResponse response = httpClient.execute(httpPost);
		// 从响应模型中获取响应实体
		HttpEntity responseEntity = response.getEntity();
		System.out.println("响应状态为:" + response.getStatusLine());
		String result = "";
		if (responseEntity != null) {
			System.out.println("响应内容长度为:" + responseEntity.getContentLength());
			result = EntityUtils.toString(responseEntity);
			System.out.println("响应内容为:" + result);
		}
		// 释放资源
		if (httpClient != null) {
			httpClient.close();
		}
		if (response != null) {
			response.close();
		}
		return result;
	}

	/**
	 * POST---有参-字符串
	 */
	public static String httpPost(String url, String params) throws ClientProtocolException, IOException {
		// 获得Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		HttpPost httpPost = new HttpPost(url + "?" + params);
		// 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
		// 响应模型 发送Post请求
		CloseableHttpResponse response = httpClient.execute(httpPost);
		// 从响应模型中获取响应实体
		HttpEntity responseEntity = response.getEntity();
		System.out.println("响应状态为:" + response.getStatusLine());
		String result = "";
		if (responseEntity != null) {
			System.out.println("响应内容长度为:" + responseEntity.getContentLength());
			result = EntityUtils.toString(responseEntity);
			System.out.println("响应内容为:" + result);
		}
		// 释放资源
		if (httpClient != null) {
			httpClient.close();
		}
		if (response != null) {
			response.close();
		}
		return result;
	}

	/**
	 * POST---有参-对象
	 */
	public static String httpPost(String url, Object param) throws ClientProtocolException, IOException {
		// 获得Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		HttpPost httpPost = new HttpPost(url);
//		String json = JSON.toJSONString(param);
		String json = JsonObjectUtil.jsonToString(JSONObject.fromObject(param));
		StringEntity entity = new StringEntity(json, "UTF-8");
		// 把参数放在请求体
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		// 从响应模型中获取响应实体
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity responseEntity = response.getEntity();
		System.out.println("响应状态为:" + response.getStatusLine());
		String result = "";
		if (responseEntity != null) {
			System.out.println("响应内容长度为:" + responseEntity.getContentLength());
			result = EntityUtils.toString(responseEntity);
			System.out.println("响应内容为:" + result);
		}
		// 释放资源
		if (httpClient != null) {
			httpClient.close();
		}
		if (response != null) {
			response.close();
		}
		return result;
	}

	/**
	 * POST---有参-字符串+对象
	 */
	public static String httpPost(String scheme, String host, Integer port, String path, Object param,
			List<NameValuePair> params) throws URISyntaxException, IOException {
		// 获得Http客户端
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath(path).setParameters(params)
				.build();
		HttpPost httpPost = new HttpPost(uri);
		// 把参数放在请求体
		StringEntity entity = new StringEntity(JSON.toJSONString(param), "UTF-8");
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
		// 响应模型 发送Post请求
		CloseableHttpResponse response = httpClient.execute(httpPost);
		// 从响应模型中获取响应实体
		HttpEntity responseEntity = response.getEntity();
		System.out.println("响应状态为:" + response.getStatusLine());
		String result = "";
		if (responseEntity != null) {
			System.out.println("响应内容长度为:" + responseEntity.getContentLength());
			result = EntityUtils.toString(responseEntity);
			System.out.println("响应内容为:" + result);
		}
		// 释放资源
		if (httpClient != null) {
			httpClient.close();
		}
		if (response != null) {
			response.close();
		}
		return result;
	}
	
	//合成参数
	@SuppressWarnings("unused")
	private static List<NameValuePair> parseParam() {
		// 将参数放入键值对类NameValuePair中,再放入集合中
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("id", "1"));
		return params;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws ParseException, IOException, URISyntaxException {
//		String url = "http://localhost:8080/view/main";
//		String result = httpGet(url);//GET---无参
		
		String url = "http://localhost:8080/api/org/model";
		String result = httpGet(url , "id=1");//GET---有参-字符串
		
//		String result = httpGet("http", "localhost", 8080, "/api/org/model", parseParam());//GET---有参-集合
		
//		String url = "http://localhost:8080/api/permission/members";
//		String result = httpPost(url);//POST---无参
		
//		String url = "http://localhost:8080/file/get/1";
//		String result = httpPost(url, "1");//POST---有参-字符串
//		String url = "http://localhost:8080/file/get1";
//		String result = httpPost(url, "fid=1");//POST---有参-字符串
		
//		String url = "http://localhost:8080/file/get2";
//		String url = "http://localhost:8080/file/get3";
//		TFile tf = new TFile();
//		tf.setId(1);
//		String result = httpPost(url, tf);//POST---有参-对象
		
//		TFile tf = new TFile();
//		tf.setId(1);
//		String result = httpPost("http", "localhost", 8080, "/file/get4", tf, parseParam());//POST---有参-字符串+对象
		System.out.println("result=["+result+"]");
	}

}
