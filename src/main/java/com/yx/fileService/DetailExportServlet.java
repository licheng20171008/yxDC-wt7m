package com.yx.fileService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yx.DBConnect.DBOpration;
import com.yx.dao.Category;
import com.yx.dao.CategoryAdd;
import com.yx.dao.Common;
import com.yx.dao.Department;
import com.yx.dao.IndexDetail;
import com.yx.dao.Type;
import com.yx.servlet.RequestOperation;

/**
 * 初始化添加页面
 */
public class DetailExportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DBOpration dbo = new DBOpration();
		List<Object> objList = null;
		String sql = "";
		String fileTitle = "";
		String[] itemArray = null;
		CategoryAdd afp = (CategoryAdd) request.getAttribute("CategoryAdd") == null ? 
			new CategoryAdd() : (CategoryAdd) request.getAttribute("CategoryAdd");
			
		// 系统时间
		String sysDate = new Common().sysDate("yyyy-MM-dd");
		try {
			afp = (CategoryAdd) new RequestOperation().excute(request, response, afp);
		} catch (Exception e) {
			e.printStackTrace();
			afp.setMessage("参数出错！！");
		}
		String businessKey = afp.getBusinessKey();
		if ("0".equals(businessKey)) {
			afp.setCategory(new Category());
			afp.setType(new Type());
			afp.setIndexDetail(new IndexDetail());
			sql = dbo.javaBeanSQL(afp.getDepartment(), afp.getSelectBox());
			fileTitle = "部门基本信息" + sysDate;
			objList = dbo.excuteSQL(sql, Department.class);
			itemArray = new String[3];
			itemArray[0] = "部门ID";
			itemArray[1] = "部门名称";
			itemArray[2] = "冻结时间";
		} else if ("1".equals(businessKey)) {
			afp.setDepartment(new Department());
			afp.setType(new Type());
			afp.setIndexDetail(new IndexDetail());
			sql = dbo.javaBeanSQL(afp.getCategory(), afp.getSelectBox());
			fileTitle = "一级分类信息" + sysDate;
			objList = dbo.excuteSQL(sql, Category.class);
			itemArray = new String[3];
			itemArray[0] = "一级分类ID";
			itemArray[1] = "一级分类名称";
			itemArray[2] = "冻结时间";
		} else if ("2".equals(businessKey)) {
			afp.setDepartment(new Department());
			afp.setCategory(new Category());
			afp.setIndexDetail(new IndexDetail());
			sql = dbo.javaBeanSQL(afp.getType(), afp.getSelectBox());
			fileTitle = "二级分类信息" + sysDate;
			objList = dbo.excuteSQL(sql, Type.class);
			itemArray = new String[4];
			itemArray[0] = "二级分类ID";
			itemArray[1] = "二级分类名称";
			itemArray[2] = "关联一级类别";
			itemArray[3] = "冻结时间";
		} else if ("3".equals(businessKey)) {
			afp.setDepartment(new Department());
			afp.setCategory(new Category());
			afp.setType(new Type());
			sql = dbo.javaBeanSQL(afp.getIndexDetail(), afp.getSelectBox());
			fileTitle = "指标明细信息" + sysDate;
			objList = dbo.excuteSQL(sql, IndexDetail.class);
			itemArray = new String[12];
			itemArray[0] = "指标ID";
			itemArray[1] = "一级分类ID";
			itemArray[2] = "二级分类ID";
			itemArray[3] = "指标编号";
			itemArray[4] = "指标名称";
			itemArray[5] = "指标定义";
			itemArray[6] = "计算公式";
			itemArray[7] = "统计周期";
			itemArray[8] = "周期单位";
			itemArray[9] = "所属科室";
			itemArray[10] = "备注";
			itemArray[11] = "冻结时间";
		}
		String servletPath = request.getSession().getServletContext().getRealPath("filePath") + File.separator + fileTitle + ".xls";
		new ExportExcel().writeFile(objList, servletPath, itemArray, fileTitle);
		new Common().download(servletPath, response);
		request.setAttribute("CategoryAdd", afp);
		request.getRequestDispatcher("/jsp/yxCategoryEdit.jsp");
		this.doGet(request, response);
	}
}