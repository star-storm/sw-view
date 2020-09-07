/**
 * 
 */
package com.taiji.dcdb.media;

import org.springframework.util.StringUtils;

/**
 * @author Administrator
 *
 */
public class Constant {
	
	public final static int DCDB_STATUS_ALL_CODE = 0;//全部
	public final static int DCDB_STATUS_END_CODE = 1;//已办结
	public final static int DCDB_STATUS_PROC_CODE = 2;//办理中
	
	public final static String DCDB_STATUS_ALL_DATA = "全部";
	public final static String DCDB_STATUS_END_DATA = "已办结";
	public final static String DCDB_STATUS_PROC_DATA = "办理中";
	
//	static String limitSql = " limit 120";
	static String limitSql = "";
	
	
	/**
	 * 
	 * 督查件：
	 * 			重点督查事项
	 * 			调研指示事项
	 * 			会议决定事项
	 * 
	 */
	public static String dcjCountSql(String year, String area) {
		String yearParam = "	and DATE_FORMAT(estabprojdate, '%Y') =  '"+year+"' ";
		String areaNameParam = "";
		if (!StringUtils.isEmpty(area))
			areaNameParam = " and p.speciapartyname = '"+(area.endsWith("区")?(area+"委"):area)+"' ";
		String sql = " select businesstypename,handlestatus,count(handlestatus) as num "
				+ " from syncv_dc t, syncv_dc_prc p "
				+ " where t.prjid=p.prjid "
				+ " and t.businesstypename in ('会议决定事项','调研指示事项','重点督查事项') "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ areaNameParam
				+ " group by t.businesstypename,t.handlestatus "
				+ limitSql;
		return sql;
	}
	/**
	 * 
	 * 督查件：
	 * 			领导批示件
	 * 			领导指示事项
	 * 
	 */
	public static String pishiCountSql(String year, String area) {
		String yearParam = "	and DATE_FORMAT(t.estabprojdate, '%Y') =  '"+year+"' ";
		String areaNameParam = "";
		if (!StringUtils.isEmpty(area))
			areaNameParam = " and p.speciapartyname = '"+(area.endsWith("区")?(area+"委"):area)+"' ";
		String sql = " select t.businesstypename,t.handlestatus,count(t.handlestatus) as num "
				+ " from syncv_sp t, syncv_sp_prc p "
				+ " where t.prjid=p.prjid "
				+ " and t.businesstypename in ('领导批示件','领导指示事项') "
				+ " 	AND t.inspectcategoryname IS NOT NULL "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ areaNameParam
				+ " group by t.businesstypename,t.handlestatus "
				+ limitSql;
		return sql;
	}
	/**
	 * 
	 * 督查件：
	 * 			媒体反映问题
	 * 			市民热线
	 * 			其他
	 * 
	 */
	public static String mediaCountSql(String year, String area) {
		String yearParam = "	and DATE_FORMAT(t.publishdate, '%Y') =  '"+year+"' ";
		String areaNameParam = "";
		if (!StringUtils.isEmpty(area))
			areaNameParam = " and p.partyname = '"+(area.endsWith("区")?(area+"委"):area)+"' ";
		String sql = " select t.businesstypename,t.handlestatus,count(t.handlestatus) as num "
				+ " from syncv_mp t, syncv_mp_prc p "
				+ " where t.prjid=p.prjid "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ areaNameParam
				+ " group by t.businesstypename,t.handlestatus "
				+ limitSql;
		return sql;
	}

