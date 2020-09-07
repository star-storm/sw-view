/**
 * 
 */
package com.taiji.admin.service.imp;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.taiji.admin.service.SystemService;
import com.taiji.admin.utils.PropertiesUtil;

/**
 * 
 * sw-view com.taiji.admin.service.imp DcjServiceImp.java
 *
 * @author hsl
 *
 * 2020年8月15日 下午6:27:11
 *
 * desc:
 *
 */
@Service
public class SystemServiceImp implements SystemService {

    @Autowired
    @Qualifier("jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

	/**
	 * 设置表空间阈值 
	 * @throws IOException 
	 */
	@Override
	public void writeThreshold(double threshold) throws IOException {
		String name = "config/sys.properties";
		String key = "threshold";
		PropertiesUtil.writeProperties(name, key, threshold+"");
	}

	/**
	 * 读取表空间阈值 
	 * @throws IOException 
	 */
	@Override
	public double readThreshold() throws IOException {
		String name = "config/sys.properties";
		String key = "threshold";
		String value = PropertiesUtil.readProperties(name, key);
		return Double.valueOf(value);
	}

	/**
	 * 表空间预警 
	 */
	@Override
	public List<Map<String,Object>> warn() {
		String sql = "select sum(table_rows) as recCount, "
			+ " sum(truncate(data_length/1024/1024, 2)) as dataSize, "
			+ " sum(truncate(index_length/1024/1024, 2)) as indexSize "
			+ " from information_schema.tables "
			+ " where table_schema='sw-view' ";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	/**
	 * 设置重定向 
	 * @throws IOException 
	 */
	@Override
	public void writeForward(String sessiomTime) throws IOException {
		String name = "config/sys.properties";
		String key = "sessionTimeout";
		PropertiesUtil.writeProperties(name, key, sessiomTime+"");
	}

	/**
	 * 读取重定向
	 * @throws IOException 
	 */
	@Override
	public Integer readForward() throws IOException {
		String name = "config/sys.properties";
		String key = "sessionTimeout";
		String value = PropertiesUtil.readProperties(name, key);
		return Integer.valueOf(value);
	}

}
