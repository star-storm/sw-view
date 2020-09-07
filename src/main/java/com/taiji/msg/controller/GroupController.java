/**
 * 
 */
package com.taiji.msg.controller;

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
import com.taiji.admin.model.SUser;
import com.taiji.msg.model.TGroup;
import com.taiji.msg.service.TGroupService;

/**
 * 
 * sw-view com.taiji.msg.controller GroupController.java
 *
 * @author hsl
 *
 * 2019年12月14日 下午5:55:34
 *
 * desc:
 */

@RestController
@RequestMapping("/api/group")
public class GroupController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private TGroupService groupService;
	
//	@Autowired
//	private LogUtil logUtil;
	
	/**
	 * 列表
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo groupPage(Integer nowPage, Integer pageSize, String name) throws IOException {
		PageInfo pageInfo = new PageInfo(nowPage, pageSize);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		long count = groupService.count(name, host);
		List<TGroup> groups = groupService.groupPage(pageInfo, name, host);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setCount(count);
		resp.setData(groups);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询信息", logUtil.appendParam("", name), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 基本信息
	 * @throws IOException 
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getGroup(Integer id) throws IOException {
		TGroup group = groupService.getGroup(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(group);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询信息详情", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 更新：新建，编辑
	 * @throws IOException 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseInfo updateGroup(Integer id, String toOrgIds, String toOrgNames, 
			String toUserIds, String toUserNames, String toUserPhones, String name, String ids) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		String result = groupService.updateGroup(id, toOrgIds, toOrgNames, toUserIds, toUserNames, toUserPhones, name, ids, host);
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
	public ResponseInfo delGroup(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		boolean result = groupService.delGroup(id, host);
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
	 * 群组总数
	 */
	@RequestMapping("/groupCount")
	@ResponseBody
	public ResponseInfo groupCount() {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		long count = groupService.count("", host);
		resp.setCode(200);
		resp.setData(count);
		return resp;
	}

}