	/**
	 * 
	 * 全件
	 * 
	 */
	public static String dcjAllListSql(String year, String status) {
		String yearParam1 = "	and DATE_FORMAT(t.estabprojdate, '%Y') =  '"+year+"' ";
		String yearParam2 = "	and DATE_FORMAT(t.publishdate, '%Y') =  '"+year+"' ";
		String statusParam = "";
		if (!StringUtils.isEmpty(status)) {
			statusParam = " and t.handlestatus='"+status+"'";
		}
		String sql = "select u.prjid, u.title, u.handlestatus, u.date, u.type "
				+ " from ( SELECT "
				+ " 	t.prjid, "
				+ " 	t.title, "
				+ " 	t.handlestatus, "
				+ " 	t.estabprojdate as date, "
				+ " 	(case when t.businesstypename='重点督查事项' then 'zddcsx' else 'dcj' end) as type "
				+ " FROM "
				+ " 	syncv_dc t, "
				+ " 	syncv_dc_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
				+ " 	AND t.businesstypename IN ( '会议决定事项', '调研指示事项', '重点督查事项' )  "
				+ (StringUtils.isEmpty(year)?"":yearParam1)
				+ statusParam
				+ " "
				+ " 	union all "
				+ " 	 "
				+ " SELECT "
				+ " 	t.prjid, "
				+ " 	t.title, "
				+ " 	t.handlestatus, "
				+ " 	t.publishdate as date, "
				+ " 	'media' as type "
				+ " FROM "
				+ " 	syncv_mp t, "
				+ " 	syncv_mp_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
				+ (StringUtils.isEmpty(year)?"":yearParam2)
				+ statusParam
				+ " "
				+ " union all "
				+ " 	 "
				+ " SELECT "
				+ " 	t.prjid, "
				+ " 	t.title, "
				+ " 	t.handlestatus, "
				+ " 	t.estabprojdate as date, "
				+ " 	'psj' as type "
				+ " FROM "
				+ " 	syncv_sp t, "
				+ " 	syncv_sp_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
				+ " 	AND t.businesstypename IN ( '领导批示件', '领导指示事项' )  "
				+ " 	AND t.inspectcategoryname IS NOT NULL  "
				+ (StringUtils.isEmpty(year)?"":yearParam1)
				+ statusParam
				+ " ) u "
				+ " 		order by u.date desc " 
				+ limitSql;
		return sql;
	}

	/**
	 * 
	 * 督查件：
	 * 			重点督查事项
	 * 			调研指示事项
	 * 			会议决定事项
	 * 
	 */
	public static String dcjListSql(String year, String type, String status, String area) {
		String yearParam = "	and DATE_FORMAT(t.estabprojdate, '%Y') =  '"+year+"' ";
		String typeParam = "";
		if (!StringUtils.isEmpty(type)) {
			typeParam = " and t.businesstypename='" +type+ "' ";
		}
		String statusParam = "";
		if (!StringUtils.isEmpty(status)) {
			statusParam = " and t.handlestatus='"+status+"'";
		}
		String areaNameParam = "";
		if (!StringUtils.isEmpty(area))
			areaNameParam = " and p.speciapartyname = '"+(area.endsWith("区")?(area+"委"):area)+"' ";
		String sql = " SELECT t.prjid, t.title, t.handlestatus, t.estabprojdate as date "
				+ " from syncv_dc t, syncv_dc_prc p "
				+ " where t.prjid=p.prjid "
//				+ " and businesstypename = '重点督查事项' "
				+ typeParam
				+ statusParam
				+ areaNameParam
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " order by t.estabprojdate desc "
				+ limitSql;
		return sql;
	}
	/**
	 * 
	 * 督查件：
	 * 			领导批示件
	 * 			领导指示事项
	 * 
	 */
	public static String psjListSql(String year, String type, String status, String area) {
		String yearParam = "	and DATE_FORMAT(t.estabprojdate, '%Y') =  '"+year+"' ";
		String typeParam = "";
		if (!StringUtils.isEmpty(type)) {
			typeParam = " and t.businesstypename='" +type+ "' ";
		}
		String statusParam = "";
		if (!StringUtils.isEmpty(status)) {
			statusParam = " and t.handlestatus='"+status+"'";
		}
		String areaNameParam = "";
		if (!StringUtils.isEmpty(area))
			areaNameParam = " and p.speciapartyname = '"+(area.endsWith("区")?(area+"委"):area)+"' ";
		String sql = " select t.prjid, t.title, t.handlestatus, t.estabprojdate as date "
				+ " from syncv_sp t, syncv_sp_prc p "
				+ " where t.prjid=p.prjid "
//				+ " and businesstypename = '领导批示件' "
				+ typeParam
				+ statusParam
				+ areaNameParam
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " 	AND t.inspectcategoryname IS NOT NULL "
				+ " order by t.estabprojdate desc "
				+ limitSql;
		return sql;
	}
	/**
	 * 
	 * 督查件：
	 * 			媒体反映问题
	 * 			市民热线
	 * 			其他
	 * 
	 */
	public static String mediaListSql(String year, String type, String status, String area) {
		String yearParam = "	and DATE_FORMAT(t.publishdate, '%Y') =  '"+year+"' ";
		String typeParam = "";
		if (!StringUtils.isEmpty(type)) {
			if (type.equals("其他")) {
				typeParam = " and t.businesstypename not in ('媒体反映问题','市民热线') ";
			}
			else {
				typeParam = " and t.businesstypename='" +type+ "' ";
			}
		}
		String statusParam = "";
		if (!StringUtils.isEmpty(status)) {
			statusParam = " and t.handlestatus='"+status+"'";
		}
		String areaNameParam = "";
		if (!StringUtils.isEmpty(area))
			areaNameParam = " and p.partyname = '"+(area.endsWith("区")?(area+"委"):area)+"' ";
		String sql = " select t.prjid, t.title, t.handlestatus, t.publishdate as date "
				+ " from syncv_mp t, syncv_mp_prc p "
				+ " where t.prjid=p.prjid "
//				+ " and businesstypename = '领导批示件' "
				+ typeParam
				+ statusParam
				+ areaNameParam
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " order by t.publishdate desc "
				+ limitSql;
		return sql;
	}
	
	
	
	
	
	
	
	
	
