	/**
 * 
 */
package com.taiji.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.LoginService;

/**
 * 
 * sw-view com.taiji.admin.controller PageController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:39:43
 *
 * desc:
 */

@Controller
@RequestMapping("/view")
public class PageController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SUserMapper userMapper;
	
	@Autowired
	LoginService loginService;

	/**
	 * 登录页
	 */
	@RequestMapping("/login")
	public String login() {
		return "view/login";
	}
	
	/**
	 * 主页
	 */
	@RequestMapping("/index")
	public String index() {
		return "view/login";
	}
	
	/**
	 * 主页
	 */
	@RequestMapping("/main")
	public String main() {
		return "view/frame/main";
	}
	
	/**
	 * 短信
	 */
	@RequestMapping("/msg")
	public String msg() {
		return "msg/frame/main";
	}
	
	/**
	 * 错误页面
	 */
	@RequestMapping("/error")
	public String error() {
		return "view/error";
	}
	
	/**
	 * 无权限页面
	 */
	@RequestMapping("/nopermission")
	public String nopermission() {
		return "view/noPermission";
	}
	
	/**
	 * 组织列表
	 */
	@RequestMapping("/org/list")
	public String orgPage() {
		return "view/org/list";
	}
	
	/**
	 * 用户列表
	 */
	@RequestMapping("/user/list")
	public String userPage() {
		return "view/user/list";
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/role/list")
	public String rolePage() {
		return "view/role/list";
	}
	
	/**
	 * 权限列表
	 */
	@RequestMapping("/permission/list")
	public String permissionPage() {
		return "view/permission/list";
	}
	
	/**
	 * 应用列表
	 */
	@RequestMapping("/app/list")
	public String appPage() {
		return "view/app/list";
	}
	
	/**
	 * 字典列表
	 */
	@RequestMapping("/dict/list")
	public String dictPage() {
		return "view/dict/list";
	}
	
	/**
	 * 日志列表
	 */
	@RequestMapping("/log/list")
	public String logPage() {
		return "view/log/list";
	}

	/**
	 * 通讯录详情
	 */
	@RequestMapping("/txl/detail/{code}")
	public ModelAndView txlDetail(@PathVariable String code) {
		int t = loginService.verifyUserByUserCode(request.getSession(), code);
		if (t != 0)
			return new ModelAndView("/view/error");
		SUser user = userMapper.selectByUserCode(code);
		if ((user == null) || (user.getId() == null))
			return new ModelAndView("/view/error");
		return new ModelAndView("redirect:/templates/view/txl/detail.html?id="+user.getId());
	}
	
	/**
	 * test
	 */
	@RequestMapping("/test")
	public ModelAndView test() {
//		return new ModelAndView("redirect:/view/login");
		return new ModelAndView("/view/login");
	}

}
