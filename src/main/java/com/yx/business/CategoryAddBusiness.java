package com.yx.business;

import com.yx.DBConnect.DBOpration;
import com.yx.dao.Category;
import com.yx.dao.CategoryAdd;
import com.yx.dao.Department;
import com.yx.dao.IndexDetail;
import com.yx.dao.Type;

public class CategoryAddBusiness {

	public String excute(CategoryAdd ca){
		String businessKey = ca.getBusinessKey();
		String message = "";
		DBOpration dbo = new DBOpration();
		if ("0".equals(businessKey)){
			Category cat = new Category();
			cat = ca.getCategory();
			if (cat.getCategoryName().isEmpty()){
				message = "请填写一级指标名称！！";
			} else {
				if (dbo.excuteSQL(dbo.objInsertSql(cat))){
					message = "一级指标添加成功！！";
				} else {
					message = "一级指标添加失败！！";
				}
			}
		} else if ("1".equals(businessKey)) {
			Type type = new Type();
			type = ca.getType();
			if (type.getTypeName().isEmpty()){
				message = "请填写二级指标名称！！";
			} else {
				if (dbo.excuteSQL(dbo.objInsertSql(type))){
					message = "二级指标添加成功！！";
				} else {
					message = "二级指标添加失败！！";
				}
			}
		} else if ("2".equals(businessKey)) {
			IndexDetail id = new IndexDetail();
			id = ca.getIndexDetail();
			if (id.getIndexName().isEmpty()){
				message = "请填写指标名称！！";
			} else {
				if (dbo.excuteSQL(dbo.objInsertSql(id))){
					message = "明细指标添加成功！！";
				} else {
					message = "明细指标添加失败！！";
				}
			}
		} else if ("3".equals(businessKey)) {
			Department dep = new Department();
			dep = ca.getDepartment();
			if (dep.getName().isEmpty()){
				message = "请填写部门名称！！";
			} else {
				if (dbo.excuteSQL(dbo.objInsertSql(dep))){
					message = "部门添加成功！！";
				} else {
					message = "部门添加失败！！";
				}
			}
		}
		return message;
	}
}
