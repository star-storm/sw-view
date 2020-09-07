/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SPermission;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SPermissionService;
import com.taiji.admin.utils.LogUtil;

/**
 * 
 * sw-view com.taiji.admin.controller PermissionController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:38:55
 *
 * desc:
 */

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SPermissionService permissionService;
	
	@Autowired
	private LogUtil logUtil;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo permissionPage(Integer nowPage, Integer pageSize, String name, String descrip) throws IOException {
		PageInfo pageInfo = new PageInfo(nowPage, pageSize);
		long count = permissionService.count(name, descrip);
		List<SPermission> permissions = permissionService.permissionPage(pageInfo, name, descrip);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setCount(count);
		resp.setData(permissions);
		logUtil.appendLog(request, "", "查询权限", logUtil.appendParam("", name), Constant.RESULT_SUCCESS_CODE);
		return resp;
	}
	
	/**
	 * 权限基本信息
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getPermission(Integer id) throws IOException {
		SPermission permission = permissionService.getPermission(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(permission);
//		logUtil.appendLog(request, Constant.ROLE_INDEX.toString(), "查询权限详情", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS_CODE);
		return resp;
	}
	
	/**
	 * 角色的权限信息
	 */
	@RequestMapping("/selectByRole")
	@ResponseBody
	public ResponseInfo selectByRole(Integer rid) throws IOException {
		List<SPermission> permissions = permissionService.selectByRole(rid);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(permissions);
//		logUtil.appendLog(request, Constant.ROLE_INDEX.toString(), "查询权限详情", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS_CODE);
		return resp;
	}
	
	/**
	 * 查询全部权限
	 * 只查询权限Id和名称
	 */
	@RequestMapping("/members")
	@ResponseBody
	public ResponseInfo members() throws IOException {
		List<Map<String, Object>> result = permissionService.members();
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(result);
//		logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "查询权限", "", Constant.RESULT_SUCCESS_CODE);
		return resp;
	}
	
	/**
	 * 更新权限
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseInfo upPermission(Integer id, String name, Integer type, String url, String method, String desc) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		Integer result = permissionService.upPermission(id, name, type, url, method, desc, host);
		if (result == 1){
			resp.setCode(500);
			resp.setMsg("权限名称为空");
		}
		else if (result == 2){
			resp.setCode(500);
			resp.setMsg("数据处理错误");
		}
		else {
			logUtil.appendLog(request, "", "更新权限", logUtil.appendParam("", name), Constant.RESULT_SUCCESS_CODE);
			resp.setCode(200);
		}
		return resp;
	}
	
	/**
	 * 删除权限
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseInfo delPermission(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = permissionService.delPermission(id, host);
		if (info.getResult() == 0){
			logUtil.appendLog(request, "", "删除权限", logUtil.appendParam("", info.getName()), Constant.RESULT_SUCCESS_CODE);
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			switch (info.getResult()) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 3:
				resp.setMsg("权限不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
		}
		return resp;
	}
	
	/**
	 * 批量删除权限
	 */
	@RequestMapping("/delBatch")
	@ResponseBody
	public ResponseInfo delBatch(String ids) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = permissionService.delBatch(ids, host);
		if (info.getResult() == 0){
			logUtil.appendLog(request, "", "批量删除权限", logUtil.appendParam("", ""), Constant.RESULT_SUCCESS_CODE);
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			switch (info.getResult()) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 3:
				resp.setMsg("权限不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
		}
		return resp;
	}
	
}
