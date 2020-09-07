/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import com.taiji.admin.model.SOrg;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SOrgService;
import com.taiji.admin.service.SRoleService;
import com.taiji.admin.service.SUserService;
import com.taiji.admin.utils.encrypt.Encrypt;

/**
 * 
 * sw-view com.taiji.admin.controller UserController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:41:57
 *
 * desc:
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SUserService userService;
	
	@Autowired
	private SRoleService roleService;
	
	@Autowired
	private SOrgService orgService;
	
//	@Autowired
//	private LogUtil logUtil;
	
	/**
	 * 列表
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo userPage(Integer nowPage, Integer pageSize, String userId, String name, String depart, String roleId) throws IOException {
		PageInfo pageInfo = new PageInfo(nowPage, pageSize);
		long count = userService.count(userId, name, depart, roleId);
		List<SUser> users = userService.userPage(pageInfo, userId, name, depart, roleId);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setCount(count);
		resp.setData(users);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询用户信息", logUtil.appendParam("", name), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 所有用户信息
	 * @throws IOException 
	 */
	@RequestMapping("/all")
	@ResponseBody
	public ResponseInfo allUserInfo() throws IOException {
		List<SUser> list = userService.allUserInfo();
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(list);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询用户信息详情及相关角色和权限", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 用户基本信息
	 * @throws IOException 
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getUser(Integer id) throws IOException {
		SUser user = userService.getUser(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(user);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询用户信息详情", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 用户所有信息：基本信息，角色，权限等
	 * @throws IOException 
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public ResponseInfo getUserAll(Integer id) throws IOException {
		SUser user = userService.getUserAll(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(user);
//		logUtil.appendLog(request, Constant.USER_INDEX.toString(), "查询用户信息详情及相关角色和权限", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 更新用户：新建，编辑
	 * @throws IOException 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseInfo updateUser(String uid, String userId, String name, String orgId, String orgName, String rid, String userSecret) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(orgId) || StringUtils.isEmpty(rid)) {
			resp.setCode(500);
			resp.setMsg("请求数据错误");
		}
		SOrg org = orgService.getOrg(Integer.valueOf(orgId));
		if (org == null) {
			resp.setCode(500);
			resp.setMsg("所在组织不存在");
		}
		else {
			SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
			SUser user = new SUser();
			if (!StringUtils.isEmpty(uid))
				user.setId(Integer.valueOf(uid));
			user.setLoginName(userId);
			user.setUserCode(userId);
			user.setName(name);
			user.setOrgCode(org.getCode());
			user.setOrgId(orgId);
			user.setOrgName(orgName);
			user.setUserSecret(userSecret);
			user.setUserPwd(Encrypt.encryptString("123456"));
			user.setCreateBy(host.getId());
			user.setCreateDate(new Date());
			user.setDelFlg("0");
			Integer id = userService.updateUser(user, host);
			if (id == -1 || id == null){
				resp.setCode(500);
				resp.setMsg("数据处理错误");
			}
			else {
				boolean result = roleService.upUserRole(id, Integer.valueOf(rid), host);
				if (!result){
					resp.setCode(500);
					resp.setMsg("角色处理错误");
				}
				resp.setCode(200);
				resp.setData(id);
			}
		}
		return resp;
	}
	
	/**
	 * 删除用户
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseInfo delUser(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		boolean result = userService.delUser(id, host);
		if (!result){
			resp.setCode(500);
			resp.setMsg("数据处理错误");
//			logUtil.appendLog(request, Constant.USER_INDEX.toString(), "删除用户信息", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_FAIL);
		}
		else {
			resp.setCode(200);
//			logUtil.appendLog(request, Constant.USER_INDEX.toString(), "删除用户信息", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		}
		return resp;
	}
	
	/**
	 * 批量删除用户
	 * @throws IOException 
	 */
	@RequestMapping("/delBatch")
	@ResponseBody
	public ResponseInfo delBatch(String ids) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = userService.delBatch(ids, host);
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
			case 2:
				resp.setMsg("有用户有该组织，不能删除");
				break;
			case 3:
				resp.setMsg("组织不存在");
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
