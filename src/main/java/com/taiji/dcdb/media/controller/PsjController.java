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
import com.taiji.dcdb.media.service.PsjService;

/**
 * 批示件
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/api/dcdb/psj")
public class PsjController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PsjService psjService;
	
	/**
	 * 督察件数-批示件统计数
	 */
	@RequestMapping("/pishiCount")
	@ResponseBody
	public ResponseInfo groupCountSql(String year, String area) throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		List<Map<String, Object>> list = psjService.pishiCount(year, area, host);
		resp.setData(list);
		return resp;
	}
	
	/**
	 * 督察件数-批示件件列表
	 */
	@RequestMapping("/pishiList")
	@ResponseBody
	public ResponseInfo pishiList(String year, String type, String status, String area) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<Map<String, Object>> result = psjService.pishiList(year, type, status, area, host);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 总数
	 */
	@RequestMapping("/count")
	@ResponseBody
	public ResponseInfo count(String year) throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		List<String> list = psjService.count(year, host);
		resp.setData(list);
		return resp;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo list(String year, String type) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<String> list = psjService.psjList(year, type, host);
		resp.setData(list);
		return resp;
	}
	
	/**
	 * 详情
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo model(String id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		List<Map<String, Object>> result = psjService.model(id, host);
		resp.setData(result.size()>0?result.get(0):result);
		return resp;
	}

}
