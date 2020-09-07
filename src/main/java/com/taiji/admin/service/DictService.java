/**
 * 
 */
package com.taiji.admin.service;

import java.util.List;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.ODict;
import com.taiji.admin.model.SUser;

/**
 * 
 * sw-view com.taiji.admin.service DictService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:22:03
 *
 * desc:数据字典
 */
public interface DictService {
	
	
	long count(String descrip, String value, String type);
	
	List<ODict> dictPage(PageInfo pageInfo, String descrip, String value, String type);
	
	/**
	 * 组信息
	 */
	List<ODict> dictList(String type);

	/**
	 * 详情
	 */
	ODict getDict(Integer id);

	/**
	 * 更新：新建，编辑
	 */
	int updateDict(Integer id, String code, String value, String type, String descrip, SUser host);

	/**
	 * 删除
	 */
	LogMsgInfo delDict(Integer id, SUser host);
	/**
	 * 批量删除
	 */
	LogMsgInfo delBatch(String ids, SUser host);
}
