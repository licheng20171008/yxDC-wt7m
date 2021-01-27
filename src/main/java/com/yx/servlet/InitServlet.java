package com.yx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yx.DBConnect.DBOpration;
import com.yx.business.CategoryAddBusiness;
import com.yx.business.CategoryAddInit;
import com.yx.dao.Category;
import com.yx.dao.CategoryAdd;
import com.yx.dao.Department;
import com.yx.dao.IndexDetail;
import com.yx.dao.Type;

/**
 * 初始化添加页面
 */
public class InitServlet extends HttpServlet {

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
		CategoryAddBusiness cab = new CategoryAddBusiness();
		DBOpration dbo = new DBOpration();
		String hiddenValue = request.getParameter("hiddenValue");
		CategoryAdd afp = (CategoryAdd) request.getAttribute("CategoryAdd") == null?new CategoryAdd():(CategoryAdd) request.getAttribute("CategoryAdd");
		if (hiddenValue == null || hiddenValue.isEmpty()) {
			try {
				afp = (CategoryAdd) new RequestOperation().excute(request, response, afp);
			} catch (Exception e) {
				e.printStackTrace();
				afp.setMessage("参数出错！！");
			}
			String businessKey = afp.getBusinessKey();
			if ("0".equals(businessKey)) {
				// 部门
				afp.setDepartment((Department) dbo.InitBean(Department.class));

				// 二级分类指标
				afp.setType((Type) dbo.InitBean(Type.class));

				// 指标标准表单
				afp.setIndexDetail((IndexDetail) dbo.InitBean(IndexDetail.class));
			}else if ("1".equals(businessKey)) {
				// 部门
				afp.setDepartment((Department) dbo.InitBean(Department.class));
				
				// 一级分类指标
				afp.setCategory((Category) dbo.InitBean(Category.class));
				
				// 指标标准表单
				afp.setIndexDetail((IndexDetail) dbo.InitBean(IndexDetail.class));
			}else if ("2".equals(businessKey)) {
				// 部门
				afp.setDepartment((Department) dbo.InitBean(Department.class));

				// 一级分类指标
				afp.setCategory((Category) dbo.InitBean(Category.class));

				// 二级分类指标
				afp.setType((Type) dbo.InitBean(Type.class));
			}else if ("3".equals(businessKey)) {
				// 一级分类指标
				afp.setCategory((Category) dbo.InitBean(Category.class));

				// 二级分类指标
				afp.setType((Type) dbo.InitBean(Type.class));

				// 指标标准表单
				afp.setIndexDetail((IndexDetail) dbo.InitBean(IndexDetail.class));
			}
			if ("0".equals(afp.getCategory_detailHidden())){
				afp.setMessage(cab.excute(afp));
			}
			afp = new CategoryAddInit().excuteInit(afp);
			request.setAttribute("CategoryAdd", afp);
			request.getRequestDispatcher("/jsp/yxCategoryAdd.jsp").forward(request, response);
		} else {
			afp = new CategoryAddInit().excuteInit(afp);
			
			// 初始化
			// 隐藏控制项
			afp.setBusinessKey("3");
			
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
			request.getRequestDispatcher("/jsp/yxCategoryAdd.jsp").forward(request, response);
		}
		this.doGet(request, response);
	}
}