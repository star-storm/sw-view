/**
 * 
 */
package com.taiji.admin.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.util.StringUtils;

/**
 * 
 * sw-view com.taiji.admin.utils IOUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:03:49
 *
 * desc:
 */
public class IOUtil {
	
	//文件头字节
	public static byte[] getFileHeadByte(File file) {
		byte[] bt = new byte[30];
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			is.read(bt, 0, bt.length);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bt;
	}
	
	//字节转16进制
	public static String byteToHexString(byte[] bt) {
		StringBuilder sbd = new StringBuilder();
		if (bt == null || bt.length <= 0)
			return null;
		for (int i = 0; i < bt.length; i++) {
			String tmp = Integer.toHexString(bt[i] & 0xFF);
//			System.out.println("tmp = [" + tmp + "]");
			if (tmp.length() < 2)
				sbd.append(0);
			sbd.append(tmp);
//			System.out.println(sbd.toString());
		}
		return sbd.toString().toUpperCase();
	}
	
	//获取文件类型
	public static String getFileType(String path, String name) {
//		File file = new File(path);
//		if (!file.exists()) {
//			System.out.println("file not exist");
//			return null;
//		}
//		byte[]bt = getFileHeadByte(file);
//		if (bt == null)
//			return null;
//		String head = byteToHexString(bt).toUpperCase();
//		System.out.println("文件头：" + head);
//		if (head == null)
//			return null;
//		Set<String>set = Constant.FILE_TYPE_MAP.keySet();
//		Iterator<String>it = set.iterator();
////		int index = 0;
		String type = "";
//		while (it.hasNext()) {
//			String key = it.next();
//			String value = Constant.FILE_TYPE_MAP.get(key);
////			System.out.println("key = [" + key + "], value = [" + value + "]");
//			if (head.startsWith(value)) {
////				System.out.println("index = [" + index + "]");
//				type = key;
//			}
////			index++;
//		}
//		if (type == null || type.equals("")) {
			if (name.contains(".")) {
				type = name.substring(name.lastIndexOf(".") + 1);
			}
//		}
		return type;
	}
	
	/**
	 * 获取文件的md5
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getMd5ByFile(File file) throws Exception {
		byte[] buffer = new byte[8192];
	    int len = 0;
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    FileInputStream fis = new FileInputStream(file);
	    while ((len = fis.read(buffer)) != -1) {
	        md.update(buffer, 0, len);
	    }
	    fis.close();
	    byte[] b = md.digest();
	    BigInteger bi = new BigInteger(1, b);
		String s = bi.toString(16);
		String as = addChar(s, 32);
		return as;
//		return DigestUtils.md5Hex(new FileInputStream(file));
	}
	
	//补0
	public static String addChar(String s, int k) {
		if (StringUtils.isEmpty(s))
			return s;
		StringBuffer sbf = new StringBuffer();
		if (s.length() <  k) {
			for (int i = 0; i < k - s.length(); i++) {
				sbf.append("0");
			}
		}
		sbf.append(s);
		return sbf.toString();
	}
	
	/**
	 * 文件转字节组
	 */
	public static byte[] fileToBytes(String fileName) throws IOException {  
		File file = new File(fileName);  
		FileInputStream fis = new FileInputStream(file);  
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
		byte[] b = new byte[1000];  
		int n;  
		while ((n = fis.read(b)) != -1) {  
			bos.write(b, 0, n);  
		}  
		fis.close();  
		bos.close();  
		byte[] buffer = bos.toByteArray();  
        return buffer;   
    }
	/**
	 * 字节组转文件
	 */
	public static void bytesToFile(byte[] bfile, String fileName) throws IOException {  
		File file = new File(fileName);  
		FileOutputStream fos = new FileOutputStream(file);    
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(bfile);  
		bos.close();  
		fos.close();
	}  
	
	public static void main(String[] args) throws Exception {
//		String basePath = "C:\\Users\\Administrator\\Desktop\\tmp\\fileZip";
//		String fiPath = basePath + File.separator + "t2\\after\\ttt.7z";
//		String fiPath = basePath + File.separator + "t2\\before\\txz.tar";
//		String fiPath = "D:\\taiji\\文档\\周报\\日报.txt";
//		System.out.println(getFileType(fiPath));
		
//		String fileName = "C:\\Users\\Administrator\\Desktop\\tmp\\TestAction.java";
//		byte[] bytes = fileToBytes(fileName);
//		System.out.println(bytes.length);
//		bytesToFile(bytes, "C:\\Users\\Administrator\\Desktop\\tmp\\TestAction0.java");
		
//		String fileName = "D:\\home\\admin\\taiji\\file-base\\teach-src\\upload\\20180810144404_5376_tar";
//		String md5 = getMd5ByFile(new File(fileName));
//		System.out.println(md5);
		
		String s = addChar("87ddf66e24c948b7500fd40c2159b68b", 32);
		System.out.println(s);
	}
}
