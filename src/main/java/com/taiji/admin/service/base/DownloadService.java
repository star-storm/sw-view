/**
 * 
 */
package com.taiji.admin.service.base;

import java.io.File;
import java.io.IOException;

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
	 * 下载通讯录
	 * @param type 类型（1:全员; 2:机构; 3:个人）
	 * @param id（机构ID; 人员ID）
	 * @return excel文件
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public File createTxl(String type, Integer id, String name, String officePhone, String salePhone) throws InvalidFormatException, IOException;

}
