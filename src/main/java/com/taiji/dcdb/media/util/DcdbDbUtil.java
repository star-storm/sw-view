/**
 * 
 */
package com.taiji.dcdb.media.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taiji.dcdb.media.Constant;

/**
 * @author Administrator
 *
 */
public class DcdbDbUtil {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/postgres?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    		System.out.println("连接数据库成功");
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
        		System.out.println("关闭数据库成功");
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        }
    }
    
    /**
     * 督办件总数查询
     * @param status (0:全部;1:已办结;2:办理中)
     * @param year (格式：2017)
     * @return 
     */
    public static long dcdbCount(String year, int status) {
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	long count = 0;
    	try {
    		pStemt = conn.prepareStatement(Constant.dcjCountSql(year,""));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			count = rs.getLong("num");
    			String name = "";
    			switch (status) {
				case Constant.DCDB_STATUS_ALL_CODE:
					name = Constant.DCDB_STATUS_ALL_DATA;
					break;
				case Constant.DCDB_STATUS_END_CODE:
					name = Constant.DCDB_STATUS_END_DATA;
					break;
				case Constant.DCDB_STATUS_PROC_CODE:
					name = Constant.DCDB_STATUS_PROC_DATA;
					break;
				default:
					break;
				}
    			System.out.println(name + ":" + count);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		if (pStemt != null) {
    			try {
    				pStemt.close();
    				closeConnection(conn);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return count;
    }

    /**
     * 督办件总数列表查询
     * @param year (格式：2017)
     * @return 
     */
    public static List<Map<String, Object>> dcdbList(String year, int status, String area, String areaName) {
    	List<Map<String, Object>> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	try {
    		pStemt = conn.prepareStatement(Constant.dcjsListSql(year, status, area, areaName));
    		ResultSet rs = pStemt.executeQuery();
    		Map<String, Object> map = null;
    		while (rs.next()) {
    			map = new HashMap<>();
    			String name = rs.getString("name");
    			map.put("name", name);
    			long num = rs.getLong("num");
    			map.put("num", num);
    			System.out.println(name + ":" + num);
    			list.add(map);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		if (pStemt != null) {
    			try {
    				pStemt.close();
    				closeConnection(conn);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return list;
    }

    /**
     * 督办件根据状态列表查询
     * @param year (格式：2017)
     * @return 
     */
    public static List<String> dcdbStatusList(String year, String status, String type, String area) {
    	List<String> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	try {
    		pStemt = conn.prepareStatement(Constant.dcjsListSql(year, status, type, area));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			String prjid = rs.getString("prjid");
    			String title = rs.getString("title");
    			String handlestatus = rs.getString("handlestatus");
    			System.out.println(title + ":" + handlestatus);
    			list.add(prjid+"|"+title+"|"+handlestatus);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		if (pStemt != null) {
    			try {
    				pStemt.close();
    				closeConnection(conn);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return list;
    }

    /**
     * 督办件详情查询
     * @return 
     */
    public static List<List<String>> dcdbModel(String id) {
    	List<List<String>> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	try {
    		pStemt = conn.prepareStatement(Constant.dcjsModelSql(id));
    		ResultSet rs = pStemt.executeQuery();
    		List<String> items = null;
    		while (rs.next()) {
    			items = new ArrayList<>();
    			String estabprojdate = rs.getString("estabprojdate");
    			items.add(estabprojdate);
    			String title = rs.getString("title");
    			items.add(title);
    			String instrleadername = rs.getString("instrleadername");
    			items.add(instrleadername);
    			String speciapartyname = rs.getString("speciapartyname");
    			items.add(speciapartyname);
    			String handlestatus = rs.getString("handlestatus");
    			items.add(handlestatus);
//    			System.out.println("督办件：" + estabprojdate + "," + title + "," + speciapartyname + "," + handlestatus);
    			list.add(items);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		if (pStemt != null) {
    			try {
    				pStemt.close();
    				closeConnection(conn);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return list;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DcdbDbUtil.dcdbList("2019", 0, "", "");
		DcdbDbUtil.dcdbList("2019", 1, "", "");
		DcdbDbUtil.dcdbList("2019", 2, "", "");
	}

}
