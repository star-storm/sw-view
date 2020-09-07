package com.taiji.admin.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * sw-view com.taiji.admin.exception FinalExceptionHandler.java
 *
 * @author hsl
 *
 * 2019年12月1日 上午12:55:41
 *
 * desc:
 */
@RestController
public class FinalExceptionHandler implements ErrorController {
	
//	@Autowired
//	private LogUtil logUtil;
    
//    @Value("${server.context-path}")
//    private String contextPath;
	
    @Override
    public String getErrorPath() {
    	System.out.println("!!!!异常：FinalExceptionHandler-getErrorPath");
        return "/view/error";
    }

    @RequestMapping(value = "/sys-error")
    public Object error(HttpServletResponse resp, HttpServletRequest req) throws IOException {
    	System.out.println(req.getRequestURL());
    	System.out.println("!!!!异常：FinalExceptionHandler-error");
		//异常日志
//    	logUtil.appendLog(req, null, "", "", Constant.RESULT_ERROR, "操作错误");
//    	return "/view/error";
    	resp.sendRedirect("/view/error");
		return null;
    }
	
}