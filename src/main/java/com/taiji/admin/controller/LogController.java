/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SLog;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SLogService;

/**
 * 
 * sw-view com.taiji.admin.controller LogController.java
 *
 * @author hsl
 *
 * 2020年2月17日 下午4:09:38
 *
 * desc:
 */

@RestController
@RequestMapping("/api/log")
public class LogController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SLogService logService;
	
	/**
	 * 列表
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo logPage(Integer nowPage, Integer pageSize, String modelId, String content, String userName) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		if (host == null) {
			resp.setCode(500);
			resp.setData(null);
		}
		else {
			String roleName = "admin";
			PageInfo pageInfo = new PageInfo(nowPage, pageSize);
			long count = logService.count(modelId, content, userName, roleName);
			List<SLog> logs = logService.logPage(pageInfo, modelId, content, userName, roleName);
			resp.setCode(200);
			resp.setCount(count);
			resp.setData(logs);
		}
//		logUtil.appendLog(request, Constant.LOG_INDEX.toString(), "LogController", "logPage", Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 详情
	 * @throws IOException 
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getLog(Integer id) throws IOException {
		SLog log = logService.getLog(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(log);
//		logUtil.appendLog(request, Constant.LOG_INDEX.toString(), "LogController", "getLog", Constant.RESULT_SUCCESS);
		return resp;
	}

}
