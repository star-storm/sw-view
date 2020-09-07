/**
 * 
 */
package com.taiji.admin.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

import com.taiji.admin.utils.RandomUtil;

/**
 * sw-view com.taiji.admin.test PoiExcelTest.java
 *
 * @author hsl
 *
 * 2020年1月9日 下午5:57:06
 *
 * desc:
 */
public class PoiExcelTest {
	
	@SuppressWarnings("resource")
	private static void testXSSFWorkbook() throws IOException {
		File file = new File("D:\\work\\workspace3\\jgj-txl\\target\\classes\\templates\\upload\\通讯录.xlsx");
		if (file.exists())
			file.delete();
		file.createNewFile();
		if (file.exists()) {
			//创建工作薄和sheet
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("通讯录");
			sheet.setDefaultColumnWidth(20);// 默认列宽
			//创建行和单元格
			for (int i=0; i<4; i++) {
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
			headRow.getCell(2).setCellValue("所在部门");
			headRow.getCell(3).setCellValue("职务职级");
			headRow.getCell(4).setCellValue("房间号");
			headRow.getCell(5).setCellValue("办公电话");
			headRow.getCell(6).setCellValue("手机");
			headRow.getCell(7).setCellValue("传真分机号");
			headRow.getCell(8).setCellValue("备注");
			headRow = sheet.getRow(1);
			headRow.getCell(0).setCellValue(RandomUtil.getRandom());
			headRow.getCell(1).setCellValue(RandomUtil.randomString(12000));
			headRow.getCell(2).setCellValue(RandomUtil.randomString(12000));
			headRow.getCell(3).setCellValue(RandomUtil.randomString(12000));
			headRow.getCell(4).setCellValue(RandomUtil.randomString(12000));
			headRow.getCell(5).setCellValue(RandomUtil.randomString(12000));
			headRow.getCell(6).setCellValue(RandomUtil.randomString(12000));
			headRow.getCell(7).setCellValue(RandomUtil.randomString(12000));
			headRow.getCell(8).setCellValue(RandomUtil.randomString(12000));
			
			// 内容写入Excel文件
			OutputStream fOut = new FileOutputStream(file);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		testXSSFWorkbook();
	}

}
