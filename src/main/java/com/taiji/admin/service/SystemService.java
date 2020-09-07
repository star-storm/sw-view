/**
 * 
 */
package com.taiji.admin.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 
 * sw-view com.taiji.admin.service SystemService.java
 *
 * @author hsl
 *
 * 2020年8月15日 下午6:31:18
 *
 * desc:
 *
 */
public interface SystemService {
	
	void writeThreshold(double threshold) throws IOException;
	
	double readThreshold() throws IOException;
	
	List<Map<String,Object>> warn();

	void writeForward(String sessiomTime) throws IOException;

	Integer readForward() throws IOException;

}
