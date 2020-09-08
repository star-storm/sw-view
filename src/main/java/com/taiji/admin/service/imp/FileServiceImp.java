/**
 * 
 */
package com.taiji.admin.service.imp;

import java.io.File;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.taiji.admin.common.LogMsgInfo;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.mapper.TFileMapper;
import com.taiji.admin.model.Plupload;
import com.taiji.admin.model.SUser;
import com.taiji.admin.model.TFile;
import com.taiji.admin.service.FileService;
import com.taiji.admin.service.base.imp.DownloadServiceImp;
import com.taiji.admin.utils.DateUtil;
import com.taiji.admin.utils.FileUtil;
import com.taiji.admin.utils.RandomUtil;

/**
 * 
 * sw-view com.taiji.admin.service.imp FileServiceImp.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:59:52
 *
 * desc:
 */
@Service
public class FileServiceImp implements FileService {
    
    @Value("${web.upload-path}")
    private String filePath;
	
	@Autowired
	private TFileMapper fileMapper;
	
	@Autowired
	private DownloadServiceImp downloadServiceImp;

	/**
	 * 上传文件
	 */
	@Override
	public int upFile(MultipartFile file, HttpServletRequest request, SUser host) throws Exception {
		String fileName = file.getOriginalFilename();
//		System.out.println("fileName-->" + fileName);
//		String contentType = file.getContentType();
//      System.out.println("getContentType-->" + contentType);
		//文件名：日期+4位随机串
		String ranStr = DateUtil.dateToStr2("yyyyMMddkkmmss") + "_" + RandomUtil.randomString(Constant.F_RAN_LEN);
		String rename = ranStr + "." + FileUtil.getSuffix(fileName);
		FileUtil.uploadFile(file.getBytes(), filePath, rename);
		File upFile = new File(filePath + File.separator + rename);
		if (!upFile.exists())//上传文件不存在
			return -1;
		
//		TFileExample example = new TFileExample();
//		TFileExample.Criteria criteria = example.createCriteria();
//		criteria.andMd5InitEqualTo(IOUtil.getMd5ByFile(upFile));
//		criteria.andDelFlgEqualTo("0");
//		long count = fileMapper.countByExample(example);
//		if (count > 0) {//文件已存在
//			upFile.delete();
//			return -2;
//		}
		
		TFile tFile = new TFile();
		tFile.setOriName(fileName);
		tFile.setName(rename);
		tFile.setPath(filePath + "/" + rename);
		tFile.setDelFlg("1");//保存模块信息时再使文件生效
		tFile.setCreateBy(host.getId());
		tFile.setCreateDate(new Date());
		fileMapper.insertSelective(tFile);
		return tFile.getId();
	}
	
	/**
	 * 分段上传
	 */
	@Override
	public int uploadSeperate(Plupload plupload, String type, HttpServletRequest request, SUser host) throws Exception {
		int chunks = plupload.getChunks();// 用户上传文件被分隔的总块数
		int nowChunk = plupload.getChunk();// 当前块，从0开始

		// 这里Request请求类型的强制转换可能出错，配置文件中向SpringIOC容器引入multipartResolver对象即可。
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) plupload.getRequest();
		// 调试发现map中只有一个键值对
		MultiValueMap<String, MultipartFile> map = multipartHttpServletRequest.getMultiFileMap();

