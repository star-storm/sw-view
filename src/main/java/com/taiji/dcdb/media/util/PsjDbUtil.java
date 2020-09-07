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
import java.util.List;

import com.taiji.dcdb.media.Constant;

/**
 * @author Administrator
 *
 */
public class PsjDbUtil {
	
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
     * 批示件总数查询
     * @param year (格式：2017)
     * @return 
     */
    public static List<String> psjCount(String year) {
    	List<String> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	try {
    		pStemt = conn.prepareStatement(Constant.psjCountSql(year));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			String name = rs.getString("name");
    			long num = rs.getLong("num");
    			System.out.println(name + ":" + num);
    			list.add(name+"|"+num);
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
     * 批示件列表查询
     * @param year (格式：2017)
     * @param type
     * @return 
     */
    public static List<String> psjList(String year, String type) {
    	List<String> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	try {
    		pStemt = conn.prepareStatement(Constant.psjListSql(year, type));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			String prjid = rs.getString("prjid");
    			String title = rs.getString("title");
    			String handlestatus = rs.getString("handlestatus");
    			System.out.println(prjid+ ","+ title + "," + handlestatus);
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
     * 批示件详情查询
     * @return 
     */
	public static List<List<String>> psjModel(String id) {
    	List<List<String>> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	List<String> items = new ArrayList<>();
    	try {
    		pStemt = conn.prepareStatement(Constant.psjModelSql(id));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			String title = rs.getString("title");
    			items.add(title);
    			String estabprojdate = rs.getString("estabprojdate");
    			items.add(estabprojdate);
    			String limitdate = rs.getString("limitdate");
    			items.add(limitdate);
    			String speciapartyname = rs.getString("speciapartyname");
    			items.add(speciapartyname);
    			String instrleadername = rs.getString("instrleadername");
    			items.add(instrleadername);
    			String handlestatus = rs.getString("handlestatus");
    			items.add(handlestatus);
//    			System.out.println("批示件-详情："+title+","+estabprojdate+","+limitdate+","+speciapartyname+","+instrleadername+","+handlestatus);
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
//		PsjDbUtil.dcdbList("2019", 0);
//		PsjDbUtil.dcdbList("2019", 1);
//		PsjDbUtil.dcdbList("2019", 2);
		
		PsjDbUtil.psjCount("2019");
		PsjDbUtil.psjList("2019", "中央刊物");
	}

}
