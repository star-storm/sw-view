package com.taiji.admin.exception;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SUser;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {
	
//	@Autowired
//	private LogUtil logUtil;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
    	System.out.println("!!!!异常：ExceptionHandler-resolveException");
    	SUser user = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
    	if (user!=null)
    		System.out.println("!!!异常：user-"+user.getId()+"-"+user.getName());
//		exceptionLog(request, exception);
        return new ModelAndView("/view/error");
    }
	
	//异常日志
	private void exceptionLog(HttpServletRequest request, Exception exception)  {
		PrintStream pw = null;
		try {
			pw = new PrintStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\errors.txt", true));//确认流的输出文件和编码格式，此过程创建了“test.txt”实例
			exception.printStackTrace(pw);
			pw.close();//关闭流

//			StackTraceElement[] stes = exception.getStackTrace();
//			String className = "";
//			String methodName = "";
//			int lineNumber = 0;
//			String fileName = "";
//			for (StackTraceElement ste : stes) {
//				className = ste.getClassName();
//				methodName = ste.getMethodName();
//				lineNumber = ste.getLineNumber();
//				fileName = ste.getFileName();
//				System.out.println("文件名：" + fileName + ", 类名：" + className + ", 方法名：" + methodName + ", 行号：" + lineNumber);
//				if (className.startsWith("com.taiji"))
//					break;
//				pw.write(ste.);//此处就是需要写入到文本的信息
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Integer modelId = getModelByFileName(fileName);
//		logUtil.appendLog(request, modelId==null?"":String.valueOf(modelId), className, methodName, Constant.RESULT_ERROR, exception.getMessage());
	}
}