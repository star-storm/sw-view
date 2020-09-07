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
import com.taiji.dcdb.media.service.PsjService;

/**
 * 督查件
 * @author Administrator
 *
 */
@Service
public class PsjServiceImp implements PsjService {

    @Autowired
    @Qualifier("dcdbJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

	/**
	 * 督察件数-批示件统计数
	 */
	@Override
	public List<Map<String,Object>> pishiCount(String year, String area, SUser host) {
//		return PsjDbUtil.psjCount(year);//Constant.psjCountSql(year)
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.pishiCountSql(year, area));
		return list;
	}

	/**
	 * 督察件数-批示件列表
	 */
	@Override
	public List<Map<String,Object>> pishiList(String year, String type, String status, String area, SUser host) {
//		List<String> result = DcdbDbUtil.dcdbStatusList(year, status, type, area);
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.psjListSql(year, type, status, area));
		return list;
	}

	/**
	 * 计数
	 */
	@Override
	public List<String> count(String year, SUser host) {
//		return PsjDbUtil.psjCount(year);//Constant.psjCountSql(year)
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.psjCountSql(year));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String num = "",name = "";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key).toString();
				if (key.equals("name"))
					name = value;
				if (key.equals("num"))
					num = value;
			}
			result.add(sbf.append(name).append("|").append(num).toString());
			sbf.setLength(0);
		}
		return result;
	}

	/**
	 * 列表
	 */
	@Override
	public List<String> psjList(String year, String type, SUser host) {
//		return PsjDbUtil.psjList(year, type);//Constant.psjListSql(year, type)
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.psjListSql(year, type));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String prjid = "",title="",handlestatus = "",date = "";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key)==null?"":map.get(key).toString();
				if (key.equals("prjid"))
					prjid = value;
				if (key.equals("title"))
					title = value;
				if (key.equals("handlestatus"))
					handlestatus = value;
				if (key.equals("date"))
					date = value;
			}
			result.add(sbf.append(prjid).append("|").append(title).append("|").append(handlestatus).append("|").append(date).toString());
			sbf.setLength(0);
		}
		return result;
	}

	/**
	 * 详情
	 */
	@Override
	public List<Map<String, Object>> model(String id, SUser host) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(Constant.psjModelSql(id));
		return list;
	}

}
