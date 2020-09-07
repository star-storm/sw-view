/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdt.userattr.UserAttrObj;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.config.BeanConfig;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.model.SLoginInfo;
import com.taiji.admin.model.SPermission;
import com.taiji.admin.model.SRole;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.LoginService;
import com.taiji.admin.service.SLoginInfoService;
import com.taiji.admin.service.SUserService;
import com.taiji.admin.utils.LogUtil;
import com.taiji.admin.utils.WebserviceUtil;

/**
 * 
 * sw-view com.taiji.admin.controller LoginController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:16:02
 *
 * desc:
 */

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	SUserService userService;
	
	@Autowired
	SLoginInfoService loginInfoService;
	
	@Autowired
	private SUserMapper userMapper;
	
	@Autowired
	private LogUtil logUtil;
	
	@Autowired
	BeanConfig beanIns;
	
	/**
	 * 首页
	 */
	@RequestMapping("/index")
	public String index() {
		return "view/login";
	}
	
	@RequestMapping(value = "/singleLogin")
	@ResponseBody
	public ResponseInfo login(String authTicket) throws DocumentException, IOException {
		System.out.println("singleLogin: "+authTicket);
		String result = WebserviceUtil.loginWebservice(beanIns.zcptSynUrl, beanIns.zcptSynQname, authTicket);
		String userCode = WebserviceUtil.getUserCode(result);
//		String userCode = "3d327b56726f469b82db61dbe3969604";
		System.out.println("登录用户编码：" + userCode);
		ResponseInfo resp = new ResponseInfo();
		int n = loginService.verifyUser(request.getSession(), userCode);
		if (n == 0) {
			resp.setCode(200);
			SUser user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
			resp.setData(user);
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "登录系统", "登录成功", Constant.RESULT_SUCCESS);
		}
		else {
			resp.setCode(500);
			if (n == 1) {
				resp.setMsg("参数错误");
			}
			else if (n == 2) {
				resp.setMsg("用户不存在");
			}
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "登录系统", "登录失败", Constant.RESULT_FAIL);
		}
		return resp;
	}
	
	@RequestMapping("/userLogin")
	@ResponseBody
	public ResponseInfo userLogin(String userCode) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		int n = loginService.verifyUser(request.getSession(), userCode);
		if (n == 0) {
			resp.setCode(200);
			SUser user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
			resp.setData(user);
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "登录系统", "登录成功", Constant.RESULT_SUCCESS);
		}
		else {
			resp.setCode(500);
			if (n == 1) {
				resp.setMsg("参数错误");
			}
			else if (n == 2) {
				resp.setMsg("用户不存在");
			}
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "登录系统", "登录失败", Constant.RESULT_FAIL);
		}
		return resp;
	}
	
	@RequestMapping("/nameLogin")
	@ResponseBody
	public ResponseInfo nameLogin(String loginName, String password) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		HttpSession session = request.getSession(); 
		SUser user = userMapper.selectUserByLoginName(loginName);
		//判断是否锁定
		SLoginInfo loginInfo = loginInfoService.getLoginInfoByName(loginName);
		if (loginInfo != null) {//有锁定记录
			String isLock = loginInfo.getIsLock();
			if (!StringUtils.isEmpty(isLock) && isLock.equals("1")) {//锁定状态
				long curTime = System.currentTimeMillis();
				String lockTimeTmp = loginInfo.getLockTime();
				if (!StringUtils.isEmpty(lockTimeTmp)) {//锁定时间
					if ((curTime-Long.valueOf(lockTimeTmp))/1000<30*60) {//锁定时间在30分钟内，未到解锁时间
						resp.setCode(500);
						resp.setMsg("密码锁定中");
					}
					else {//超过解锁时间限制，清楚锁定记录，然后正常登录
						loginInfo.setFailNum(0);
						loginInfo.setIsLock("0");
						loginInfo.setLockTime(null);
						loginInfoService.updateLoginInfo(loginInfo, user);
						resp = nameLoginProc(session, user, loginName, password);
					}
				}
				else//锁定时间为空，认为没锁定，走正常登录流程
					resp = nameLoginProc(session, user, loginName, password);
			}
			else {//未锁定
				resp = nameLoginProc(session, user, loginName, password);
			}
		}
		else {//未锁定
			resp = nameLoginProc(session, user, loginName, password);
		}
		return resp;
	}
	//用户名密码登录
	private ResponseInfo nameLoginProc(HttpSession session, SUser user, String loginName, String password) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		int n = loginService.verifyUser(session, loginName, password);
		if (n == 0) {
			resp.setCode(200);
			SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
			resp.setData(host);
			System.out.println("getMaxInactiveInterval="+request.getSession().getMaxInactiveInterval());
			logUtil.appendLog(request, "", "用户名密码登录系统", "登录成功", Constant.RESULT_SUCCESS_CODE);
			if (password.equals("123456")) {//初始密码，转到修改密码界面
				resp.setMsg("orign");
			}
			else {
				SLoginInfo loginInfo = loginInfoService.getLoginInfo(host.getId());
				if (loginInfo != null) {
					//登录成功，密码错误记录
					loginInfo.setFailNum(0);
					loginInfo.setIsLock("0");
					loginInfo.setLockTime(null);
					loginInfoService.updateLoginInfo(loginInfo, user);
					
					//判断是否距上次更新密码已到7天时间限制
					String lastUpdateTmp = loginInfo.getLastUpdate();
					if (!StringUtils.isEmpty(lastUpdateTmp)) {//上次更新密码时间
						Long lastUpdate = Long.valueOf(lastUpdateTmp);
						long curTime = System.currentTimeMillis();
						if (((curTime-Long.valueOf(lastUpdate))/1000>=7*24*60*60)) {//已到7天，转到修改密码界面
							resp.setMsg("update");
						}
					}
				}
			}
		}
		else {
			resp.setCode(500);
			switch (n) {
			case 1:
				resp.setMsg("请输入用户名");
				break;
			case 2:
				resp.setMsg("请输入密码");
				break;
			case 3:
				//密码错误，记录到数据表
				SLoginInfo loginInfo = loginInfoService.getLoginInfoByName(loginName);
				if (loginInfo != null) {//有锁定记录
					Integer failNum = loginInfo.getFailNum();
					if (StringUtils.isEmpty(failNum))//锁定次数
						failNum = 0;
					if (failNum < 4) {//密码登录尝试次数未到最大限制数，记录失败次数+1
						loginInfo.setFailNum(failNum+1);
						loginInfoService.updateLoginInfo(loginInfo, user);
						resp.setMsg("密码错误,已输错"+(failNum+1)+"次");
					}
					else {//密码登录尝试次数到最大限制数，锁定账号
						loginInfo.setFailNum(failNum+1);
						loginInfo.setIsLock("1");
						loginInfo.setLockTime(System.currentTimeMillis()+"");
						loginInfoService.updateLoginInfo(loginInfo, user);
						resp.setMsg("密码已输错5次，账号被锁定");
					}
				}
				else {//无锁定记录
					loginInfo = new SLoginInfo();
					loginInfo.setFailNum(1);
					loginInfo.setIsLock("0");
					loginInfo.setLockTime(null);
					loginInfoService.updateLoginInfo(loginInfo, user);
					resp.setMsg("密码错误");
				}
				break;
			case 4:
				resp.setMsg("用户暂未同步，请联系项目管理员");
				break;

			default:
				resp.setMsg("登录失败");
				break;
			}
			logUtil.appendLog(request, "", "用户名密码登录系统", "登录失败", Constant.RESULT_FAIL_CODE);
		}
		return resp;
	}
	
	@RequestMapping("/adminLogin")
	public ModelAndView adminLogin() throws IOException {
		ResponseInfo resp = new ResponseInfo();
		UserAttrObj attrobj = new UserAttrObj(request);
//		System.out.println("----------attrobj--------------"+attrobj);
//		String uid = attrobj.getAttribute("UserId");
		String uid = "sysAdmin";
		int n = loginService.verifyUserByUserCode(request.getSession(), uid);
		if (n == 0) {
			logUtil.appendLog(request, "", "单点登录系统", "登录成功", Constant.RESULT_SUCCESS_CODE);
			return new ModelAndView("redirect:/static/manage/frame/main.html");
		}
		else {
			logUtil.appendLog(request, "", "单点登录系统", "登录失败", Constant.RESULT_FAIL_CODE);
			return new ModelAndView("redirect:/templates/view/noPermission.html");
		}
	}
	
	@RequestMapping("/checkLogin")
	@ResponseBody
	public ResponseInfo checkLogin() throws IOException {
		ResponseInfo resp = new ResponseInfo();
//		loginService.verifyUser(request.getSession(), "admin");//直接以管理员登录
		SUser user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		if (user != null) {
			resp.setCode(200);
			resp.setData(user);
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "检测用户登录", "用户认证成功", Constant.RESULT_SUCCESS);
		}
		else {
			resp.setCode(500);
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "检测用户登录", "用户认证失败", Constant.RESULT_FAIL);
		}
		return resp;
	}
	
	@RequestMapping("/userInfo")
	@ResponseBody
	public ResponseInfo userInfo() throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		if (user == null) {
			resp.setCode(500);
			resp.setMsg("用户未登录！");
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "获取用户信息", "获取用户信息失败，用户未登录", Constant.RESULT_FAIL);
		}
		else {
			resp.setCode(200);
			resp.setData(user);
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "获取用户信息", "获取用户信息成功", Constant.RESULT_SUCCESS);
		}
		return resp;
	}
	
