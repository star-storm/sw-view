/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SRole;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SRoleService;
import com.taiji.admin.utils.LogUtil;

/**
 * 
 * sw-view com.taiji.admin.controller RoleController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:39:53
 *
 * desc:
 */

@RestController
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SRoleService roleService;
	
	@Autowired
	private LogUtil logUtil;
	
	/**
	 * 列表
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo rolePage(Integer nowPage, Integer pageSize, String name, String descrip) throws IOException {
		PageInfo pageInfo = new PageInfo(nowPage, pageSize);
		long count = roleService.count(name, descrip);
		List<SRole> roles = roleService.rolePage(pageInfo, name, descrip);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setCount(count);
		resp.setData(roles);
		logUtil.appendLog(request, "", "查询角色", logUtil.appendParam("", name), Constant.RESULT_SUCCESS_CODE);
		return resp;
	}
	
	/**
	 * 角色基本信息
	 * @throws IOException 
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getRole(Integer id) throws IOException {
		SRole role = roleService.getRole(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(role);
//		logUtil.appendLog(request, Constant.ROLE_INDEX.toString(), "查询角色详情", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS_CODE);
		return resp;
	}
	
	/**
	 * 角色所有信息：基本信息，权限等
	 * @throws IOException 
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public ResponseInfo getRoleAll(Integer id) throws IOException {
		SRole role = roleService.getRoleAll(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(role);
//		logUtil.appendLog(request, Constant.ROLE_INDEX.toString(), "查询角色详情及相关权限", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS_CODE);
		return resp;
	}

	/**
	 * 更新角色：新建，编辑
	 * @throws IOException 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseInfo updateRole(String id, String name, String descrip, String pIds) throws IOException {
		String tag = "编辑角色信息";
		if (StringUtils.isEmpty(id))
			tag = "新建角色信息";
		
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int result = roleService.updateRole(id, name, descrip, pIds, host);
		if (result == 0){
			resp.setCode(200);
			resp.setMsg("操作成功");
			logUtil.appendLog(request, "", tag, logUtil.appendParam(String.valueOf(id), name), Constant.RESULT_SUCCESS_CODE);
		}
		else {
			resp.setCode(500);
			switch (result) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
				resp.setMsg("数据不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
			logUtil.appendLog(request, "", tag, logUtil.appendParam(String.valueOf(id), name), Constant.RESULT_FAIL_CODE);
		}
		return resp;
	}
	
	/**
	 * 删除角色
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseInfo delRole(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = roleService.delRole(id, host);
		if (info.getResult() == 0){
			resp.setCode(200);
			logUtil.appendLog(request, "", "删除角色信息", logUtil.appendParam(info.getId(), info.getName()), Constant.RESULT_SUCCESS_CODE);
		}
		else {
			resp.setCode(500);
			switch (info.getResult()) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
				resp.setMsg("有用户有该角色，不能删除");
				break;
			case 3:
				resp.setMsg("角色不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
			logUtil.appendLog(request, "", "删除角色信息", logUtil.appendParam(info.getId(), info.getName()), Constant.RESULT_FAIL_CODE);
		}
		return resp;
	}
	
	/**
	 * 批量删除角色
	 * @throws IOException 
	 */
	@RequestMapping("/delBatch")
	@ResponseBody
	public ResponseInfo delBatch(String ids) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = roleService.delBatch(ids, host);
		if (info.getResult() == 0){
			resp.setCode(200);
			logUtil.appendLog(request, "", "批量删除虚拟机模板信息", logUtil.appendParam(info.getId(), info.getName()), Constant.RESULT_SUCCESS_CODE);
		}
		else {
			resp.setCode(500);
			switch (info.getResult()) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
				resp.setMsg("有用户有该角色，不能删除");
				break;
			case 3:
				resp.setMsg("角色不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
			logUtil.appendLog(request, "", "批量删除虚拟机模板信息", logUtil.appendParam(info.getId(), info.getName()), Constant.RESULT_FAIL_CODE);
		}
		return resp;
	}
	
	/**
	 * 查询全部角色
	 * 只查询角色Id和名称
	 * @throws IOException 
	 */
	@RequestMapping("/members")
	@ResponseBody
	public ResponseInfo members() throws IOException {
		List<Map<String, Object>> result = roleService.members();
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(result);
//		logUtil.appendLog(request, Constant.ROLE_INDEX.toString(), "查询全部角色信息", "", Constant.RESULT_SUCCESS_CODE);
		return resp;
	}
	
	/**
	 * 更新用户角色
	 * @throws IOException 
	 */
	@RequestMapping("/upUserRole")
	@ResponseBody
	public ResponseInfo upUserRole(Integer uid, Integer rid) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		boolean result = roleService.upUserRole(uid, rid, host);
		if (!result){
			resp.setCode(500);
			resp.setMsg("数据处理错误");
			logUtil.appendLog(request, "", "更新用户角色", "", Constant.RESULT_FAIL_CODE);
		}
		else {
			resp.setCode(200);
			logUtil.appendLog(request, "", "更新用户角色", "", Constant.RESULT_SUCCESS_CODE);
		}
		return resp;
	}
}