	//督查件数列表
	//@status(0:全部;1:已办结;2:办理中)
	public static String dcjsListSql(String year, int status, String area, String areaName) {
		String statusParam = status==DCDB_STATUS_ALL_CODE?"":status==DCDB_STATUS_END_CODE?" and t1.handlestatus='已办结' ":" and t1.handlestatus='办理中' ";
		String aeraParam = "";
		String areaNameParam = "";
		String yearParam = "		AND DATE_FORMAT( t1.estabprojdate, '%Y' ) =  '"+year+"' ";
		if (!StringUtils.isEmpty(area)) {
			if (area.equals("0")) {//16区委
				aeraParam = " and t2.speciapartyname like'%区%' ";
			}
			else {//其他单位
				aeraParam = " and t2.speciapartyname not like'%区%' ";
			}
		}
		else {
			if (!StringUtils.isEmpty(areaName))
				areaNameParam = " and t2.speciapartyname like '%"+areaName+"%' ";
		}
		String sql = "SELECT "
				+ "	p.NAME as name, "
				+ "	count( t.businesstypename ) AS num "
				+ "FROM "
				+ "	business_type p "
				+ "	LEFT JOIN (select t1.businesstypename  from syncv_sp t1, syncv_sp_prc t2 "
				+ "		WHERE t1.prjid=t2.prjid "
//				+ "     and DATE_FORMAT( t1.estabprojdate, '%Y' ) =  '"+year+"' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ "     "+statusParam
				+ "     "+aeraParam
				+ "     "+areaNameParam+") t "
				+ "	ON t.businesstypename = p.NAME "
				+ "GROUP BY "
				+ "	p.NAME "
				+ "ORDER BY "
				+ "	p.sort "
				+ limitSql;
		return sql;
	}
	
	//督查件数列表
	//@status(0:全部;1:已办结;2:办理中)
	public static String dcjsStatusListSql(String year, String status, String type, String area) {
		String statusParam = "";
		if (!StringUtils.isEmpty(status)) {
			statusParam = " and t1.handlestatus='"+status+"'";
		}
		String aeraParam = "";
		String typeParam = "";
		String yearParam = "		AND DATE_FORMAT( t1.estabprojdate, '%Y' ) =  '"+year+"' ";
		if (!StringUtils.isEmpty(type)) {
			typeParam = " and t1.businesstypename= '"+type+"'";
		}
		if (!StringUtils.isEmpty(area)) {
			if (area.equals("0")) {//16区委
				aeraParam = " and t2.speciapartyname like'%区%' ";
			}
			else {//其他单位
				aeraParam = " and t2.speciapartyname not like'%区%' ";
			}
		}
		String sql = "SELECT "
				+ "	p.NAME as name, "
				+ "	count( t.businesstypename ) AS num "
				+ "FROM "
				+ "	business_type p "
				+ "	LEFT JOIN (select t1.businesstypename  from syncv_dc t1, syncv_dc_prc t2 "
				+ "		WHERE t1.prjid=t2.prjid "
//				+ "     and DATE_FORMAT( t1.estabprojdate, '%Y' ) =  '"+year+"' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ "     "+statusParam
				+ "     "+aeraParam
				+ "     "+typeParam+") t "
				+ "	ON t.businesstypename = p.NAME "
				+ "GROUP BY "
				+ "	p.NAME "
				+ "ORDER BY "
				+ "	p.sort "
				+ limitSql;
		return sql;
	}
	
