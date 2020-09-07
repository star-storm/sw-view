/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.service.SUserService;
import com.taiji.admin.service.SystemService;
import com.taiji.admin.utils.LogUtil;

/**
 * 
 * sw-view com.taiji.admin.controller SystemController.java
 *
 * @author hsl
 *
 * 2020年8月15日 下午6:09:43
 *
 * desc:
 *
 */

@Controller
@RequestMapping("/api/sys")
public class SystemController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SystemService systemService;
	
	@Autowired
	SUserService userService;
	
	@Autowired
	private LogUtil logUtil;
	
//	@Autowired
//	BeanConfig beanIns;
	
	/**
	 * 设置表空间阈值 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/writeThreshold")
	@ResponseBody
	public ResponseInfo writeThreshold(double threshold) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		systemService.writeThreshold(threshold);
		logUtil.appendLog(request, "", "设置表空间阈值 ", "操作成功", Constant.RESULT_SUCCESS_CODE);
		resp.setCode(200);
		return resp;
	}
	
	/**
	 * 读取表空间阈值 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/readThreshold")
	@ResponseBody
	public ResponseInfo readThreshold() throws IOException {
		ResponseInfo resp = new ResponseInfo();
		double threshold =  systemService.readThreshold();
		resp.setCode(200);
		resp.setData(threshold);
		return resp;
	}
	
	/**
	 * 表空间预警
	 */
	@RequestMapping(value = "/warn")
	@ResponseBody
	public ResponseInfo warn() {
		ResponseInfo resp = new ResponseInfo();
		List<Map<String,Object>> list =  systemService.warn();
		resp.setCode(200);
		resp.setData(list);
		return resp;
	}
	
	/**
	 * 系统超时重定向
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getRedirectTime")
	@ResponseBody
	public ResponseInfo getRedirectTime() throws IOException {
		ResponseInfo resp = new ResponseInfo();
		Integer sessiomTime0 =  systemService.readForward();
		Integer sessiomTime = request.getSession().getMaxInactiveInterval();
		System.out.println("getMaxInactiveInterval="+request.getSession().getMaxInactiveInterval());
		if (!StringUtils.isEmpty(sessiomTime0)) {
//			System.out.println("sessiomTime="+sessiomTime);
			sessiomTime = (int) Math.floor(sessiomTime/60);
			if (sessiomTime0 != sessiomTime) {
				request.getSession().setMaxInactiveInterval(sessiomTime0*60);
			}
			resp.setData(sessiomTime0);
		}
		else {
			request.getSession().setMaxInactiveInterval(30*60);
			resp.setData(30);
		}
		resp.setCode(200);
		return resp;
	}
	@RequestMapping(value = "/setRedirectTime")
	@ResponseBody
	public ResponseInfo setRedirectTime(String sessiomTime) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		if (!StringUtils.isEmpty(sessiomTime)) {
			request.getSession().setMaxInactiveInterval(Integer.valueOf(sessiomTime)*60);
			systemService.writeForward(sessiomTime);
			resp.setCode(200);
			logUtil.appendLog(request, "", "设置系统超时重定向", "操作成功", Constant.RESULT_SUCCESS_CODE);
		}
		else {
			resp.setCode(500);
		}
		return resp;
	}

}
