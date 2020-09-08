/**
 * 
 */
package com.taiji.admin.service.base;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * sw-view com.taiji.admin.service.base DownloadService.java
 *
 * @author hsl
 *
 * 2020年1月9日 下午12:23:22
 *
 * desc:
 */
public interface DownloadService {
	
	/**
	 * 导出日志 
	 */
	public File exportLog(HttpServletRequest request, String modelId, String content, String userName, String startTime, String endTime)
			throws InvalidFormatException, IOException;

}
