package com.yx.dao;

import java.util.Date;

/**
 * @author 李成
 * @version 1.0
 * 二级分类指标
 * */
public class Type {

	// 二级分类ID
	private int id;

	// 二级分类名称
	private String typeName;
	
	// 关联一级类别
	private String category_type;
	
	// 冻结时间
	private Date abateTime;
		
	public Type(){};
	
	// 取得二级分类ID
	public int getId() {
		return id;
	}

	// 设置二级分类ID
	public void setId(int id) {
		this.id = id;
	}

	// 取得关联一级分类ID
	public String getCategory_type() {
		return category_type;
	}

	// 设置关联一级分类ID
	public void setCategory_type(String category_type) {
		this.category_type = category_type;
	}

	// 取得二级分类名称
	public String getTypeName() {
		return typeName;
	}

	// 设置二级分类名称
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
