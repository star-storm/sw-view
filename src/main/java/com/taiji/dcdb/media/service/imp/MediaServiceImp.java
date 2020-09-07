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
import com.taiji.dcdb.media.service.MediaService;

/**
 * 媒体
 * @author Administrator
 *
 */
@Service
public class MediaServiceImp implements MediaService {

    @Autowired
    @Qualifier("dcdbJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

	/**
	 * 督察件数-媒体统计数
	 */
	@Override
	public List<Map<String,Object>> mediaCount(String year, String area, SUser host) {
//		return PsjDbUtil.psjCount(year);//Constant.psjCountSql(year)
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.mediaCountSql(year, area));
		return list;
	}

	/**
	 * 督察件数-媒体列表
	 */
	@Override
	public List<Map<String,Object>> mediaList(String year, String type, String status, String area, SUser host) {
//		List<String> result = DcdbDbUtil.dcdbStatusList(year, status, type, area);
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.mediaListSql(year, type, status, area));
		return list;
	}

	/**
	 * 媒体反映问题列表
	 */
	@Override
	public List<String> mediaWentiList(String year, SUser host) {
//		List<String> result = MediaDbUtil.mediaFankuiList(year);
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.mediaWentiListSql(year));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String id = "",name = "",status="",date="";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key)==null?"":map.get(key).toString();
				if (key.equals("id"))
					id = value;
				if (key.equals("name"))
					name = value;
				if (key.equals("handlestatus"))
					status = value;
				if (key.equals("date"))
					date = value;
			}
			result.add(sbf.append(id).append("|").append(name).append("|").append(status).append("|").append(date).toString());
			sbf.setLength(0);
		}
		return result;
	}

	/**
	 * 反馈计数
	 */
	@Override
	public long mediaFankuiCount(String year, SUser host) {
//		return MediaDbUtil.mediaFankuiCount(year);
		@SuppressWarnings("rawtypes")
		List list = jdbcTemplate.queryForList(Constant.mediaFankuiCountSql(year), Long.class);
		return list.size()>0?((Long) list.get(0)):0;
	}

	/**
	 * 反馈列表
	 */
	@Override
	public List<String> mediaFankuiList(String year, SUser host) {
//		List<String> result = MediaDbUtil.mediaFankuiList(year);
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.mediaFankuiListSql(year));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String id = "",name = "",date="";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key)==null?"":map.get(key).toString();
				if (key.equals("id"))
					id = value;
				if (key.equals("name"))
					name = value;
				if (key.equals("date"))
					date = value;
			}
			result.add(sbf.append(id).append("|").append(name).append("|").append(date).toString());
			sbf.setLength(0);
		}
		return result;
	}

	/**
	 * 核查已办结计数
	 */
	@Override
	public long mediaHechaBanJieCount(String year, SUser host) {
//		return MediaDbUtil.mediaHechaBanJieCount(year);
		@SuppressWarnings("rawtypes")
		List list = jdbcTemplate.queryForList(Constant.mediaHechaBanJieCountSql(year), Long.class);
		return list.size()>0?((Long) list.get(0)):0;
	}

	/**
	 * 核查已办结列表
	 */
	@Override
	public List<String> mediaHechaBanJieList(String year, SUser host) {
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Constant.mediaHechaBanJieListSql(year));
		List<String> result = new ArrayList<>();
		StringBuffer sbf = new StringBuffer();
		for (Map<String,Object> map : list) {
			String id = "",name = "",date="";
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key)==null?"":map.get(key).toString();
				if (key.equals("id"))
					id = value;
				if (key.equals("name"))
					name = value;
				if (key.equals("date"))
					date = value;
			}
			result.add(sbf.append(id).append("|").append(name).append("|").append(date).toString());
			sbf.setLength(0);
		}
		return result;
	}

	/**
	 * 媒体详情
	 */
	@Override
	public List<Map<String, Object>> mediaModel(String id, SUser host) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(Constant.mediaModelSql(id));
		return list;
	}

}