	//督查件数根据状态查询列表
	//@status(0:全部;1:已办结;2:办理中)
	public static String dcjsListSql(String year, String status, String type, String area) {
		String statusParam = "";
		String typeParam = "";
		String aeraParam = "";
		String yearParam = "		AND DATE_FORMAT( t.estabprojdate, '%Y' ) =  '"+year+"' ";
		if (!StringUtils.isEmpty(status)) {
			statusParam = " and t.handlestatus='" +status+ "' ";
		}
		if (!StringUtils.isEmpty(type)) {
			typeParam = " and t.businesstypename='" +type+ "' ";
		}
		if (!StringUtils.isEmpty(area)) {
			if (area.equals("0")) {//16区委
				aeraParam = " and p.speciapartyname like'%区%' ";
			}
			else {//其他单位
				aeraParam = " and p.speciapartyname not like'%区%' ";
			}
		}
		String sql = " select t.prjid, t.title, t.handlestatus from syncv_sp t, syncv_sp_prc p "
				+ "		WHERE t.prjid=p.prjid"
//				+ "     and DATE_FORMAT( t.estabprojdate, '%Y' ) =  '"+year+"' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ "     "+statusParam
				+ "     "+typeParam
				+ "     "+aeraParam
				+ " order by t.publishdate desc "
				+ limitSql;
		return sql;
	}
	
	//督查件数根据区域查询统计
	public static String dcjsAreaCountSql(String year, String area) {
		String aeraParam = "";
		String aeraParamMp = "";
		if (!StringUtils.isEmpty(area)) {
			if (area.equals("0")) {//16区委
				aeraParam = " and p.speciapartyname like'%区%' ";
				aeraParamMp = " and p.partyname like'%区%' ";
			}
			else {//其他单位
				aeraParam = " and p.speciapartyname not like'%区%' ";
				aeraParamMp = " and p.partyname not like'%区%' ";
			}
		}
		String yearParam = "		AND DATE_FORMAT( t.estabprojdate, '%Y' ) =  '"+year+"' ";
		String yearParamMp = "		AND DATE_FORMAT( t.publishdate, '%Y' ) =  '"+year+"' ";
		String sql = " select u.name, sum(u.num) as num from  "
				+ " (SELECT "
				+ " 	p.speciapartyname AS NAME, "
				+ " 	count( p.speciapartyname ) AS num  "
				+ " FROM "
				+ " 	syncv_dc t, "
				+ " 	syncv_dc_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
				+ " and t.businesstypename in ('会议决定事项','调研指示事项','重点督查事项') "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ "     "+aeraParam
				+ " GROUP BY "
				+ " 	p.speciapartyname "
				+ " 	 "
				+ " 	union all "
				+ "  "
				+ " SELECT "
				+ " 	p.partyname AS NAME, "
				+ " 	count( p.partyname ) AS num  "
				+ " FROM "
				+ " 	syncv_mp t, "
				+ " 	syncv_mp_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
				+ (StringUtils.isEmpty(year)?"":yearParamMp)
				+ "     "+aeraParamMp
				+ " GROUP BY "
				+ " 	p.partyname "
				+ " 	 "
				+ " 	union all "
				+ "  "
				+ " SELECT "
				+ " 	p.speciapartyname AS NAME, "
				+ " 	count( p.speciapartyname ) AS num  "
				+ " FROM "
				+ " 	syncv_sp t, "
				+ " 	syncv_sp_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
				+ " and t.businesstypename in ('领导批示件','领导指示事项') "
				+ " AND t.inspectcategoryname IS NOT NULL   "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ "     "+aeraParam
				+ " GROUP BY "
				+ " 	p.speciapartyname) u "
				+ " group by u.name "
				+ " order by num desc "
				+ limitSql;
		return sql;
	}
	
	//督查件数根据区域查询统计
	public static String dcjsAreaCountSql0(String year, String area) {
		String aeraParam = "";
		if (!StringUtils.isEmpty(area)) {
			if (area.equals("0")) {//16区委
				aeraParam = " and p.speciapartyname like'%区%' ";
			}
			else {//其他单位
				aeraParam = " and p.speciapartyname not like'%区%' ";
			}
		}
		String yearParam = "		AND DATE_FORMAT( t.estabprojdate, '%Y' ) =  '"+year+"' ";
		String sql = "SELECT "
				+ "   p.speciapartyname as name, "
				+ "   count(p.speciapartyname) as num "
				+ " FROM "
				+ " 	syncv_sp t, "
				+ " 	syncv_sp_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
//				+ " 	AND DATE_FORMAT( t.estabprojdate, '%Y' ) = '"+year+"' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ "     "+aeraParam
				+ " group by p.speciapartyname"
				+ " order by num desc "
				+ limitSql;
		return sql;
	}
	
