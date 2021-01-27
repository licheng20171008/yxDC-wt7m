package com.yx.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

/*
 * 公用类
 */
public final class Common {

	/*
	 * 系统时间根据类型返回
	 * 
	 * @param dateFormat 时间格式
	 * @return 字符串时间
	 */
	public String sysDate (String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}
	
	/*
	 * 字符串类型的数字前面添加0
	 * 
	 * @param param 字符串
	 * @param length 字符串加0前的可能长度
	 * @param size 字符串加0后的总长度
	 * @return 加0后的字符串
	 */
	public String addZero (String param, int length, int size) {
		return param.length() < size?String.format("%0" + size + "d", Integer.parseInt(param)):param;
	}
	
	/*
	 * 文件下载
	 * 
	 * @param path 文件地址
	 * @param response
	 */
	public void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO-8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/vnd.ms-excel");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
