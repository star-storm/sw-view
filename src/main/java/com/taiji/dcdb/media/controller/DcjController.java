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
import com.taiji.dcdb.media.service.DcjService;

/**
 * 督查件
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/api/dcdb/dcj")
public class DcjController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	DcjService dcjService;
	
	/**
	 * 督察件数-督查件统计数
	 */
	@RequestMapping("/dcjCount")
	@ResponseBody
	public ResponseInfo dcjCount(String year, String area) throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		List<Map<String, Object>> list = dcjService.dcjCount(year, area, host);
		resp.setData(list);
		return resp;
	}
	
	/**
	 * 督察件数-督查件列表
	 */
	@RequestMapping("/dcjList")
	@ResponseBody
	public ResponseInfo dcjList(String year, String type, String status, String area) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<Map<String, Object>> result = dcjService.dcjList(year, type, status, area, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 督察件数-督查件全列表
	 */
	@RequestMapping("/dcjAllList")
	@ResponseBody
	public ResponseInfo dcjAllList(String year, String status) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<Map<String, Object>> result = dcjService.dcjAllList(year, status, host);
		resp.setData(result);
		return resp;
	}
	
	
	
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo list(String year, String area, String areaName) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		@SuppressWarnings("rawtypes")
		List<List> result = dcjService.dcdbList(year, area, areaName, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 状态列表
	 */
	@RequestMapping("/statusList")
	@ResponseBody
	public ResponseInfo statusList(String year, String status, String type, String area) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<String> result = dcjService.dcdbStatusList(year, status, type, area, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 区域统计
	 * area(0:16区;1:其他)
	 */
	@RequestMapping("/areaCount")
	@ResponseBody
	public ResponseInfo areaCount(String year, String area) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<String> result = dcjService.dcjsAreaCount(year, area, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 区域列表
	 */
	@RequestMapping("/areaList")
	@ResponseBody
	public ResponseInfo areaList(String year, String status, String type, String area, String areaName) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<String> result = dcjService.dcdbAreaList(year, status, type, areaName, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 基本信息
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo model(String id) throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<Map<String, Object>> list = dcjService.dcdbModel(id, host);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(list.size()>0?list.get(0):null);
		return resp;
	}

}
