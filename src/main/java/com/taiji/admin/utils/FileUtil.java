/**
 * 
 */
package com.taiji.admin.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.util.StringUtils;

/**
 * 
 * sw-view com.taiji.admin.utils FileUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:03:41
 *
 * desc:
 */
public class FileUtil {
	
	/**
	 * 上传文件
	 */
	public static void uploadFile(InputStream fis, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        byte[] buffer = new byte[1024];
		BufferedInputStream bis = new BufferedInputStream(fis);
		FileOutputStream os = new FileOutputStream(filePath + File.separator + fileName, true);
		int i = bis.read(buffer);
		while (i != -1) {
			os.write(buffer, 0, i);
			i = bis.read(buffer);
		}
		os.flush();
		os.close();
		bis.close();
		fis.close();
    }
	
	/**
	 * 上传文件
	 */
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
		File targetFile = new File(filePath);  
		if(!targetFile.exists()){    
			targetFile.mkdirs();    
		}       
		byte[] buffer = new byte[1024];
		InputStream fis = new ByteArrayInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		FileOutputStream os = new FileOutputStream(filePath + File.separator + fileName);
		int i = bis.read(buffer);
		while (i != -1) {
			os.write(buffer, 0, i);
			i = bis.read(buffer);
		}
		os.flush();
		os.close();
		bis.close();
		fis.close();
	}
	
	/**
	 * 下载文件
	 * @param file
	 * @param os
	 * @throws Exception
	 */
	public static void downloadFile(File file, OutputStream os) throws Exception { 
		byte[] buffer = new byte[1024];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int i = bis.read(buffer);
		while (i != -1) {
			os.write(buffer, 0, i);
			i = bis.read(buffer);
		}
		os.flush();
		os.close();
		bis.close();
		fis.close();
	}
	
	/**
	 * 获取文件后缀
	 */
	public static String getSuffix(String fileName) { 
		if (fileName.contains("."))
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}
	
	/**
	 * 循环删除文件
	 */
	public static void deleteFile(File file) {
		deleteFile(file.getAbsolutePath());
	}
	public static void deleteFile(String path) {
		if (StringUtils.isEmpty(path))
			return;
		File file = new File(path);
		if (!file.exists())
			return;
		if (file.isFile()) {
//			System.out.println("删除文件：" + file.getName());
			file.delete();
		}
		else {
			for (File sub : file.listFiles()) {
				deleteFile(sub.getAbsolutePath());
			}
//			System.out.println("删除文件：" + file.getName());
			file.delete();
		}
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println(getSuffix("adaksd"));
//		System.out.println(getSuffix("adaks.d"));
//		System.out.println(getSuffix(".adaksd"));
//		System.out.println(getSuffix("adaksd."));
//		System.out.println("z,x,c,".split(",").length);
//		for (String s : "z,x,c,".split(",")) {
//			System.out.println("[" + s + "]");
//		}
		
//		String path = "D:\\work\\workspace3\\teach-src-manage\\teach-src-manage\\src\\main\\webapp\\updownload\\mmyb";
//		File file = new File(path + File.separator + "日报.txt.rar");
////		File file = new File(path + File.separator + "20180410144046_doc.zip");
//		if (!file.exists())
//			System.out.println("文件不存在");
////		System.out.println(isZip(file));
//		System.out.println(IOUtil.getMd5ByFile(file));
//		System.out.println(DigestUtils.md5Hex(new FileInputStream(file)));
		
		String path = "C:\\Users\\Administrator\\Desktop\\tmp\\压缩前文件";
		deleteFile(path);
	}

}
