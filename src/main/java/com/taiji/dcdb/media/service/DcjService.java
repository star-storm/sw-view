/**
 * 
 */
package com.taiji.dcdb.media.service;

import java.util.List;
import java.util.Map;

import com.taiji.admin.model.SUser;

/**
 * 督查件
 * @author Administrator
 *
 */
public interface DcjService {
	
	/**
	 * 计数
	 */
	List<Map<String, Object>> dcjCount(String year, String area, SUser host);
	
	/**
	 * 列表
	 */
	List<Map<String, Object>> dcjList(String year, String type, String status, String area, SUser host);
	/**
	 * 全列表
	 */
	List<Map<String, Object>> dcjAllList(String year, String status, SUser host);
	
	/**
	 * 列表
	 */
	@SuppressWarnings("rawtypes")
	List<List> dcdbList(String year, String area, String areaName, SUser host);
	
	/**
	 * 状态列表
	 */
	List<String> dcdbStatusList(String year, String status, String type, String area, SUser host);
	
	/**
	 * 区域统计
	 */
	List<String> dcjsAreaCount(String year, String area, SUser host);
	
	/**
	 * 区域列表
	 */
	List<String> dcdbAreaList(String year, String status, String type, String areaName, SUser host);

	/**
	 * 详情
	 */
	List<Map<String, Object>> dcdbModel(String id, SUser host);

}
