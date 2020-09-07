package com.taiji.admin.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.taiji.admin.common.ResponseInfo;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
//	@Autowired
//	private LogUtil logUtil;

	/**
	 * 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
    	System.out.println("!!!!异常：GlobalExceptionHandler-handleExceptionInternal");
		return new ResponseEntity<Object>("", HttpStatus.EXPECTATION_FAILED);

	}

	/**
	 * 默认的异常处理方法
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseInfo ExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
		System.out.println("!!!!异常：GlobalExceptionHandler-jsonHandler");
		//异常日志
//		exceptionLog(request, exception);
		exception.printStackTrace();
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(500);
//		resp.setMsg(exception.getMessage());
		resp.setMsg("操作失败");
		return resp;
	}
	
	//异常日志
	@SuppressWarnings("unused")
	private void exceptionLog(HttpServletRequest request, Exception exception) throws IOException {
		StackTraceElement[] stes = exception.getStackTrace();
		String className = "";
		String methodName = "";
//		int lineNumber = 0;
		String fileName = "";
		for (StackTraceElement ste : stes) {
			className = ste.getClassName();
			methodName = ste.getMethodName();
//			lineNumber = ste.getLineNumber();
			fileName = ste.getFileName();
//			System.out.println("文件名：" + fileName + ", 类名：" + className + ", 方法名：" + methodName + ", 行号：" + lineNumber);
			if (className.startsWith("com.taiji"))
				break;
		}
//		logUtil.appendLog(request, modelId==null?"":String.valueOf(modelId), className, methodName, Constant.RESULT_ERROR, exception.getMessage());
	}
}