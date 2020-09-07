/**
 * 
 */
package com.taiji.dcdb.media.service;

import java.util.List;
import java.util.Map;

import com.taiji.admin.model.SUser;

/**
 * 媒体
 * @author Administrator
 *
 */
public interface MediaService {

	/**
	 * 督察件数-媒体统计数
	 */
	List<Map<String, Object>> mediaCount(String year, String area, SUser host);
	
	/**
	 * 督察件数-媒体列表
	 */
	List<Map<String, Object>> mediaList(String year, String type, String status, String area, SUser host);
	
	/**
	 * 计数
	 */
	long mediaFankuiCount(String year, SUser host);
	long mediaHechaBanJieCount(String year, SUser host);
	
	/**
	 * 列表
	 */
	List<String> mediaWentiList(String year, SUser host);
	List<String> mediaFankuiList(String year, SUser host);
	List<String> mediaHechaBanJieList(String year, SUser host);
	
	/**
	 * 详情
	 */
	List<Map<String, Object>> mediaModel(String id, SUser host);

}
