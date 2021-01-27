package com.yx.dao;

import java.util.Date;

/**
 * @author 李成
 * @version 1.0
 * 一级分类指标
 * */
public class Category {

	// 一级分类ID
	private int id;
	
	// 一级分类名称
	private String categoryName;
	
	// 冻结时间
	private Date abateTime;
	
	public Category(){}
	
	// 取得一级分类ID
	public int getId() {
		return id;
	}
	
	// 设置一级分类ID
	public void setId(int id) {
		this.id = id;
	}
	
	// 取得一级分类名称
	public String getCategoryName() {
		return categoryName;
	}
	
	//设置一级分类名称
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	// 取得冻结时间
	public Date getAbateTime() {
		return abateTime;
	}

	//设置冻结时间
	public void setAbateTime(Date abateTime) {
		this.abateTime = abateTime;
	}
}
