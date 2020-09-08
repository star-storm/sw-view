/**
 * 
 */
package com.taiji.admin.service;

import java.util.List;

import com.taiji.admin.common.PageInfo;
import com.taiji.admin.model.SLog;

/**
 * 
 * sw-view com.taiji.admin.service SLogService.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午10:22:31
 *
 * desc:
 */
public interface SLogService {
	
	/**
	 * 计数
	 */
	long count(String modelId, String content, String userName, String startTime, String endTime, Integer roleId);
	
	/**
	 * 列表
	 */
	List<SLog> logPage(PageInfo pageInfo, String modelId, String content, String userName, String startTime, String endTime, Integer roleId);
	
	/**
	 * 所有数据
	 */
	List<SLog> logDatas(String modelId, String content, String userName, String startTime, String endTime,
			Integer roleId);
	
	/**
	 * 详情
	 */
	SLog getLog(Integer id);

	/**
	 * 更新：新建，编辑
	 */
	int addLog(String modelId, String userId, String option, String content, String result);

}
