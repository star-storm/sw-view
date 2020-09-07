/**
 * 
 */
package com.taiji.admin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.taiji.admin.config.AdminDataSourceConfig;

/**
 * 
 * sw-view com.taiji.admin.utils DbUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 上午11:59:24
 *
 * desc:
 */
public class DbUtil {
	
	/**
	 * 执行sql脚本
	 */
	public static String recoverDb(AdminDataSourceConfig dataSource, String sqlPath) throws Exception {
        SqlSessionFactory sqlSessionFactory = dataSource.adminSqlSessionFactory(dataSource.adminDataSource());
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection conn = sqlSession.getConnection();
		ScriptRunner runner = new ScriptRunner(conn);
		runner.setErrorLogWriter(null);
		runner.setLogWriter(null);
		Reader reader = Resources.getResourceAsReader(sqlPath);
		if (reader == null) {// 如果文件不存在  
			return "1";
		}  
		runner.runScript(reader);
		conn.commit();
		conn.close();
		return "0";
	}
	/**
	 * 执行sql脚本
	 */
	public static String recoverDb(AdminDataSourceConfig dataSource, File file) throws Exception {
		if (!file.exists() || file.isDirectory()) {// 如果文件不存在  
			return "3";
		}  
		SqlSessionFactory sqlSessionFactory = dataSource.adminSqlSessionFactory(dataSource.adminDataSource());
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection conn = sqlSession.getConnection();
		ScriptRunner runner = new ScriptRunner(conn);
		runner.setErrorLogWriter(null);
		runner.setLogWriter(null);
		Reader reader = new InputStreamReader(new FileInputStream(file));
		runner.runScript(reader);
		conn.commit();
		conn.close();
		return "0";
	}
	
	/**
	 * database测试
	 */
	public static void testDb() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/teach_src";
	    String username = "root";
	    String password = "1234";
	    Class.forName(driver); //classLoader,加载对应驱动
	    Connection conn = (Connection) DriverManager.getConnection(url, username, password);
	    ScriptRunner runner = new ScriptRunner(conn);
		runner.setErrorLogWriter(null);
		runner.setLogWriter(null);
		String path = "D:/home/admin/taiji/file-base/teach-src/db/recover/teach_src.sql";
		File file = new File(path);
		Reader reader = new InputStreamReader(new FileInputStream(file));
		runner.runScript(reader);
		conn.commit();
		conn.close();
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		testDb();
	}

}
