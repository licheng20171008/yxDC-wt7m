package com.yx.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李成
 * @version 1.0 表单提交项目
 */
public class CategoryAdd {

	// 隐藏控制项
	private String businessKey;

	// 一级分类指标
	private Category category;

	// 一级分类指标集合
	private List<Category> categoryList = new ArrayList<Category>();
	
	// 一级分类指标显示集合
	private List<Category> categoryViewList = new ArrayList<Category>();
	
	// 一级分类条数
	private int catCount;
	
	// 一级分类当前页数
	private int catCurPage;
	
	// 一级分类总页数
	private int catCountPage;

	// 二级分类指标
	private Type type;

	// 二级分类指标集合
	private List<Type> typeList = new ArrayList<Type>();
	
	// 二级分类指标显示集合
	private List<Type> typeViewList = new ArrayList<Type>();
	
	// 二级分类条数
	private int typeCount;
	
	// 二级分类当前页数
	private int typeCurPage;
	
	// 二级分类总页数
	private int typeCountPage;

	// 指标标准表单
	private IndexDetail indexDetail;
	
	// 指标标准表单集合
	private List<IndexDetail> idList = new ArrayList<IndexDetail>();
	
	// 指标条数
	private int idCount;
	
	// 指标当前页数
	private int idCurPage;
	
	// 指标总页数
	private int idCountPage;
	
	// 部门
	private Department department;
	
	// 部门集合
	private List<Department> depList = new ArrayList<Department>();
	
	// 部门条数
	private int depCount;
	
	// 部门当前页数
	private int depCurPage;
	
	// 部门总页数
	private int depCountPage;
	
	// 页面信息
	private String message;

	// 联动选项
	private String category_detailHidden;
	
	// 传递参数
	private String operation;
	
	// 模糊查询
	private String selectBox;

	// 取得一级分类指标
	public Category getCategory() {
		return category;
	}

	// 设置一级分类指标
	public void setCategory(Category category) {
		this.category = category;
	}

	// 取得二级分类指标
	public Type getType() {
		return type;
	}

	// 设置二级分类指标
	public void setType(Type type) {
		this.type = type;
	}

	// 取得指标标准表单
	public IndexDetail getIndexDetail() {
		return indexDetail;
	}

	// 设置指标标准表单
	public void setIndexDetail(IndexDetail indexDetail) {
		this.indexDetail = indexDetail;
	}

	// 取得隐藏控制项
	public String getBusinessKey() {
		return businessKey;
	}

	// 设置隐藏控制项
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	// 取得一级分类指标集合
	public List<Category> getCategoryList() {
		return categoryList;
	}

	// 设置一级分类指标集合
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	// 取得二级分类指标集合
	public List<Type> getTypeList() {
		return typeList;
	}

	// 设置二级分类指标集合
	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	// 取得页面信息
	public String getMessage() {
		return message;
	}

	// 设置页面信息
	public void setMessage(String message) {
		this.message = message;
	}

	// 取得联动选项
	public String getCategory_detailHidden() {
		return category_detailHidden;
	}

	// 设置联动选项
	public void setCategory_detailHidden(String category_detailHidden) {
		this.category_detailHidden = category_detailHidden;
	}

	// 取得传递参数
	public String getOperation() {
		return operation;
	}

	// 设置传递参数
	public void setOperation(String operation) {
		this.operation = operation;
	}

	// 取得部门
	public Department getDepartment() {
		return department;
	}

	// 设置部门
	public void setDepartment(Department department) {
		this.department = department;
	}

	// 取得指标标准表单集合
	public List<IndexDetail> getIdList() {
		return idList;
	}

	// 设置指标标准表单集合
	public void setIdList(List<IndexDetail> idList) {
		this.idList = idList;
	}

	// 取得部门集合
	public List<Department> getDepList() {
		return depList;
	}

	// 设置部门集合
	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

	// 取得一级分类条数
	public int getCatCount() {
		return catCount;
	}

	// 设置一级分类条数
	public void setCatCount(int catCount) {
		this.catCount = catCount;
	}

	// 取得二级分类条数
	public int getTypeCount() {
		return typeCount;
	}

	// 设置二级分类条数
	public void setTypeCount(int typeCount) {
		this.typeCount = typeCount;
	}

	// 取得指标条数
	public int getIdCount() {
		return idCount;
	}

	// 设置指标条数
	public void setIdCount(int idCount) {
		this.idCount = idCount;
	}

	// 取得部门条数
	public int getDepCount() {
		return depCount;
	}

	// 设置部门条数
	public void setDepCount(int depCount) {
		this.depCount = depCount;
	}

	// 取得部门当前页数
	public int getCatCurPage() {
		return catCurPage;
	}

	// 设置部门当前页数
	public void setCatCurPage(int catCurPage) {
		this.catCurPage = catCurPage;
	}

	// 取得部门当前页数
	public int getTypeCurPage() {
		return typeCurPage;
	}

	// 设置部门当前页数
	public void setTypeCurPage(int typeCurPage) {
		this.typeCurPage = typeCurPage;
	}

	// 取得部门当前页数
	public int getIdCurPage() {
		return idCurPage;
	}

	// 设置部门当前页数
	public void setIdCurPage(int idCurPage) {
		this.idCurPage = idCurPage;
	}

	// 取得部门当前页数
	public int getDepCurPage() {
		return depCurPage;
	}

	// 设置部门当前页数
	public void setDepCurPage(int depCurPage) {
		this.depCurPage = depCurPage;
	}

	// 取得一级分类总页数
	public int getCatCountPage() {
		return catCountPage;
	}

	// 设置一级分类总页数
	public void setCatCountPage(int catCountPage) {
		this.catCountPage = catCountPage;
	}

	// 取得二级分类总页数
	public int getTypeCountPage() {
		return typeCountPage;
	}

	// 设置二级分类总页数
	public void setTypeCountPage(int typeCountPage) {
		this.typeCountPage = typeCountPage;
	}

	// 取得指标总页数
	public int getIdCountPage() {
		return idCountPage;
	}

	// 设置指标总页数
	public void setIdCountPage(int idCountPage) {
		this.idCountPage = idCountPage;
	}

	// 取得部门总页数
	public int getDepCountPage() {
		return depCountPage;
	}

	// 设置部门总页数
	public void setDepCountPage(int depCountPage) {
		this.depCountPage = depCountPage;
	}

	// 取得一级分类指标显示集合
	public List<Category> getCategoryViewList() {
		return categoryViewList;
	}

	// 设置一级分类指标显示集合
	public void setCategoryViewList(List<Category> categoryViewList) {
		this.categoryViewList = categoryViewList;
	}

	// 取得二级分类指标显示集合
	public List<Type> getTypeViewList() {
		return typeViewList;
	}

	// 设置二级分类指标显示集合
	public void setTypeViewList(List<Type> typeViewList) {
		this.typeViewList = typeViewList;
	}

	// 取得模糊查询
	public String getSelectBox() {
		return selectBox;
	}

	// 设置模糊查询
	public void setSelectBox(String selectBox) {
		this.selectBox = selectBox;
	}
}