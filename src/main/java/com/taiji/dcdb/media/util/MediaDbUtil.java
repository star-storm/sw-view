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
public class MediaDbUtil {
	
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
     * 媒体-反馈数查询
     * @param year (格式：2017)
     * @return 
     */
    public static long mediaFankuiCount(String year) {
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	long count = 0;
    	try {
    		pStemt = conn.prepareStatement(Constant.mediaFankuiCountSql(year));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			count = rs.getLong("num");
    			System.out.println(count);
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
     * 媒体-反馈查询
     * @param year (格式：2017)
     * @return 
     */
	public static List<String> mediaFankuiList(String year) {
    	List<String> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	try {
    		pStemt = conn.prepareStatement(Constant.mediaFankuiListSql(year));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			String id = rs.getString("id");
    			String name = rs.getString("name");
//    			System.out.println("媒体-反馈："+id+","+name);
    			list.add(id+"|"+name);
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
     * 媒体-核查已办结数查询
     * @param year (格式：2017)
     * @return 
     */
    public static long mediaHechaBanJieCount(String year) {
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	long count = 0;
    	try {
    		pStemt = conn.prepareStatement(Constant.mediaHechaBanJieCountSql(year));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			count = rs.getLong("num");
    			System.out.println(count);
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
     * 媒体-核查已办结查询
     * @param year (格式：2017)
     * @return 
     */
	public static List<String> mediaHechaBanJieList(String year) {
    	List<String> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	try {
    		pStemt = conn.prepareStatement(Constant.mediaHechaBanJieListSql(year));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			String id = rs.getString("id");
    			String name = rs.getString("name");
//    			System.out.println("媒体-核查办结："+id+","+name);
    			list.add(id+"|"+name);
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
     * 媒体-详情查询
     * @return 
     */
	public static List<List<String>> mediaModel(String id) {
    	List<List<String>> list = new ArrayList<>();
    	//与数据库的连接
    	Connection conn = getConnection();
    	PreparedStatement pStemt = null;
    	List<String> items = new ArrayList<>();
    	try {
    		pStemt = conn.prepareStatement(Constant.mediaModelSql(id));
    		ResultSet rs = pStemt.executeQuery();
    		while (rs.next()) {
    			String title = rs.getString("title");
    			items.add(title);
    			String mediapartyname = rs.getString("mediapartyname");
    			items.add(mediapartyname);
    			String publishdate = rs.getString("publishdate");
    			items.add(publishdate);
    			String partyname = rs.getString("partyname");
    			items.add(partyname);
    			String handlestatus = rs.getString("handlestatus");
    			items.add(handlestatus);
//    			System.out.println("媒体-详情："+title+","+mediapartyname+","+publishdate+","+partyname+","+handlestatus);
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
		MediaDbUtil.mediaFankuiCount("2019");
		MediaDbUtil.mediaFankuiList("2019");
		MediaDbUtil.mediaHechaBanJieCount("2019");
		MediaDbUtil.mediaHechaBanJieList("2019");
	}

}
