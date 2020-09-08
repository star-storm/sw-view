/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.Plupload;
import com.taiji.admin.model.SUser;
import com.taiji.admin.model.TFile;
import com.taiji.admin.service.FileService;
import com.taiji.admin.utils.JsonObjectUtil;

import net.sf.json.JSONObject;

/**
 * 
 * sw-view com.taiji.admin.controller FileController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:34:49
 *
 * desc:
 */

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	private FileService fileService;
	
//	@Autowired
//	private LogUtil logUtil;
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value = "/upload", method = {RequestMethod.GET,RequestMethod.POST}, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String upload(@RequestParam("file")  MultipartFile file, HttpServletRequest request) throws Exception {
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int result = fileService.upFile(file, request, host);
		ResponseInfo resp = new ResponseInfo();
		switch (result) {
		case -1:
			resp.setCode(500);
			resp.setMsg("文件数据错误！请重试");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", file.getOriginalFilename()), Constant.RESULT_FAIL);
			break;
		case -2:
			resp.setCode(500);
			resp.setMsg("文件已存在！");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", file.getOriginalFilename()), Constant.RESULT_FAIL);
			break;
		case -3:
			resp.setCode(500);
			resp.setMsg("文件类型错误！请上传压缩文件");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", file.getOriginalFilename()), Constant.RESULT_FAIL);
			break;
		case -4:
			resp.setCode(500);
			resp.setMsg("解压文件失败");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", file.getOriginalFilename()), Constant.RESULT_FAIL);
			break;
		case -5:
			resp.setCode(500);
			resp.setMsg("文件处理失败");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", file.getOriginalFilename()), Constant.RESULT_FAIL);
			break;
			
		default:
			resp.setCode(200);
			resp.setData(result);
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", file.getOriginalFilename()), Constant.RESULT_SUCCESS);
			break;
		}
		return JSONObject.fromObject(resp).toString();
	}

	/**
	 * 检测文件存在
	 */
	@RequestMapping(value = "/check", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseInfo chceck(String fid) throws Exception {
		boolean result = fileService.checkFile(fid);
		ResponseInfo resp = new ResponseInfo();
		resp.setData(result);
		if (result) {
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			resp.setMsg("目标文件不存在");
		}
		return resp;
	}
	
	/**
	 * 获取文件
	 */
	@RequestMapping(value = "/get/{fid}", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseInfo get(@PathVariable("fid") String fid) throws Exception {
		Boolean result = fileService.getFileTest(request, response, fid);
		ResponseInfo resp = new ResponseInfo();
		if (result) {
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			resp.setMsg("目标文件不存在");
		}
		return resp;
	}
	@RequestMapping(value = "/get1", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseInfo get1(String fid) throws Exception {
		Boolean result = fileService.getFileTest(request, response, fid);
		ResponseInfo resp = new ResponseInfo();
		if (result) {
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			resp.setMsg("目标文件不存在");
		}
		return resp;
	}
	@RequestMapping(value = "/get2", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseInfo get2( @RequestBody  String json) throws Exception {
		Boolean result = fileService.getFileTest(request, response, JsonObjectUtil.getValueByName(json, "id").toString());
		ResponseInfo resp = new ResponseInfo();
		if (result) {
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			resp.setMsg("目标文件不存在");
		}
		return resp;
	}
	@RequestMapping(value = "/get3", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseInfo get3(@RequestBody TFile tf) throws Exception {
		Boolean result = fileService.getFileTest(request, response, tf.getId().toString());
		ResponseInfo resp = new ResponseInfo();
		if (result) {
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			resp.setMsg("目标文件不存在");
		}
		return resp;
	}
	@RequestMapping(value = "/get4", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseInfo get4(@RequestBody TFile tf, @RequestParam String id) throws Exception {
		Boolean result = fileService.getFileTest(request, response, tf.getId().toString());
		ResponseInfo resp = new ResponseInfo();
		if (result) {
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			resp.setMsg("目标文件不存在");
		}
		return resp;
	}
	
	/**
	 * 下载文件
	 */
	@RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public ResponseInfo download(HttpServletResponse response, @RequestHeader String referer, String modelId, String content, String userName, String startTime, String endTime) throws Exception {
		System.out.println(referer);
		int result = fileService.exportLog(request, response, modelId, content, userName, startTime, endTime);
		ResponseInfo resp = new ResponseInfo();
		switch (result) {
		case 0:
			resp.setCode(200);
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "下载文件", logUtil.appendParam(fid, ""), Constant.RESULT_SUCCESS);
			return null;
		case 1:
			resp.setCode(500);
			resp.setMsg("文件不存在");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "下载文件", logUtil.appendParam(fid, ""), Constant.RESULT_FAIL);
			return resp;
			
		default:
			resp.setCode(500);
			resp.setMsg("操作失败");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "下载文件", logUtil.appendParam(fid, ""), Constant.RESULT_FAIL);
			return resp;
		}
	}

	/**
	 * 链接下载文件
	 */
	@RequestMapping(value = "/hrefdown", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseInfo hrefdown(HttpServletResponse response, String modelId, String content, String userName, String startTime, String endTime) throws Exception {
		int result = fileService.exportLog(request, response, modelId, content, userName, startTime, endTime);
		ResponseInfo resp = new ResponseInfo();
		switch (result) {
		case 0:
			resp.setCode(200);
			return null;
		case 1:
			resp.setCode(500);
			resp.setMsg("文件不存在");
			return resp;
			
		default:
			resp.setCode(500);
			resp.setMsg("操作失败");
			return resp;
		}
	}
	
	/**
	 * 删除文件
	 * @throws IOException 
	 */
	@RequestMapping("/del")
	@ResponseBody
	public ResponseInfo delFile(Integer id, String type) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = fileService.delFile(id, host);
		switch (info.getResult()) {
		case 0:
			resp.setCode(200);
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "删除文件", logUtil.appendParam(info.getId(), info.getName()), Constant.RESULT_SUCCESS);
			break;
		case 1:
			resp.setCode(500);
			resp.setMsg("文件不存在");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "删除文件", logUtil.appendParam(info.getId(), info.getName()), Constant.RESULT_FAIL);
			break;
			
		default:
			resp.setCode(500);
			resp.setMsg("操作失败");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "删除文件", logUtil.appendParam(info.getId(), info.getName()), Constant.RESULT_FAIL);
			break;
		}
		return resp;
	}

	/**
	 * 分片上传文件
	 */
	@RequestMapping(value = "/seperate", method = {RequestMethod.GET,RequestMethod.POST}, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String uploadSpare(Plupload plupload, String type, HttpServletRequest request) throws Exception {
		plupload.setRequest(request);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int result = fileService.uploadSeperate(plupload, type, request, host);
		ResponseInfo resp = new ResponseInfo();

		switch (result) {
		case -1:
			resp.setCode(500);
			resp.setMsg("文件数据错误！请重试");
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", plupload.getName()), Constant.RESULT_FAIL);
			break;
			
		default:
			resp.setCode(200);
			resp.setData(result);
//			logUtil.appendLog(request, String.valueOf(CommonUtil.modelIndex(type)), "上传文件", logUtil.appendParam("", plupload.getName()), Constant.RESULT_SUCCESS);
			break;
		}
		return JSONObject.fromObject(resp).toString();
	}
	

}