//	@RequestMapping("/clearType")
//	@ResponseBody
//	public ResponseInfo clearType() throws IOException {
//		ResponseInfo resp = new ResponseInfo();
//		SUser user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
//		if (user != null) {
//			user.setType("");
//			request.getSession().setAttribute(Constant.SESSION_USER_KEY, user);
//		}
//		return resp;
//	}
	
//	@RequestMapping("/clearAdd")
//	@ResponseBody
//	public ResponseInfo clearAdd() throws IOException {
//		ResponseInfo resp = new ResponseInfo();
//		SUser user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
//		if (user != null) {
//			user.setAdd("");
//			request.getSession().setAttribute(Constant.SESSION_USER_KEY, user);
//		}
//		return resp;
//	}
	
	@RequestMapping("/perInfo")
	@ResponseBody
	public ResponseInfo perInfo(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser user = null;
		if (!StringUtils.isEmpty(id))
			user = userService.getUser(id);
		else
			user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		if (user == null) {
			resp.setCode(500);
			resp.setMsg("用户未登录！");
		}
		else {
			resp.setCode(200);
			if (user.getRoles() != null) {
				StringBuffer sbf = new StringBuffer();
				for (SRole role : user.getRoles()) {
					if (role.getPermissons() != null) {
						for (SPermission per : role.getPermissons()) {
							sbf.append(per.getName());
							sbf.append(",");
						}
					}
				}
				resp.setData(sbf.toString());
			}
			else
				resp.setData(null);
//			logUtil.appendLog(request, Constant.SYSTEM_INDEX.toString(), "获取用户权限", "获取用户权限成功", Constant.RESULT_SUCCESS);
		}
		return resp;
	}
	
	@RequestMapping("/cas")
	@ResponseBody
	public ResponseInfo cas() {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setMsg("CAS！");
		return resp;
	}
	
	@RequestMapping("/fail")
	@ResponseBody
	public ResponseInfo fail() {
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setMsg("登录失败！");
		return resp;
	}
	
	@RequestMapping("/changePwd")
	@ResponseBody
	public ResponseInfo changePwd(String pwd0, String pwd) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int n = loginService.checkPwd(host.getLoginName(), pwd0);
		if (n != 0) {
			resp.setCode(500);
			resp.setMsg("原密码错误");
			logUtil.appendLog(request, "", "修改密码", "修改失败，原密码错误", Constant.RESULT_FAIL_CODE);
		}
		else {
			HttpSession session = request.getSession(); 
			loginService.changePwd(session, pwd);
			resp.setCode(200);
			resp.setMsg("密码修改成功");
			logUtil.appendLog(request, "", "修改密码", "修改成功", Constant.RESULT_SUCCESS_CODE);
			//记录密码更新时间，七天更新一次
			SLoginInfo loginInfo = loginInfoService.getLoginInfo(host.getId());
			if (loginInfo == null) {
				loginInfo = new SLoginInfo();
			}
			loginInfo.setLastUpdate(System.currentTimeMillis()+"");
			loginInfoService.updateLoginInfo(loginInfo, host);
		}
		return resp;
//		return  new ModelAndView(new RedirectView("http://www.baidu.com"));
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public ResponseInfo logout() throws IOException {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		request.getSession().removeAttribute(Constant.SESSION_USER_KEY);
		String hostId = (host == null?"":host.getId().toString());
		request.getSession().setAttribute("tmpHost", hostId);
		System.out.println("注销成功！");
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setMsg("CAS！");
		logUtil.appendLog(request, "", "用户注销", "用户注销成功", Constant.RESULT_SUCCESS_CODE);
		request.getSession().removeAttribute("tmpHost");
		return resp;
//		return  new ModelAndView(new RedirectView("http://www.baidu.com"));
	}

}
