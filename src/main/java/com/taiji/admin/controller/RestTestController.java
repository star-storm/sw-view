/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.admin.model.SUser;
import com.taiji.admin.utils.JsonObjectUtil;

import net.sf.json.JSONObject;

/**
 * sw-view com.taiji.admin.controller RestController.java
 *
 * @author hsl
 *
 * 2020年4月23日 下午3:53:13
 *
 * desc:
 */
@RestController
@RequestMapping("/rest")
public class RestTestController {
	
	/**
	 * GET---无参
	 */
	@RequestMapping(value="/getNoParam", method={RequestMethod.GET})
	public String getNoParam() throws IOException {
		System.out.println("rest test...getNoParam");
		return "getNoParam";
	}
	
	/**
	 * GET---有参-字符串
	 */
	@RequestMapping(value="/getStringParam", method={RequestMethod.GET})
	public String getStringParam(Integer id, String name) throws IOException {
		System.out.println("rest test...getStringParam");
		String result = "id:"+id+",name:"+name;
		System.out.println("result=["+result+"]");
		return result;
	}
	
	/**
	 * POST---无参
	 */
	@RequestMapping(value="/postNoParam", method={RequestMethod.POST}, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String postNoParam() throws IOException {
		System.out.println("rest test...postNoParam");
		return "postNoParam";
	}
	
	/**
	 * POST---有参-字符串
	 */
	@RequestMapping(value="/postStringParam", method={RequestMethod.POST})
	@ResponseBody
	public String postStringParam(@RequestParam Integer id, @RequestParam String name) throws IOException {
		System.out.println("rest test...postStringParam");
		String result = "id:"+id+",name:"+name;
		System.out.println("result=["+result+"]");
		return result;
	}
	
	/**
	 * POST---有参-对象
	 */
	@RequestMapping(value="/postObjectParam", method={RequestMethod.POST})
	public String postObjectParam(@RequestBody SUser user) throws IOException {
		System.out.println("rest test...postObjectParam");
		String result = JsonObjectUtil.jsonToString(JSONObject.fromObject(user));
		System.out.println("result=["+result+"]");
		return result;
	}
	
	/**
	 * POST---有参-字符串+对象
	 */
	@RequestMapping(value="/postStringAndObjectParam", method={RequestMethod.POST})
	public String postStringAndObjectParam(@RequestParam(value="name") String name, @RequestBody SUser user) throws IOException {
		System.out.println("rest test...postStringAndObjectParam");
		String result = "name:"+name+","+JsonObjectUtil.jsonToString(JSONObject.fromObject(user));
		System.out.println("result=["+result+"]");
		return result;
	}
	
	public static void main(String[] args) throws ParseException, IOException, URISyntaxException {
//		String url = "http://localhost:8080/view/main";
//		String result = httpGet(url);//GET---无参
		
//		String url = "http://localhost:8080/api/org/model";
//		String result = httpGet(url , "id=1");//GET---有参-字符串
		
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
//		System.out.println("result=["+result+"]");
	}

}
