/**
 * 
 */
package com.taiji.admin.service.base.imp;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taiji.admin.service.base.DownloadService;

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

	/**
	 * 下载通讯录
	 * @param type 类型（1:全员; 2:机构; 3:个人）
	 * @param id（机构ID; 人员ID）
	 * @return excel文件
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	@Override
	public File createTxl(String type, Integer id, String nameTmp, String officePhoneTmp, String salePhoneTmp) throws InvalidFormatException, IOException {
////		String basePath = DownloadServiceImp.class.getResource("/").getPath();
////		String fileDirPath = basePath + File.separator + "templates" + File.separator + Constant.TXL_DOWNLOAD_DIR;
//		String fileDirPath = uploadPath + File.separator + DateUtil.dateToStr2("yyyyMMddHHmmss") + "_" + RandomUtil.randomString(4);
//		File fileDir = new File(fileDirPath);
//		if (!fileDir.exists())
//			fileDir.mkdirs();
//		String fileName = Constant.TXL_DOWNLOAD_NAME;
//		String filePath = fileDir + File.separator + fileName;
//		File file = new File(filePath);
//		if (file.exists())
//			file.delete();
//		file.createNewFile();
//		if (file.exists()) {
//			//创建工作薄和sheet
//			Workbook workbook = new XSSFWorkbook();
//			Sheet sheet = workbook.createSheet("通讯录");
//			sheet.setDefaultColumnWidth(20);// 默认列宽
//			//查询数据，获取行数
//			TAddrBookExample example = new TAddrBookExample();
//			TAddrBookExample.Criteria criteria = example.createCriteria();
//			switch (type) {
//			case "1":
//				break;
//			case "2":
//				criteria.andOrgIdEqualTo(id);
//				break;
//			case "3":
//				criteria.andIdEqualTo(id);
//				break;
//			default:
//				return null;
//			}
//			if (!StringUtils.isEmpty(nameTmp))
//				criteria.andNameLike("%" + nameTmp + "%");
//			if (!StringUtils.isEmpty(officePhoneTmp))
//				criteria.andOfficePhoneLike("%" + officePhoneTmp + "%");
//			if (!StringUtils.isEmpty(salePhoneTmp))
//				criteria.andSalePhoneLike("%" + salePhoneTmp + "%");
//			criteria.andDelFlgEqualTo("0");
//			example.setOrderByClause(" org_id, sort, name");
//			List<TAddrBook> list = addrBookMapper.selectByExample(example);
//			//创建行和单元格
//			for (int i=0; i<list.size()+1; i++) {
//				Row row = sheet.createRow(i);
//				for (int j=0; j<12; j++) {
//					Cell cell = row.createCell(j);
//					cell.setCellType(CellType.STRING);
//					CellStyle style = cell.getCellStyle();
//					Font font = workbook.createFont();
//					font.setFontName("微软雅黑");
////					font.setFontName("Arial");
//					if (i == 0) {//标题样式：加粗
//					   	font.setBold(true);//字体加粗
//					}
//					style.setAlignment(HorizontalAlignment.CENTER);
//					style.setVerticalAlignment(VerticalAlignment.CENTER);
//					style.setBorderBottom(BorderStyle.THIN);
//					style.setBorderLeft(BorderStyle.THIN);
//					style.setBorderRight(BorderStyle.THIN);
//					style.setBorderTop(BorderStyle.THIN);
//					cell.setCellStyle(style);
//					style.setFont(font);
//				}
//			}
//			Row headRow = sheet.getRow(0);
//			headRow.getCell(0).setCellValue("序号");
//			headRow.getCell(1).setCellValue("姓名");
//			headRow.getCell(2).setCellValue("所在部门");
//			headRow.getCell(3).setCellValue("职务职级");
//			headRow.getCell(4).setCellValue("房间号");
//			headRow.getCell(5).setCellValue("办公电话");
//			headRow.getCell(6).setCellValue("手机");
//			headRow.getCell(7).setCellValue("传真分机号");
//			headRow.getCell(8).setCellValue("备注");
//			int k = 1;
//			for (TAddrBook addrBook : list) {
//				String name = addrBook.getName();
//				String orgName = addrBook.getOrg().getName();
//				String post = addrBook.getPost();
//				String position = addrBook.getPosition();
//				String roomId = addrBook.getRoomId();
//				String officePhone = addrBook.getOfficePhone();
//				String salePhone = addrBook.getSalePhone();
//				String fax = addrBook.getFax();
//				String remark = addrBook.getRemark();
//				
//				Row row = sheet.getRow(k++);
//				for (Cell cell : row) {
//					cell.setCellType(CellType.STRING);
//					CellStyle style = cell.getCellStyle();
//					cell.setCellStyle(style);
//				}
//				row.getCell(0).setCellValue(String.valueOf(k-1));
//				row.getCell(1).setCellValue(parseValue(name));
//				row.getCell(2).setCellValue(parseValue(orgName));
//				row.getCell(3).setCellValue(parseValue(post, position));
//				row.getCell(4).setCellValue(parseValue(roomId));
//				row.getCell(5).setCellValue(parseValue(officePhone));
//				row.getCell(6).setCellValue(parseValue(salePhone));
//				row.getCell(7).setCellValue(parseValue(fax));
//				row.getCell(8).setCellValue(parseValue(remark));
//			}
//			
//			// 内容写入Excel文件
//			OutputStream fOut = new FileOutputStream(file);
//			workbook.write(fOut);
//			fOut.flush();
//			fOut.close();
//			return file;
//		}
		return null;
	}
	
	/**
	 * 转换空字符串
	 * @param v
	 * @return
	 */
	@SuppressWarnings("unused")
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
