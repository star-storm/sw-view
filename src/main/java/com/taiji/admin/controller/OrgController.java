/**
 * 
 */
package com.taiji.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.common.ResponseInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SOrg;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SOrgService;

import net.sf.json.JSONArray;

/**
 * 
 * sw-view com.taiji.admin.controller OrgController.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:38:13
 *
 * desc:
 */

@RestController
@RequestMapping("/api/org")
public class OrgController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private SOrgService orgService;
	
//	@Autowired
//	private LogUtil logUtil;
	
	/**
	 * 列表
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResponseInfo orgPage(Integer nowPage, Integer pageSize, String id, String name, String code) throws IOException {
		PageInfo pageInfo = new PageInfo(nowPage, pageSize);
		long count = orgService.count(id, name, code);
		List<SOrg> orgs = orgService.orgPage(pageInfo, id, name, code);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setCount(count);
		resp.setData(orgs);
//		logUtil.appendLog(request, "0", "查询组织信息", logUtil.appendParam("", name), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 组织基本信息
	 * @throws IOException 
	 */
	@RequestMapping("/model")
	@ResponseBody
	public ResponseInfo getOrg(Integer id) throws IOException {
		SOrg org = orgService.getOrg(id);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(org);
//		logUtil.appendLog(request, "0", "查询组织详情", logUtil.appendParam(String.valueOf(id), org.getName()), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 组织基本信息
	 * @throws IOException 
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public ResponseInfo orgDetail(String code) throws IOException {
		SOrg org = orgService.selectByCode(code);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(org);
//		logUtil.appendLog(request, "0", "查询组织详情", logUtil.appendParam(String.valueOf(code), org.getName()), Constant.RESULT_SUCCESS);
		return resp;
	}

	/**
	 * 更新组织：新建，编辑
	 * @throws IOException 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseInfo updateOrg(String json) throws IOException {
//		String tag = "编辑组织信息";
//		if (StringUtils.isEmpty(id))
//			tag = "新建组织信息";
		ResponseInfo resp = new ResponseInfo();
		SOrg org = SOrg.jsonToSOrg(json);
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int result = orgService.updateOrg(org, host);
		if (result == 0){
			resp.setCode(200);
			resp.setMsg("操作成功");
//			logUtil.appendLog(request, "0", tag, logUtil.appendParam(String.valueOf(id), name), Constant.RESULT_SUCCESS);
		}
		else {
			resp.setCode(500);
			switch (result) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
				resp.setMsg("数据不存在");
				break;
			case 3:
				resp.setMsg("所属组织不能是当前组织或当前组织的子类");
				break;
			default:
				break;
			}
//			logUtil.appendLog(request, "0", tag, logUtil.appendParam(String.valueOf(id), name), Constant.RESULT_FAIL);
		}
		return resp;
	}
	
	/**
	 * 删除组织
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseInfo delOrg(Integer id) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int result = orgService.delOrg(id, host);
		if (result == 0){
			resp.setCode(200);
//			logUtil.appendLog(request, "0", "删除组织信息", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_SUCCESS);
		}
		else {
			resp.setCode(500);
			switch (result) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
				resp.setMsg("有用户有该组织，不能删除");
				break;
			case 3:
				resp.setMsg("组织不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
//			logUtil.appendLog(request, "0", "删除组织信息", logUtil.appendParam(String.valueOf(id), ""), Constant.RESULT_FAIL);
		}
		return resp;
	}
	
	/**
	 * 批量删除组织
	 * @throws IOException 
	 */
	@RequestMapping("/delBatch")
	@ResponseBody
	public ResponseInfo delBatch(String ids) throws IOException {
		ResponseInfo resp = new ResponseInfo();
		SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
		int result = orgService.delBatch(ids, host);
		if (result == 0){
			resp.setCode(200);
//			logUtil.appendLog(request, "0", "批量删除组织信息", logUtil.appendParam(String.valueOf(ids), ""), Constant.RESULT_SUCCESS_CODE);
		}
		else {
			resp.setCode(500);
			switch (result) {
			case 1:
				resp.setMsg("参数错误");
				break;
			case 2:
				resp.setMsg("有用户有该组织，不能删除");
				break;
			case 3:
				resp.setMsg("组织不存在");
				break;
			default:
				resp.setMsg("处理错误");
				break;
			}
//			logUtil.appendLog(request, "0", "批量删除组织信息", logUtil.appendParam(String.valueOf(ids), ""), Constant.RESULT_FAIL);
		}
		return resp;
	}
	
	/**
	 * 查询子组织
	 */
	@RequestMapping("/subOrgs")
	@ResponseBody
	public ResponseInfo subOrgs(String pid) throws IOException {
		List<SOrg> result = orgService.subOrgs(pid);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(result);
//		logUtil.appendLog(request, "0", "查询全部组织信息", logUtil.appendParam("", ""), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 条件查询组织
	 */
	@RequestMapping("/queryOrgs")
	@ResponseBody
	public ResponseInfo queryOrgs(String name) throws IOException {
		List<SOrg> result = orgService.queryOrgs(name);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(result);
//		logUtil.appendLog(request, "0", "查询全部组织信息", logUtil.appendParam("", ""), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 根据id查询全部子类
	 * @throws IOException 
	 */
	@RequestMapping("/orgChildren")
	@ResponseBody
	public ResponseInfo treeChildren(String p) throws IOException {
		String children = orgService.orgChildren(p);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(children);
//		logUtil.appendLog(request, "0", "查询子组织信息", logUtil.appendParam("", p), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 根据id查询全部父类
	 * @throws IOException 
	 */
	@RequestMapping("/orgParents")
	@ResponseBody
	public ResponseInfo treeParents(String p) throws IOException {
		String parent = orgService.orgParents(p);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(parent);
//		logUtil.appendLog(request, "0", "查询最高父类信息", logUtil.appendParam("", p), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 根据id查询最高级父类
	 * @throws IOException 
	 */
	@RequestMapping("/orgParent")
	@ResponseBody
	public ResponseInfo treeParent(String p) throws IOException {
		String parent = orgService.orgParent(p);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(parent);
//		logUtil.appendLog(request, "0", "查询最高父类信息", logUtil.appendParam("", p), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * 根据id查询下级组织和该组织下用户
	 * @throws IOException 
	 */
	@RequestMapping("/nextLevel")
	@ResponseBody
	public ResponseInfo nextLevel(String name) throws IOException {
		List<Map<String, Object>> parent = orgService.nextLevel(name);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(parent);
//		logUtil.appendLog(request, "0", "查询最高父类信息", logUtil.appendParam("", p), Constant.RESULT_SUCCESS);
		return resp;
	}
	
	/**
	 * ztree-test
	 */
	@RequestMapping("/ztree")
	@ResponseBody
	public ResponseInfo treeData() {
		JSONArray result = new JSONArray();
		String resultStr = "["
				+ "{ id:1, pId:0, name:'根 Root', open:true},"
				+ "{ id:11, pId:1, name:'父节点 1-1', open:true},"
				+ "{ id:111, pId:11, name:'叶子节点 1-1-1'},"
				+ "{ id:112, pId:11, name:'叶子节点 1-1-2'},"
				+ "{ id:113, pId:11, name:'叶子节点 1-1-3'},"
				+ "{ id:114, pId:11, name:'叶子节点 1-1-4'},"
				+ "{ id:12, pId:1, name:'父节点 1-2', open:true},"
				+ "{ id:121, pId:12, name:'叶子节点 1-2-1'},"
				+ "{ id:122, pId:12, name:'叶子节点 1-2-2'},"
				+ "{ id:123, pId:12, name:'叶子节点 1-2-3'},"
				+ "{ id:124, pId:12, name:'叶子节点 1-2-4'},"
				+ "{ id:13, pId:1, name:'父节点 1-3', open:true},"
				+ "{ id:131, pId:13, name:'叶子节点 1-3-1'},"
				+ "{ id:132, pId:13, name:'叶子节点 1-3-2'},"
				+ "{ id:133, pId:13, name:'叶子节点 1-3-3'},"
				+ "{ id:134, pId:13, name:'叶子节点 1-3-4'},"
				+ "]";
		result = JSONArray.fromObject(resultStr);
		ResponseInfo resp = new ResponseInfo();
		resp.setCode(200);
		resp.setData(result);
		return resp;
	}
	
}
