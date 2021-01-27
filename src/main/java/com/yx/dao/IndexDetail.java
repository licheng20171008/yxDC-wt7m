package com.yx.dao;

import java.util.Date;

/**
 * @author 李成
 * @version 1.0
 * 指标标准表单
 * */
public class IndexDetail {

	// 指标ID
	private int id;
	
	// 一级分类ID
	private String category_detail;
	
	// 二级分类ID
	private String type_detail;
	
	// 指标编号
	private String indexID;
	
	// 指标名称
	private String indexName;
	
	// 指标定义
	private String indexDetail;
	
	// 计算公式
	private String indexFormula;
	
	// 统计周期
	private String computingCycle;
	
	// 周期单位
	private String cycleUnit;
	
	// 所属科室
	private String department;
	
	// 备注
	private String remark;

	// 冻结时间
	private Date abateTime;
		
	public IndexDetail(){};
	
	// 取得指标ID
	public int getId() {
		return id;
	}

	// 设置指标ID
	public void setId(int id) {
		this.id = id;
	}

	// 取得一级分类ID
	public String getCategory_detail() {
		return category_detail;
	}

	// 设置一级分类ID
	public void setCategory_detail(String category_detail) {
		this.category_detail = category_detail;
	}

	// 取得二级分类ID
	public String getType_detail() {
		return type_detail;
	}

	// 设置二级分类ID
	public void setType_detail(String type_detail) {
		this.type_detail = type_detail;
	}

	// 取得编号
	public String getIndexID() {
		return indexID;
	}

	// 设置编号
	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}

	// 取得指标名称
	public String getIndexName() {
		return indexName;
	}

	// 设置指标名称
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	// 取得指标定义
	public String getIndexDetail() {
		return indexDetail;
	}

	// 设置指标定义
	public void setIndexDetail(String indexDetail) {
		this.indexDetail = indexDetail;
	}

	// 取得计算公式
	public String getIndexFormula() {
		return indexFormula;
	}

	// 设置计算公式
	public void setIndexFormula(String indexFormula) {
		this.indexFormula = indexFormula;
	}

	// 取得统计周期
	public String getComputingCycle() {
		return computingCycle;
	}

	// 设置统计周期
	public void setComputingCycle(String computingCycle) {
		this.computingCycle = computingCycle;
	}

	// 取得周期单位
	public String getCycleUnit() {
		return cycleUnit;
	}

	// 设置周期单位
	public void setCycleUnit(String cycleUnit) {
		this.cycleUnit = cycleUnit;
	}

	// 取得所属科室
	public String getDepartment() {
		return department;
	}

	// 设置所属科室
	public void setDepartment(String department) {
		this.department = department;
	}

	// 取得备注
	public String getRemark() {
		return remark;
	}

	// 设置备注
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	// 取得冻结时间
	public Date getAbateTime() {
		return abateTime;
	}

	// 设置冻结时间
	public void setAbateTime(Date abateTime) {
		this.abateTime = abateTime;
	}
}
