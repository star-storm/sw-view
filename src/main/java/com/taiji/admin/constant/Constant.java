/**
 * 
 */
package com.taiji.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * sw-view com.taiji.admin.constant Constant.java
 *
 * @author hsl
 *
 * 2019年11月24日 上午10:43:15
 *
 * desc:常量类
 */
public class Constant {

    /**
     * session中用户的key
     */
    public final static String SESSION_USER_KEY = "HOST";
    
    /**
     * 默认的用户密码：123456
     */
    public final static String DEFAULT_USER_PASSWORD = "123456";
	
	/**
	 * 组织根父节点id
	 */
	public final static String ORG_ROOT_PARENT="0";
	public final static String ORG_ROOT="1";
	
	/**
	 * 用户角色ID
	 */
	public final static int ADMIN_ROLE_ID=1;
	public final static int USER_ROLE_ID=2;
	
	/**
	 * 下载目录
	 */
	public final static String DOWNLOAD_DIR="upload";
	
	/**
	 * 日志文件名称
	 */
	public final static String LOG_FILE="sw-view.xlsx";
	
	/**
	 * 返回值
	 */
	public final static String RESULT_SUCCESS="处理成功";
	public final static String RESULT_FAIL="处理失败";
	public final static String RESULT_SUCCESS_CODE="1";
	public final static String RESULT_FAIL_CODE="0";
    
	/**
	 * 随机数长度
	 */
	public final static int F_RAN_LEN = 4;
    
    /**
     * 文件类型
     */
	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
	static {
		/**
		 * 压缩
		 */
		FILE_TYPE_MAP.put("rar", "52617221");                       //RAR
		FILE_TYPE_MAP.put("zip", "504B0304");                       //ZIP
		FILE_TYPE_MAP.put("gz", "1F8B08");                          //GZ
		FILE_TYPE_MAP.put("7z", "377ABCAF271C");                    //7Z
		/**
		 * 图片
		 */
		FILE_TYPE_MAP.put("jpg", "FFD8FF");                         //JPEG (jpg)
		FILE_TYPE_MAP.put("png", "89504E47");                       //PNG (png)
		FILE_TYPE_MAP.put("gif", "47494638");                       //GIF (gif)
		FILE_TYPE_MAP.put("tif", "49492A00");                       //TIFF (tif)
		FILE_TYPE_MAP.put("bmp", "424D");                           //Windows Bitmap (bmp)
		/**
		 * 文本
		 */
		FILE_TYPE_MAP.put("txt", "75736167");                       //TXT
		FILE_TYPE_MAP.put("pdf", "255044462D312E");                 //Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("xml", "3C3F786D6C");
		FILE_TYPE_MAP.put("html", "68746D6C3E");                    //HTML (html)
		FILE_TYPE_MAP.put("rtf", "7B5C727466");                     //Rich Text Format (rtf)
		/**
		 * MS二进制
		 */
		FILE_TYPE_MAP.put("xls", "D0CF11E0");                       //MS Word
		FILE_TYPE_MAP.put("doc", "D0CF11E0");                       //MS Excel 注意：word 和 excel的文件头一样
//		FILE_TYPE_MAP.put("docx", "504B0304");                      //DOCX
		//其他MS
		FILE_TYPE_MAP.put("psd", "38425053");                       //Photoshop (psd)
		FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");   //Email [thorough only] (eml)
		FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");               //Outlook Express (dbx)
		FILE_TYPE_MAP.put("pst", "2142444E");                       //Outlook (pst)
		FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");           //MS Access (mdb)
		FILE_TYPE_MAP.put("wpd", "FF575043");                       //WordPerfect (wpd)
		/**
		 * 其他
		 */
		FILE_TYPE_MAP.put("dwg", "41433130");                       //CAD (dwg)
		FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("qdf", "AC9EBD8F");                       //Quicken (qdf)
		FILE_TYPE_MAP.put("pwl", "E3828596");                       //Windows Password (pwl)
		FILE_TYPE_MAP.put("wav", "57415645");                       //Wave (wav)
		FILE_TYPE_MAP.put("avi", "41564920");
		FILE_TYPE_MAP.put("ram", "2E7261FD");                       //Real Audio (ram)
		FILE_TYPE_MAP.put("rm", "2E524D46");                        //Real Media (rm)
		FILE_TYPE_MAP.put("mpg", "000001BA");                       //MPG
		FILE_TYPE_MAP.put("mpg", "000001B3");                       //MPG
		FILE_TYPE_MAP.put("mov", "6D6F6F76");                       //Quicktime (mov)
		FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");               //Windows Media (asf)
		FILE_TYPE_MAP.put("mid", "4D546864");                       //MIDI (mid)
		FILE_TYPE_MAP.put("exe", "4D5A9000");                       //EXE
		FILE_TYPE_MAP.put("dll", "4D5A9000");                       //DLL
		
	}

}
