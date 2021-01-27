package com.yx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yx.DBConnect.DBOpration;
import com.yx.business.CategoryAddInit;
import com.yx.business.CategoryEditBusiness;
import com.yx.dao.Category;
import com.yx.dao.CategoryAdd;
import com.yx.dao.Department;
import com.yx.dao.IndexDetail;
import com.yx.dao.Type;

/**
 * 初始化添加页面
 */
public class ReportServlet extends HttpServlet {

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
		HttpSession session = request.getSession(true);
		CategoryEditBusiness ceb = new CategoryEditBusiness();
		DBOpration dbo = new DBOpration();
		String hiddenValue = request.getParameter("hiddenValue");
		CategoryAdd afp = (CategoryAdd) request.getAttribute("CategoryAdd") == null?
			new CategoryAdd():(CategoryAdd) request.getAttribute("CategoryAdd");
		if (hiddenValue == null || hiddenValue.isEmpty()) {
			try {
				afp = (CategoryAdd) new RequestOperation().excute(request, response, afp);
			} catch (Exception e) {
				e.printStackTrace();
				afp.setMessage("参数出错！！");
			}
			String businessKey = afp.getBusinessKey();
			if ("0".equals(businessKey)){
				// 一级分类指标
				afp.setCategory((Category) dbo.InitBean(Category.class));

				// 二级分类指标
				afp.setType((Type) dbo.InitBean(Type.class));

				// 指标标准表单
				afp.setIndexDetail((IndexDetail) dbo.InitBean(IndexDetail.class));
				if (afp.getDepCurPage() == 0){
					afp.setDepCurPage(1);
					session.setAttribute("department", afp.getDepartment());
				} else {
					afp.setDepartment((Department) session.getAttribute("department"));
				}
			} else if ("1".equals(businessKey)){
				// 部门
				afp.setDepartment((Department) dbo.InitBean(Department.class));

				// 二级分类指标
				afp.setType((Type) dbo.InitBean(Type.class));

				// 指标标准表单
				afp.setIndexDetail((IndexDetail) dbo.InitBean(IndexDetail.class));
				if (afp.getCatCurPage() == 0){
					afp.setCatCurPage(1);
					session.setAttribute("category", afp.getCategory());
				} else {
					afp.setCategory((Category) session.getAttribute("category"));
				}
			} else if ("2".equals(businessKey)){
				// 部门
				afp.setDepartment((Department) dbo.InitBean(Department.class));
				
				// 一级分类指标
				afp.setCategory((Category) dbo.InitBean(Category.class));
				
				// 指标标准表单
				afp.setIndexDetail((IndexDetail) dbo.InitBean(IndexDetail.class));
				if (afp.getTypeCurPage() == 0){
					afp.setTypeCurPage(1);
					session.setAttribute("type", afp.getType());
				} else {
					afp.setType((Type) session.getAttribute("type"));
				}
			} else if ("3".equals(businessKey)){
				// 部门
				afp.setDepartment((Department) dbo.InitBean(Department.class));

				// 一级分类指标
				afp.setCategory((Category) dbo.InitBean(Category.class));

				// 二级分类指标
				afp.setType((Type) dbo.InitBean(Type.class));
				if (afp.getIdCurPage() == 0){
					afp.setIdCurPage(1);
					session.setAttribute("indexDetail", afp.getIndexDetail());
				} else {
					afp.setIndexDetail((IndexDetail) session.getAttribute("indexDetail"));
				}
			}
			afp = ceb.excute(afp);
			afp = new CategoryAddInit().excuteInit(afp);
			request.setAttribute("CategoryAdd", afp);
			request.getRequestDispatcher("/jsp/yxCategoryEdit.jsp").forward(request, response);
		} else {
			afp = new CategoryAddInit().excuteInit(afp);
			
			// 选项初始化
			afp.setBusinessKey("0");
			afp.setSelectBox("1");
			
			// 页面信息
			afp.setMessage("");
			
			// 部门
			afp.setDepartment((Department) dbo.InitBean(Department.class));

			// 一级分类指标
			afp.setCategory((Category) dbo.InitBean(Category.class));

			// 二级分类指标
			afp.setType((Type) dbo.InitBean(Type.class));

			// 指标标准表单
			afp.setIndexDetail((IndexDetail) dbo.InitBean(IndexDetail.class));
			request.setAttribute("CategoryAdd", afp);
			request.getRequestDispatcher("/jsp/yxCategoryEdit.jsp").forward(request, response);
		}
		this.doGet(request, response);
	}
}