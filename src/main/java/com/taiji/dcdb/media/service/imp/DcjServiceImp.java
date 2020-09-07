/**
 * 
 */
package com.taiji.dcdb.media.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.taiji.admin.model.SUser;
import com.taiji.dcdb.media.Constant;
import com.taiji.dcdb.media.service.DcjService;

/**
 * 督查件
 * @author Administrator
 *
 */
@Service
public class DcjServiceImp implements DcjService {

    @Autowired
    @Qualifier("dcdbJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

	/**
	 * 督察件数-督查件统计数
	 */
	@Override
	public List<Map<String,Object>> dcjCount(String year, String area, SUser host) {
//		return PsjDbUtil.psjCount(year);//Constant.psjCountSql(year)
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.dcjCountSql(year, area));
		return list;
	}

	/**
	 * 列表
	 */
	@Override
	public List<Map<String,Object>> dcjList(String year, String type, String status, String area, SUser host) {
//		List<String> result = DcdbDbUtil.dcdbStatusList(year, status, type, area);
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.dcjListSql(year, type, status, area));
		return list;
	}
	/**
	 * 全列表
	 */
	public List<Map<String, Object>> dcjAllList(String year, String status, SUser host) {
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.dcjAllListSql(year, status));
		return list;
	}

	/**
	 * 列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<List> dcdbList(String year, String area, String areaName, SUser host) {
		List<Map<String, Object>> allList = jdbcTemplate.queryForList(Constant.dcjsListSql(year, Constant.DCDB_STATUS_ALL_CODE, area, areaName));
		List<Map<String, Object>> endList = jdbcTemplate.queryForList(Constant.dcjsListSql(year, Constant.DCDB_STATUS_END_CODE, area, areaName));
		List<Map<String, Object>> procList = jdbcTemplate.queryForList(Constant.dcjsListSql(year, Constant.DCDB_STATUS_PROC_CODE, area, areaName));
		List<List> result = new ArrayList<>();
		List items = null;
		for (int i=0; i<allList.size(); i++) {
			items = new ArrayList<>();
			Map<String, Object> map1 = allList.get(i);
			items.add(map1.get("name"));
			items.add(map1.get("num"));
			Map<String, Object> map2 = endList.get(i);
			items.add(map2.get("num"));
			Map<String, Object> map3 = procList.get(i);
			items.add(map3.get("num"));
			result.add(items);
		}
		return result;
	}

	/**
	 * 列表
	 */
	@Override
	public List<String> dcdbStatusList(String year, String status, String type, String area, SUser host) {
//		List<String> result = DcdbDbUtil.dcdbStatusList(year, status, type, area);
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.dcjsStatusListSql(year, status, type, area));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String prjid = "",title = "",handlestatus = "";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key).toString();
				if (key.equals("prjid"))
					prjid = value;
				if (key.equals("title"))
					title = value;
				if (key.equals("handlestatus"))
					handlestatus = value;
			}
			result.add(sbf.append(prjid).append("|").append(title).append("|").append(handlestatus).toString());
			sbf.setLength(0);
		}
		return result;
	}
	
	/**
	 * 区域统计
	 */
	@Override
	public List<String> dcjsAreaCount(String year, String area, SUser host) {
//		return PsjDbUtil.psjCount(year);//Constant.psjCountSql(year)
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.dcjsAreaCountSql(year, area));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String num = "",name = "";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key).toString();
				if (key.equals("name")) {
					if (area.equals("0"))
						name = value.replace("区委", "区");
					else
						name = value;
				}
				if (key.equals("num"))
					num = value;
			}
			result.add(sbf.append(name).append("|").append(num).toString());
			sbf.setLength(0);
		}
		return result;
	}

	/**
	 * 区域列表
	 */
	@Override
	public List<String> dcdbAreaList(String year, String status, String type, String areaName, SUser host) {
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.dcjsAreaListSql(year, status, type, areaName));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String prjid = "",title = "",handlestatus = "";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key).toString();
				if (key.equals("prjid"))
					prjid = value;
				if (key.equals("title"))
					title = value;
				if (key.equals("handlestatus"))
					handlestatus = value;
			}
			result.add(sbf.append(prjid).append("|").append(title).append("|").append(handlestatus).toString());
			sbf.setLength(0);
		}
		return result;
	}
	
	/**
	 * 详情
	 */
	@Override
	public List<Map<String, Object>> dcdbModel(String id, SUser host) {
//		return DcdbDbUtil.dcdbModel(id);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(Constant.dcjsModelSql(id));
		return list;
	}

}
