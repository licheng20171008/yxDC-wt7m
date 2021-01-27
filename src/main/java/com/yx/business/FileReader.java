package com.yx.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yx.dao.IndexDetail;

public class FileReader {

	// 返回MAP
	public Map<String, Object> resultMap = new HashMap<String, Object>();
	private String message = "";

	// sheet数据集合
	List<IndexDetail> idList = null;

	// 文件二进制输入流
	private InputStream is = null;

	// 当前位置
	private int currPosition = 0;

	// sheet数量
	private int numOfSheets = 0;

	// HSSFWorkbook
	Workbook workbook = null;

	public FileReader(HttpServletRequest request) {
		
		// 文件名
		String fileName = "";
		
		// 文件类型
		String fileType = "";
		
		// 使用Apache文件上传组件处理文件上传步骤
		// 1.创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 2.创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		
		// 3.判断提交上来的数据是否是上传表单的数据
		if (!ServletFileUpload.isMultipartContent(request)) {
			
			// 按照传统方式获取数据
			message = "文件上传方式错误！！";
		}
		
		// 4.使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合
		// 每一个FileItem对应一个Form表单的输入项
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> fileItemList = upload.parseRequest(request);
			for (FileItem fileItem : fileItemList) {

				// 判断上传文件
				if (!fileItem.isFormField()) {
					fileName = fileItem.getName();
					if (fileName == null || "".equals(fileName.trim())) {
						message = "请上传文件！！";
						resultMap.put("message", message);
						continue;
					} else {

						// 注意：不同的浏览器提交的文件名不一样，去除路径
						// 处理获得的上传文件的文件名的路径部分，只保留文件名部分
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

						// 取得文件名的后缀名赋值给filetype
						fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

						// 判断文件类型
						//  && !fileType.equalsIgnoreCase("xls")
						if (!fileType.equalsIgnoreCase("xlsx")) {
							message = "请上传EXCEL文件！！";
							resultMap.put("message", message);
						} else {
							// 设置开始为0
							currPosition = 0;

							try {
								// 创建文件输入流
								is = fileItem.getInputStream();

								// 如果是EXCEL文件则创建HSSFWorkbook读取
								workbook = new XSSFWorkbook(is);

								// 设置Sheet数
								numOfSheets = workbook.getNumberOfSheets();

								// 读取数据
								this.readLine();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								this.close();
							}
						}
						break;
					}
				}
			}
		} catch (FileUploadException e1) {
			e1.printStackTrace();
			message = "文件上传失败！！";
			resultMap.put("message", message);
		}
	}

	// 函数readLine读取文件的一行
	public void readLine() throws IOException {

		// 当前sheet
		int currSheet = 0;
		
		// 初始化SHEET
		XSSFSheet sheet = null;

		// 判断是否还有Sheet
		while (currSheet < numOfSheets) {

			// 根据currSheet值获得当前的SHEET
			sheet = (XSSFSheet) workbook.getSheetAt(currSheet);

			idList = new ArrayList<IndexDetail>();

			// 初始化当前SHEET位置
			currPosition = 0;

			// 读取当前行数据
			while (currPosition <= sheet.getLastRowNum()) {
				// 获取当前行数
				int row = currPosition;
				currPosition++;

				// 读取当前行数据
				this.getXssfSheetLine(sheet, row);
			}

			resultMap.put("data", idList);
			// 当前行数是否已经到达文件末尾
			if (currPosition == (sheet.getLastRowNum() + 1)) {

				// 当前SHEET指向下一张sheet
				currSheet++;
				continue;
			}
		}
	}

	// 函数getLine返回Sheet的一行数据
	private void getXssfSheetLine(XSSFSheet sheet, int row) {

		IndexDetail id = new IndexDetail();
		
		// 根据行数取得sheet的一行
		XSSFRow rowline = sheet.getRow(row);
		
		// 单元格获取
		XSSFCell cell = null;

		// 循环遍历所有列
		if (row > 1) {
			
			// 行数
			id.setId(row + 1);
			
			// 一级分类
			cell = rowline.getCell(0);
			id.setCategory_detail(getCellValue(cell));
			
			// 二级分类
			cell = rowline.getCell(1);
			id.setType_detail(getCellValue(cell));
			
			// 编号
			cell = rowline.getCell(2);
			id.setIndexID(getCellValue(cell));
			
			// 指标名称
			cell = rowline.getCell(3);
			id.setIndexName(getCellValue(cell));
			
			// 指标定义
			cell = rowline.getCell(4);
			id.setIndexDetail(getCellValue(cell));
			
			// 计算公式
			cell = rowline.getCell(5);
			id.setIndexFormula(getCellValue(cell));
			
			// 统计周期
			cell = rowline.getCell(6);
			id.setComputingCycle(getCellValue(cell));
			
			// 周期单位
			cell = rowline.getCell(7);
			id.setCycleUnit(getCellValue(cell));
			
			// 所属科室
			cell = rowline.getCell(8);
			id.setDepartment(getCellValue(cell));
			idList.add(id);
		}
	}

	private String getCellValue(XSSFCell cell) {
		
		String cellvalue = "";
		if (cell != null) {
			// 判断当前cell的type
			switch (cell.getCellType()) {
			// 如果当前cell的type为NUMERIC
			case XSSFCell.CELL_TYPE_NUMERIC: {
				// 判断当前cell是否为date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {

					// 把date转换成本地格式的字符串
					cellvalue = cell.getDateCellValue().toString();
				} else {
					// 如果是纯数字
					// 取得当前cell的数值
					Integer num = new Integer((int) cell.getNumericCellValue());
					cellvalue = String.valueOf(num);
				}
				break;

				// 如果当前cell的type为STRING
			}
			case HSSFCell.CELL_TYPE_STRING: {
				// 取得当前的cell字符串
				cellvalue = cell.getStringCellValue().replaceAll("'", "");
				for(int j=10;j<14;j++){  
					cellvalue = cellvalue.replaceAll(String.valueOf((char)j), "");  
			    }
				break;
			}
			}
		}
		return cellvalue;
	}

	// close函数执行流的关闭操作
	public void close() {
		// 如果IS为空，则关闭InputStream文件输入流
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				is = null;
			}
		}
	}
}
