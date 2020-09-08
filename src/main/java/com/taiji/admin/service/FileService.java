/**
 * 
 */
package com.taiji.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.model.Plupload;
import com.taiji.admin.model.SUser;
import com.taiji.admin.model.TFile;

/**
 * 
 * sw-view com.taiji.admin.service FileService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:22:13
 *
 * desc:
 */
public interface FileService {
	
	/**
	 * 上传文件
	 */
	public int upFile(MultipartFile file, HttpServletRequest request, SUser host) throws Exception;
	
	/**
	 * 获取文件信息
	 */
	public TFile getFile(Integer id);

	/**
	 * 删除用户
	 */
	LogMsgInfo delFile(Integer id, SUser host);
	
	/**
	 * 检测文件存在
	 */
	boolean checkFile(String fid);
	
	/**
	 * 获取文件
	 */
	boolean getFileTest(HttpServletRequest request, HttpServletResponse response, String fid) throws Exception;

	/**
	 * 下载文件
	 */
	int downFile(HttpServletRequest request, HttpServletResponse response, String type, String fid) throws Exception;

	/**
	 * 链接下载文件
	 */
	int hrefdown(HttpServletRequest request, HttpServletResponse response, String fid) throws Exception;
	
	/**
	 * 导出日志
	 */
	int exportLog(HttpServletRequest request, HttpServletResponse response, String modelId, String content, String userName, String startTime, String endTime) throws Exception;

	/**
	 * 分段上传文件
	 */
	int uploadSeperate(Plupload plupload, String type, HttpServletRequest request, SUser host) throws Exception;

}