		if (map != null) {
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				List<MultipartFile> multipartFileList = map.get(key);
				for (MultipartFile multipartFile : multipartFileList) {// 循环只进行一次
					plupload.setMultipartFile(multipartFile);// 手动向Plupload对象传入MultipartFile属性值
					String fileName = plupload.getName();
					if (chunks > 1) {// 用户上传资料总块数大于1，要进行合并
						File tempFile = new File(filePath + "/" + multipartFile.getName());
						// 第一块直接从头写入，不用从末端写入
						// savePluploadFile(multipartFile.getInputStream(),tempFile,nowChunk==0?false:true);
						FileUtil.uploadFile(multipartFile.getInputStream(), filePath, multipartFile.getName());
						if (chunks - nowChunk == 1) {// 全部块已经上传完毕，此时targetFile因为有被流写入而存在，要改文件名字
							//文件名：日期+4位随机串
							String ranStr = DateUtil.dateToStr2("yyyyMMddkkmmss") + "_" + RandomUtil.randomString(Constant.F_RAN_LEN);
							String rename = ranStr + "_" + FileUtil.getSuffix(fileName);
							File targetFile = new File(filePath + File.separator + rename);// 新建目标文件，只有被流写入时才会真正存在
							tempFile.renameTo(targetFile);
							
							TFile tFile = new TFile();
							tFile.setOriName(fileName);
							tFile.setName(rename);
							tFile.setPath(filePath + "/" + rename);
							tFile.setDelFlg("1");//保存模块信息时再使文件生效
							tFile.setCreateBy(host.getId());
							tFile.setCreateDate(new Date());
							fileMapper.insertSelective(tFile);
							return tFile.getId();
						}
					} else {
						//文件名：日期+4位随机串
						String ranStr = DateUtil.dateToStr2("yyyyMMddkkmmss") + "_" + RandomUtil.randomString(Constant.F_RAN_LEN);
						String rename = ranStr + "_" + FileUtil.getSuffix(fileName);
						@SuppressWarnings("unused")
						File targetFile = new File(filePath + File.separator + rename);// 新建目标文件，只有被流写入时才会真正存在
						// 只有一块，就直接拷贝文件内容
						FileUtil.uploadFile(multipartFile.getInputStream(), filePath, rename);

						TFile tFile = new TFile();
						tFile.setOriName(fileName);
						tFile.setName(rename);
						tFile.setPath(filePath + "/" + rename);
						tFile.setDelFlg("1");//保存模块信息时再使文件生效
						tFile.setCreateBy(host.getId());
						tFile.setCreateDate(new Date());
						fileMapper.insertSelective(tFile);
						return tFile.getId();
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * 检测文件存在
	 */
	@Override
	public boolean checkFile(String fid) {
		if (StringUtils.isEmpty(fid))
			return false;
		TFile po = fileMapper.selectByPrimaryKey(Integer.valueOf(fid));
		String filePath = po.getPath();
		File file = new File(filePath);
		if (file.exists())
			return true;
		else 
			return false;
	}
	
	/**
	 * 获取文件
	 * @throws Exception 
	 * @throws  
	 */
	@Override
	public boolean getFileTest(HttpServletRequest request, HttpServletResponse response, String fid) throws Exception {
		if (StringUtils.isEmpty(fid))
			return false;
		TFile po = fileMapper.selectByPrimaryKey(Integer.valueOf(fid));
		String filePath = po.getPath();
		File file = new File(filePath);
		if (file.exists()) {
			String fileName = file.getName();
			response.setContentType("application/octet-stream");
			String header = request.getHeader("User-Agent").toUpperCase();
			if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
				fileName = URLEncoder.encode(fileName, "utf-8");
				fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
			} else {
				fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
			}
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			FileUtil.downloadFile(file, response.getOutputStream());
			response.setHeader("Content-Length", Long.toString(file.length()));
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * 下载文件
	 */
	@Override
	public int downFile(HttpServletRequest request, HttpServletResponse response, String type, String fid) throws Exception {
		TFile po = fileMapper.selectByPrimaryKey(Integer.valueOf(fid));
		String filePath = po.getPath();
		File file = new File(filePath);
		if (file.exists()) {
			response.setContentType("application/octet-stream");
			try {
				String fileName = po.getOriName();
				String header = request.getHeader("User-Agent").toUpperCase();
				if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
					fileName = URLEncoder.encode(fileName, "utf-8");
					fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
				} else {
					fileName = new String(fileName.getBytes(), "ISO-8859-1");
				}
				
				response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				FileUtil.downloadFile(file, response.getOutputStream());
//				response.reset();
				response.setHeader("Content-Length", Long.toString(file.length()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		else 
			return 1;
	}
	
	/**
	 * 链接下载文件
	 */
	@Override
	public int hrefdown(HttpServletRequest request, HttpServletResponse response, String fid) throws Exception {
		TFile po = fileMapper.selectByPrimaryKey(Integer.valueOf(fid));
		File file = new File(po.getPath());
		if (file.exists()) {
			//解压的原文件
			response.setContentType("application/octet-stream");
			
			String fileName = po.getOriName();
			String header = request.getHeader("User-Agent").toUpperCase();
			if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
				fileName = URLEncoder.encode(fileName, "utf-8");
				fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
			} else {
				fileName = new String(fileName.getBytes(), "ISO-8859-1");
			}
			
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			FileUtil.downloadFile(file, response.getOutputStream());
//			response.reset();
			response.setHeader("Content-Length", Long.toString(file.length()));
			return 0;
		}
		else 
			return 1;
	}
	
	/**
	 * 导出日志 
	 */
	@Override
	public int exportLog(HttpServletRequest request, HttpServletResponse response, String modelId, String content, String userName, String startTime, String endTime) throws Exception {
		try {
			File file = downloadServiceImp.exportLog(request, modelId, content, userName, startTime, endTime);
			if (!file.exists()) {
				return 1;
			}
			String fileName = file.getName();
			response.setContentType("application/octet-stream");
			String header = request.getHeader("User-Agent").toUpperCase();
			if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
				fileName = URLEncoder.encode(fileName, "utf-8");
				fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
			} else {
				fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
			}
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			FileUtil.downloadFile(file, response.getOutputStream());
			response.setHeader("Content-Length", Long.toString(file.length()));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		response.reset();
		return 0;
	}
	
	/**
	 * 文件基本信息
	 */
	@Override
	public TFile getFile(Integer id) {
		return fileMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 删除文件
	 */
	@Override
	public LogMsgInfo delFile(Integer id, SUser host) {
		LogMsgInfo info = new LogMsgInfo();
		info.setId(String.valueOf(id));
		info.setResult(0);
		if (id == null)
			info.setResult(1);
		else {
			TFile tmp = fileMapper.selectByPrimaryKey(id);
			if (tmp != null) {
				info.setName(tmp.getOriName());
				tmp.setDelFlg("1");//逻辑删除
				tmp.setUpdateBy(host.getId());
				tmp.setUpdateDate(new Date());
				fileMapper.updateByPrimaryKeySelective(tmp);
			}
			else
				info.setResult(2);
		}
		return info;
	}
	
	public static void main(String[] args) {
		String path = FileServiceImp.class.getResource("/").getPath();
		System.out.println("path="+path);
		path = FileServiceImp.class.getResource("").getPath();
		System.out.println("path="+path);
	}

}
