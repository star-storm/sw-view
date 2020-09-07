/**
 * 
 */
package com.taiji.dcdb.media.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SUser;
import com.taiji.dcdb.media.service.MediaService;

/**
 * 
 * 媒体
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/api/dcdb/media")
public class MediaController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MediaService mediaService;
	
	/**
	 * 督察件数-媒体统计数
	 */
	@RequestMapping("/mediaCount")
	@ResponseBody
	public ResponseInfo mediaCount(String year, String area) throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		List<Map<String, Object>> list = mediaService.mediaCount(year, area, host);
		resp.setData(list);
		return resp;
	}
	
	/**
	 * 督察件数-媒体件列表
	 */
	@RequestMapping("/mediaList")
	@ResponseBody
	public ResponseInfo mediaList(String year, String type, String status, String area) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<Map<String, Object>> result = mediaService.mediaList(year, type, status, area, host);
		resp.setData(result);
		return resp;
	}
	
	
	/**
	 * 媒体反映问题列表
	 */
	@RequestMapping("/mediaWentiList")
	@ResponseBody
	public ResponseInfo mediaWentiList(String year) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<String> result = mediaService.mediaWentiList(year, host);
		resp.setData(result);
		return resp;
	}
	
	
	/**
	 * 反馈总数
	 */
	@RequestMapping("/mediaFankuiCount")
	@ResponseBody
	public ResponseInfo mediaFankuiCount(String year) throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		long mediaFankuiCount = mediaService.mediaFankuiCount(year, host);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(mediaFankuiCount);
		return resp;
	}
	
	/**
	 * 反馈列表
	 */
	@RequestMapping("/mediaFankuiList")
	@ResponseBody
	public ResponseInfo mediaFankuiList(String year) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<String> result = mediaService.mediaFankuiList(year, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 核查已办结总数
	 */
	@RequestMapping("/mediaHechaBanJieCount")
	@ResponseBody
	public ResponseInfo count(String year) throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		long mediaHechaBanJieCount = mediaService.mediaHechaBanJieCount(year, host);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(mediaHechaBanJieCount);
		return resp;
	}
	
	/**
	 * 核查已办结列表
	 */
	@RequestMapping("/mediaHechaBanJieList")
	@ResponseBody
	public ResponseInfo mediaHechaBanJieList(String year) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<String> result = mediaService.mediaHechaBanJieList(year, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 详情
	 */
	@RequestMapping("/mediaModel")
	@ResponseBody
	public ResponseInfo mediaModel(String id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<Map<String, Object>> result = mediaService.mediaModel(id, host);
		resp.setData(result.size()>0?result.get(0):result);
		return resp;
	}

}
