package com.yx.fileService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yx.business.FileHandle;
import com.yx.business.FileReader;
import com.yx.dao.IndexDetail;

public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String hiddenValue = request.getParameter("hiddenValue");
		String message = "";
		if (hiddenValue == null || hiddenValue.isEmpty()){
			FileReader fr = new FileReader(request);
			Map<String, Object> frMap = fr.resultMap;
			message = (String) frMap.get("message");
			if (message == null) {
				message = "";
			}
			if(message.isEmpty()){
				message = new FileHandle().checkFile((List<IndexDetail>)frMap.get("data"));
			}
			if(message.isEmpty()){
				message = new FileHandle().excute((List<IndexDetail>)frMap.get("data"));
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/jsp/fileUpload.jsp").forward(request, response);
		this.doGet(request, response);
	}

}
