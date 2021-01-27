package com.yx.dao;

import java.util.Date;

public class Department {

	// 部门ID
	private int id;
	
	// 部门名称
	private String name;
	
	// 冻结时间
	private Date abateTime;
		
	public Department(){}
	
	// 取得部门ID
	public int getId() {
		return id;
	}
	
	// 设置部门ID
	public void setId(int id) {
		this.id = id;
	}
	
	// 取得部门名称
	public String getName() {
		return name;
	}
	
	// 设置部门名称
	public void setName(String name) {
		this.name = name;
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
