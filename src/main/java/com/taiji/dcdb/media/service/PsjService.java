/**
 * 
 */
package com.taiji.dcdb.media.service;

import java.util.List;
import java.util.Map;

import com.taiji.admin.model.SUser;

/**
 * 批示件
 * @author Administrator
 *
 */
public interface PsjService {

	/**
	 * 督察件数-批示件统计数
	 */
	List<Map<String, Object>> pishiCount(String year, String area, SUser host);

	/**
	 * 督察件数-批示件列表
	 */
	List<Map<String, Object>> pishiList(String year, String type, String status, String area, SUser host);
	
	/**
	 * 计数
	 */
	List<String> count(String year, SUser host);
	
	/**
	 * 列表
	 */
	List<String> psjList(String year, String type, SUser host);

	/**
	 * 详情
	 */
	List<Map<String, Object>> model(String id, SUser host);

}
