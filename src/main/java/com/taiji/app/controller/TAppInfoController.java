/**
 * 
 */
package com.taiji.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SUser;
import com.taiji.app.model.TAppInfo;
import com.taiji.app.service.TAppInfoService;

/**
 * 
 * sw-view com.taiji.app.controller TAppInfoController.java
 *
 * @author hsl
 *
 * 2020年5月9日 下午4:38:37
 *
 * desc:
 */

@RestController
@RequestMapping("/api/app")
public class TAppInfoController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private TAppInfoService appInfoService;
	
//	@Autowired
//	private LogUtil logUtil;
	
	/**
	 * 列表
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo appInfoPage(Integer nowPage, Integer pageSize, String name) throws IOException {
		PageInfo pageInfo = new PageInfo(nowPage, pageSize);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		long count = appInfoService.count(name, host);
		List<TAppInfo> appInfos = appInfoService.appInfoPage(pageInfo, name, host);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setCount(count);
		resp.setData(appInfos);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询信息", logUtil.appendParam("", name), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 基本信息
	 * @throws IOException 
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getAppInfo(Integer id) throws IOException {
		TAppInfo appInfo = appInfoService.getAppInfo(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(appInfo);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询信息详情", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 查询全部
	 */
	@RequestMapping("/members")
	@ResponseBody
	public ResponseInfo members() throws IOException {
		List<TAppInfo> result = appInfoService.members();
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 更新：新建，编辑
	 * @throws IOException 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseInfo updateAppInfo(Integer id, String name, String code, 
			String url, String owner, String pid) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		String result = appInfoService.updateAppInfo(id, name, code, url, owner, pid, host);
		if (!result.equals("success")){
			resp.setCode(500);
			resp.setMsg(result);
//			logUtil.appendLog(request, Constant.USER_INDEX.toString(), "更新信息", "", Constant.RESULT_FAIL);
		}
		else {
			resp.setCode(200);
//			logUtil.appendLog(request, Constant.USER_INDEX.toString(), "更新信息", "", Constant.RESULT_SUCCESS);
		}
		return resp;
	}
	
	/**
	 * 删除
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseInfo delAppInfo(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		boolean result = appInfoService.delAppInfo(id, host);
		if (!result){
			resp.setCode(500);
			resp.setMsg("数据处理错误");
//			logUtil.appendLog(request, Constant.USER_INDEX.toString(), "删除信息", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_FAIL);
		}
		else {
			resp.setCode(200);
//			logUtil.appendLog(request, Constant.USER_INDEX.toString(), "删除信息", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		}
		return resp;
	}
	
	/**
	 * 批量删除组织
	 */
	@RequestMapping("/delBatch")
	@ResponseBody
	public ResponseInfo delBatch(String ids) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = appInfoService.delBatch(ids, host);
		if (info.getResult() == 0){
			resp.setCode(200);
//			logUtil.appendLog(request, "0", "批量删除组织信息", logUtil.appendParam(String.valueOf(ids), ""), Constant.RESULT_SUCCESS_CODE);
		}
		else {
			resp.setCode(500);
			switch (info.getResult()) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 3:
				resp.setMsg("应用不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
//			logUtil.appendLog(request, "0", "批量删除组织信息", logUtil.appendParam(String.valueOf(ids), ""), Constant.RESULT_FAIL);
		}
		return resp;
	}

}
