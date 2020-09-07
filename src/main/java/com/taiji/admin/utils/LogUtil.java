/**
 * 
 */
package com.taiji.admin.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SLogService;

/**
 * 
 * sw-view com.taiji.admin.utils LogUtil.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:49:56
 *
 * desc:
 */
public class LogUtil {
	
	@Autowired
	private SLogService logService;
	
	/**
	 * 记录日志
	 */
	public void appendLog(HttpServletRequest request, String modelId, String option, String content, String result) throws IOException {
		//用户session
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		//登录状态
		boolean loginStatus = host == null;
		//登录用户
		String userId = "";
		if (!loginStatus) {
			userId = host.getId().toString();
		}
		else {
			userId = (String) request.getSession().getAttribute("tmpHost");
		}
		//记录日志
		logService.addLog(modelId, userId, option, content, result);
	}
	
	/**
	 * 查询条件
	 */
	public String appendParam(String id, String name) {
		StringBuffer sbf = new StringBuffer();
		if (!StringUtils.isEmpty(id))
			sbf.append("编号：" + id + ", ");
		if (!StringUtils.isEmpty(name))
			sbf.append("名称：" + name);
		return sbf.toString();
	}
	
	//获取请求参数
	@SuppressWarnings({ "rawtypes", "unused" })
	private String requestParam(HttpServletRequest request) {
		StringBuffer sbf = new StringBuffer();
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			Object paramValue = request.getParameter(paramName);
			sbf.append(paramName + "=" + paramValue);
			sbf.append("&");
		}
		String result = sbf.toString();
		if (result.endsWith("&"))
			result = result.substring(0, result.length() - 1);
		System.out.println("params: [" + result + "]");
		return result;
	}
		
	//获取请求参数
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private String requestParam0(HttpServletRequest request) {
		StringBuffer sbf = new StringBuffer();
		Map map = request.getParameterMap();
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String paramName = iterator.next();
			Object paramValue = request.getParameter(paramName);
			sbf.append(paramName + "=" + paramValue);
			sbf.append("&");
		}
		String result = sbf.toString();
		if (result.endsWith("&"))
			result = result.substring(0, result.length() - 1);
		System.out.println("params: [" + result + "]");
		return result;
	}

}