	//督查件数根据状态查询列表
	//@status(0:全部;1:已办结;2:办理中)
	public static String dcjsAreaListSql(String year, String status, String type, String areaName) {
		String statusParam = "";
		String typeParam = "";
		String areaNameParam = "";
		if (!StringUtils.isEmpty(status)) {
			statusParam = " and t.handlestatus='" +status+ "' ";
		}
		if (!StringUtils.isEmpty(type)) {
			typeParam = " and t.businesstypename='" +type+ "' ";
		}
		if (!StringUtils.isEmpty(areaName)) {
			areaNameParam = " and p.speciapartyname like '%"+areaName+"%' ";
		}
		String yearParam = "		AND DATE_FORMAT( t.estabprojdate, '%Y' ) =  '"+year+"' ";
		String sql = "SELECT  t.prjid, t.title, t.handlestatus "
				+ " FROM "
				+ " 	syncv_sp t, "
				+ " 	syncv_sp_prc p  "
				+ " WHERE "
				+ " 	t.prjid = p.prjid  "
//				+ " 	AND DATE_FORMAT( t.estabprojdate, '%Y' ) = '"+year+"' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ "     "+statusParam
				+ "     "+typeParam
				+ "     "+areaNameParam
				+ " order by t.publishdate desc "
				+ limitSql;
		return sql;
	}
	
	//督查件数详情
	public static String dcjsModelSql(String id) {
		String sql = " SELECT "
				+ " 	DATE_FORMAT( t.estabprojdate, '%Y-%m-%d') AS estabprojdate, "
				+ " 	t.title, "
				+ " 	p.speciapartyname, "
				+ " 	v.instrleadername, "
				+ " 	t.handlestatus "
				+ " FROM "
				+ " 	syncv_dc t "
				+ "   left join syncv_dc_prc p on t.prjid = p.prjid "
				+ "   left join syncv_instr v on t.prjid = v.prjid "
				+ " WHERE t.prjid = '"+id+"' "
				+ " limit 1";
		return sql;
	}
	
	//媒体-媒体反映问题列表
	public static String mediaWentiListSql(String year) {
		String yearParam = "		AND DATE_FORMAT( t.publishdate, '%Y' ) =  '"+year+"' ";
		String sql = " SELECT "
				+ " 	t.prjid as id, t.title as name, t.handlestatus, t.publishdate as date "
				+ " from syncv_mp t "
				+ " where t.businesstypename='媒体反映问题' "
//				+ " and DATE_FORMAT(t.publishdate, '%Y')='2019' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " order by t.publishdate desc "
				+ limitSql;
		return sql;
	}
	
	//媒体-反馈数总数
	public static String mediaFankuiCountSql(String year) {
		String yearParam = "		AND DATE_FORMAT( t.publishdate, '%Y' ) =  '"+year+"' ";
		String sql = " select count(t.handlestatus) as num "
				+ " from syncv_mp t, syncv_mp_prc p "
				+ " where t.prjid=p.prjid "
//				+ " and DATE_FORMAT(t.publishdate, '%Y')='2019' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " and t.handlestatus='已办结' "
				+ " and p.fbregC=1 "
				+ " group by t.businesstypename,t.handlestatus "
				+ limitSql;
		return sql;
	}
	
	//媒体-反馈数列表
	public static String mediaFankuiListSql(String year) {
		String yearParam = "		AND DATE_FORMAT( t.publishdate, '%Y' ) =  '"+year+"' ";
		String sql = " SELECT "
				+ " 	t.prjid as id, t.title as name, t.publishdate as date "
				+ " from syncv_mp t, syncv_mp_prc p "
				+ " where t.prjid=p.prjid "
//				+ " and DATE_FORMAT(t.publishdate, '%Y')='2019' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " and t.handlestatus='已办结' "
				+ " and p.fbregC=1 "
				+ " order by t.publishdate desc "
				+ limitSql;
		return sql;
	}
	
	//媒体-核查已办结数总数
	public static String mediaHechaBanJieCountSql(String year) {
		String yearParam = "		AND DATE_FORMAT( t.publishdate, '%Y' ) =  '"+year+"' ";
		String sql = " select count(t.handlestatus) as num "
				+ " from syncv_mp t, syncv_mp_prc p "
				+ " where t.prjid=p.prjid "
//				+ " and DATE_FORMAT(t.publishdate, '%Y')='2019' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " and t.handlestatus='已办结' "
				+ " and p.thridcheckfinished=1 "
				+ " group by t.businesstypename,t.handlestatus "
				+ limitSql;
		return sql;
	}
	
