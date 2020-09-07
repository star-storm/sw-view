/**
 * 
 */
package com.taiji.admin.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

/**
 * 
 * sw-view com.taiji.admin.utils SystemUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:05:43
 *
 * desc:
 */
public class SystemUtil {
	
	/**
	 * 备份数据库
	 */
	public static String dumpDb(String ip, String uname, String pwd, String dbins, String path) throws Exception {
		File saveFile = new File(path);  
        if (!saveFile.exists()) {// 如果文件不存在  
            saveFile.createNewFile();// 创建文件
        }  
//      Process process = Runtime.getRuntime().exec(" mysqldump -hlocalhost -uroot -p1234 --set-charset=UTF8 teach_src");  
        String exeStr = " mysqldump -h" + ip + " -u" + uname + " -p" + pwd + " --set-charset=UTF8 " + dbins;
        System.out.println("exeStr = " + exeStr);
        Process process = Runtime.getRuntime().exec(exeStr);
        
        byte[] buffer = new byte[1024];
        InputStream is = process.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
        OutputStream os = new FileOutputStream(saveFile);
        int i = bis.read(buffer);
		while (i != -1) {
			os.write(buffer, 0, i);
			i = bis.read(buffer);
		}
		os.flush();
		os.close();
		bis.close();
		is.close();
		
        if(process.waitFor() == 0){//0 表示线程正常终止。  
            return "0";  
        }  
        return "0";
	}
	
	/**
	 * scp传输文件
	 */
	public static String scpPutFile(String ip, String uname, String pwd, String pathIn, String pathOut) throws IOException {
		File saveFile = new File(pathIn);  
        if (!saveFile.exists()) {// 如果文件不存在  
            return "1";
        }  
		Connection conn = new Connection(ip);
		conn.connect();
		boolean isAuthenticated = conn.authenticateWithPassword(uname, pwd);
		if (isAuthenticated == false)
			return "2";
		SCPClient client = new SCPClient(conn);
		client.put(pathIn, pathOut);
		conn.close();
		return "0";
	}
	
	/**
	 * scp接收文件
	 */
	public static String scpGetFile(String ip, String uname, String pwd, String pathIn, String pathOut) throws IOException {
		Connection conn = new Connection(ip);
		conn.connect();
		boolean isAuthenticated = conn.authenticateWithPassword(uname, pwd);
		if (isAuthenticated == false)
			return "2";
		SCPClient client = new SCPClient(conn);
		client.get(pathIn, pathOut);
		conn.close();
		return "0";
	}
	
	/**
	 * scp传输目录
	 */
	public static String scpDir(String ip, String uname, String pwd, String upDir, String pathIn, String pathOut) throws Exception {
		String result = zipDir(upDir, pathIn);
		if(result.equals("0")){//正常
			//传输文件
			return scpPutFile(ip, uname, pwd, pathIn, pathOut);
        }
		else {
			return "3";
		}
	}
	
	/**
	 * scp压缩文件夹
	 */
	public static String zipDir(String pathIn, String pathOut) throws Exception {
		//压缩文件(tar -zcvf /home/admin/taiji/file-base/teach-src/upload.tar.gz /home/admin/taiji/file-base/teach-src/upload)
		Process process = Runtime.getRuntime().exec("tar -zcvf " + pathOut + " " + pathIn);
		System.out.println("zip-command: " + "tar -zcvf " + pathOut + " " + pathIn);
		if(process.waitFor() == 0){//0 表示线程正常终止。
			return "0";
		}
		else {
			return "1";
		}
	}
	
	/**
	 * scp解压缩文件夹
	 */
	public static String unzipDir(String pathIn, String pathOut) throws Exception {
		//压缩文件(tar -xzvf /home/admin/taiji/file-base/teach-src/upload.tar.gz -C /home/admin/taiji/file-base/teach-src)
		Process process = Runtime.getRuntime().exec("tar -xzvf " + pathIn + " -C " + pathOut);
		System.out.println("unzip-command: " + "tar -xzvf " + pathIn + " -C " + pathOut);
		if(process.waitFor() == 0){//0 表示线程正常终止。
			return "0";
		}
		else {
			return "3";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		dumpDb("192.168.2.55", "root", "1234", "teach_src", "teach_src.sql");
//		scpFile("192.168.2.55", "root", "123456", "C:/Users/Administrator/Desktop/111879150730243352.png", "/home/admin/taiji/file-base/tmp");
//		scpGetFile("192.168.2.65", "root", "123456", "/home/admin/taiji/file-base/tmp/teach_src.sql", "/home/admin/taiji/file-base/db/recover");
	}

}
