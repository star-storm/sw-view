/**
 * 
 */
package com.taiji.admin.service.base.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taiji.admin.constant.Constant;
import com.taiji.admin.model.SLog;
import com.taiji.admin.model.SUser;
import com.taiji.admin.service.SLogService;
import com.taiji.admin.service.base.DownloadService;
import com.taiji.admin.utils.DateUtil;
import com.taiji.admin.utils.RandomUtil;

/**
 * sw-view com.taiji.admin.service.base.imp DownloadServiceImp.java
 *
 * @author hsl
 *
 * 2020年1月9日 下午12:27:12
 *
 * desc:
 */
@Service
public class DownloadServiceImp implements DownloadService {
	
    @Value("${web.upload-path}")
    public String uploadPath;
	
	@Autowired
	private SLogService logService;

	/**
	 * 导出日志 
	 */
	@Override
	public File exportLog(HttpServletRequest request, String modelId, String content, String userName, String startTime, String endTime) throws InvalidFormatException, IOException {
//		String basePath = DownloadServiceImp.class.getResource("/").getPath();
//		String fileDirPath = basePath + File.separator + "templates" + File.separator + Constant.TXL_DOWNLOAD_DIR;
		String fileDirPath = uploadPath + File.separator + DateUtil.dateToStr2("yyyyMMddHHmmss") + "_" + RandomUtil.randomString(4);
		File fileDir = new File(fileDirPath);
		if (!fileDir.exists())
			fileDir.mkdirs();
		String fileName = Constant.LOG_FILE;
		String filePath = fileDir + File.separator + fileName;
		File file = new File(filePath);
		if (file.exists())
			file.delete();
		file.createNewFile();
		if (file.exists()) {
			//创建工作薄和sheet
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("日志");
			sheet.setDefaultColumnWidth(20);// 默认列宽
			//查询数据，获取行数
			SUser host = (SUser) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
			Integer roleId = host.getRoles().get(0).getId();
			List<SLog> list = logService.logDatas(modelId, content, userName, startTime, endTime, roleId);
			//创建行和单元格
			for (int i=0; i<list.size()+1; i++) {
				Row row = sheet.createRow(i);
				for (int j=0; j<12; j++) {
					Cell cell = row.createCell(j);
					cell.setCellType(CellType.STRING);
					CellStyle style = cell.getCellStyle();
					Font font = workbook.createFont();
					font.setFontName("微软雅黑");
//					font.setFontName("Arial");
					if (i == 0) {//标题样式：加粗
					   	font.setBold(true);//字体加粗
					}
					style.setAlignment(HorizontalAlignment.CENTER);
					style.setVerticalAlignment(VerticalAlignment.CENTER);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderTop(BorderStyle.THIN);
					cell.setCellStyle(style);
					style.setFont(font);
				}
			}
			Row headRow = sheet.getRow(0);
			headRow.getCell(0).setCellValue("序号");
			headRow.getCell(1).setCellValue("姓名");
			headRow.getCell(2).setCellValue("操作名称");
			headRow.getCell(3).setCellValue("操作内容");
			headRow.getCell(4).setCellValue("操作时间");
			int k = 1;
			for (SLog log : list) {
				String uName = log.getUserName();
				String options = log.getOptions();
				String optContent = log.getContent();
				String optDate = DateUtil.dateToStr1(log.getOptionDate());
				
				Row row = sheet.getRow(k++);
				for (Cell cell : row) {
					cell.setCellType(CellType.STRING);
					CellStyle style = cell.getCellStyle();
					cell.setCellStyle(style);
				}
				row.getCell(0).setCellValue(String.valueOf(k-1));
				row.getCell(1).setCellValue(parseValue(uName));
				row.getCell(2).setCellValue(parseValue(options));
				row.getCell(3).setCellValue(parseValue(optContent));
				row.getCell(4).setCellValue(parseValue(optDate));
			}
			
			// 内容写入Excel文件
			OutputStream fOut = new FileOutputStream(file);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
			workbook.close();
			return file;
		}
		return null;
	}
	
	/**
	 * 转换空字符串
	 * @param v
	 * @return
	 */
	private String parseValue(String v) {
		if (!StringUtils.isEmpty(v) && v.equalsIgnoreCase("null"))
			v = "";
		if (StringUtils.isEmpty(v))
			return "";
		else
			return v.trim();
	}
	@SuppressWarnings("unused")
	private String parseValue(String post, String position) {
		if (!StringUtils.isEmpty(post) && post.equalsIgnoreCase("null"))
			post = "";
		if (!StringUtils.isEmpty(position) && position.equalsIgnoreCase("null"))
			position = "";
		StringBuffer sbf = new StringBuffer();
		if (!StringUtils.isEmpty(post) && !StringUtils.isEmpty(position))
			return sbf.append(post).append("、").append(position).toString();
		else if (!StringUtils.isEmpty(post) && StringUtils.isEmpty(position))
			return post;
		else if (StringUtils.isEmpty(post) && !StringUtils.isEmpty(position))
			return position;
		return "";
	}

}