	//媒体-核查已办结列表
	public static String mediaHechaBanJieListSql(String year) {
		String yearParam = "		AND DATE_FORMAT( t.publishdate, '%Y' ) =  '"+year+"' ";
		String sql = " SELECT "
				+ " 	t.prjid as id, t.title as name, t.publishdate as date "
				+ " from syncv_mp t, syncv_mp_prc p "
				+ " where t.prjid=p.prjid "
//				+ " and DATE_FORMAT(t.publishdate, '%Y')='2019' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " and t.handlestatus='已办结' "
				+ " and p.thridcheckfinished=1 "
				+ " order by t.publishdate desc "
				+ limitSql;
		return sql;
	}
	
	//媒体-详情
	public static String mediaModelSql(String id) {
		String sql = " SELECT "
				+ " 	t.title, "
				+ " 	t.mediapartyname, "
				+ " 	DATE_FORMAT( t.publishdate, '%Y-%m-%d') AS publishdate, "
				+ " 	p.partyname, "
				+ " 	t.handlestatus "
				+ " FROM "
				+ " 	syncv_mp t, "
				+ " 	syncv_mp_prc p "
				+ " WHERE "
				+ " 	t.prjid = p.prjid "
				+ " 	AND t.prjid = '"+id+"' "
				+ " limit 1";
		return sql;
	}
	
	//批示件数总数
	public static String psjCountSql(String year) {
		String yearParam = "	AND	 DATE_FORMAT( estabprojdate, '%Y' ) =  '"+year+"' ";
		String sql = "select u.name,u.num from ( SELECT "
				+ " 	inspectcategoryname AS name, "
				+ " 	COUNT(inspectcategoryname) as num, "
				+ "     CASE WHEN inspectcategoryname='市属刊物' THEN 1 "
				+ "      WHEN inspectcategoryname='批示件' THEN 2 "
				+ "      WHEN inspectcategoryname='中央刊物' THEN 3 "
				+ "      WHEN inspectcategoryname='公文' THEN 4 "
				+ "      WHEN inspectcategoryname='其他' THEN 5 "
				+ "     ELSE 6 END AS sort "
				+ " FROM "
				+ " 	syncv_sp "
				+ " WHERE businesstypename in ('领导批示件','领导指示事项')  "
//				+ "   AND	DATE_FORMAT( estabprojdate, '%Y' ) = '"+year+"' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " 	AND inspectcategoryname IS NOT NULL "
				+ " GROUP BY inspectcategoryname ) u "
				+ " order by u.sort "
				+ limitSql;
		return sql;
	}
	
	//批示件数列表
	public static String psjListSql(String year, String type) {
		String yearParam = "	AND	 DATE_FORMAT( estabprojdate, '%Y' ) =  '"+year+"' ";
		String sql = "SELECT "
				+ " 	prjid, title, handlestatus, estabprojdate as date "
				+ " FROM "
				+ " 	syncv_sp "
				+ " WHERE  businesstypename in ('领导批示件','领导指示事项')  "
//				+ " 	AND DATE_FORMAT( estabprojdate, '%Y' ) = '"+year+"' "
				+ (StringUtils.isEmpty(year)?"":yearParam)
				+ " 	AND inspectcategoryname IS NOT NULL "
				+ " 	AND inspectcategoryname = '"+type+"' "
				+ " order by estabprojdate desc "
				+ limitSql;
		return sql;
	}
	
	//批示件详情
	public static String psjModelSql(String id) {
		String sql = " SELECT "
				+ " 	t.title, "
				+ " 	DATE_FORMAT( t.estabprojdate, '%Y-%m-%d') AS estabprojdate, "
				+ " 	DATE_FORMAT( t.limitdate, '%Y-%m-%d') AS limitdate, "
				+ " 	p.speciapartyname, "
				+ " 	v.instrleadername, "
				+ " 	t.handlestatus "
				+ " FROM "
				+ " 	syncv_sp t "
				+ "  left join syncv_sp_prc p on t.prjid = p.prjid "
				+ "  left join syncv_instr v on t.prjid = v.prjid "
				+ " WHERE  t.prjid = '"+id+"' "
				+ " limit 1";
		return sql;
	}	

}
