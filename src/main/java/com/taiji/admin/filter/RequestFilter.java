/**
 * 
 */
package com.taiji.admin.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.taiji.admin.constant.Constant;

import net.sf.json.JSONObject;

/**
 * 
 * sw-view com.taiji.admin.filter RequestFilter.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:44:44
 *
 * desc:
 */
public class RequestFilter implements Filter {
	
    @Value("${loginCheck.directUris}")
    private String directUris;
    
//    @Value("${server.context-path}")
//    private String contextPath;
    
	private List<String> directUriList;
	
	private Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	public void destroy() {
		
	}

	@SuppressWarnings("static-access")
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		//非登录验证资源
		boolean noLogCheck = false;
		String uri = request.getRequestURI();
		System.out.println("uri = " + uri);
//		logger.info("uri = " + uri);
		for (String s : directUriList) {
			if (uri.startsWith(s)) {
				noLogCheck = true;
				break;
			}
		}
		String unloginUrl = "/view/login";
		if (request.getSession().getAttribute(Constant.SESSION_USER_KEY) == null && !noLogCheck) {
			//ajxs请求
			if (uri.startsWith("/api") || uri.startsWith("/file") || uri.startsWith("/statistic")
					|| uri.startsWith("/perInfo") || uri.startsWith("/setRoleIndex")
					|| uri.startsWith("/userInfo") || uri.startsWith("/changePwd")) {
//				response.addHeader("REDIRECT", "REDIRECT");
//				response.addHeader("LOCATION", unloginUrl);//重定向地址 
	
				response.setCharacterEncoding("UTF-8");  
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = null ;
				JSONObject res = new JSONObject();
				res.put("success","false");
				res.put("code","501");
				res.put("msg","用户已注销，请重新登录");
				out = response.getWriter();
				out.append(res.toString());
			}
			else {//页面请求
				try {
					if (uri.equals("/view/main"))
						Thread.currentThread().sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				response.sendRedirect(unloginUrl);
//				response.sendRedirect("/view/login");
			}
		}
		else
			chain.doFilter(request, response);
//		chain.doFilter(request, response);
//		System.out.println("filter: resp.stat = " + response.getStatus());
	}

	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("directUris = " + directUris);
		if (directUris != null && !directUris.equals("")) {
			directUriList = Arrays.asList(directUris.split(";"));
//			for (String uri : directUriList) {
//				System.out.println("uri = " + uri);
//			}
		}
	}

}
