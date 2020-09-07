/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.ODict;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.DictService;

/**
 * 
 * sw-view com.taiji.admin.controller DictController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:29:31
 *
 * desc: 数据字典
 */

@Controller
@RequestMapping("/api/dict")
public class DictController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private DictService dictService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/page")
	@ResponseBody
	public ResponseInfo dictPage(Integer nowPage, Integer pageSize, String descrip, String value, String type) throws IOException {
		PageInfo pageInfo = new PageInfo(nowPage, pageSize);
		long count = dictService.count(descrip, value, type);
		List<ODict> dicts = dictService.dictPage(pageInfo, descrip, value, type);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setCount(count);
		resp.setData(dicts);
		return resp;
	}
	
	/**
	 * 组信息
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo dictList(String type) {
		List<ODict> result = dictService.dictList(type);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(result);
		return resp;
	}
	
	/**
	 * 详情
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getDict(Integer id) throws IOException {
		ODict dict = dictService.getDict(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(dict);
		return resp;
	}

	/**
	 * 更新：新建，编辑
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseInfo updateDict(Integer id, String code, String value, String type, String descrip) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int result = dictService.updateDict(id, code, value, type, descrip, host);
		if (result != 0){
			resp.setCode(500);
			switch (result) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
				resp.setMsg("数据不存在");
				break;
			default:
				resp.setMsg("数据处理错误");
				break;
			}
		}
		else {
			resp.setCode(200);
			resp.setMsg("数据处理成功");
		}
		return resp;
	}
	
	/**
	 * 删除
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseInfo delDict(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = dictService.delDict(id, host);
		if (info.getResult() == 0){
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			switch (info.getResult()) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
			case 3:
				resp.setMsg("数据不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
		}
		return resp;
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping("/delBatch")
	@ResponseBody
	public ResponseInfo delBatch(String ids) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		LogMsgInfo info = dictService.delBatch(ids, host);
		if (info.getResult() == 0){
			resp.setCode(200);
		}
		else {
			resp.setCode(500);
			switch (info.getResult()) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
			case 3:
				resp.setMsg("数据不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
		}
		return resp;
	}

}
