package com.yx.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yx.DBConnect.DBOpration;
import com.yx.dao.Category;
import com.yx.dao.CategoryAdd;
import com.yx.dao.Common;
import com.yx.dao.Department;
import com.yx.dao.IndexDetail;
import com.yx.dao.Type;

public class CategoryEditBusiness {

	public CategoryAdd excute(CategoryAdd ca){
		String businessKey = ca.getBusinessKey();
		String operation = ca.getOperation();
		Map<String, String> map = new HashMap<String, String>();
		List<Object> objList = null;
		List<Object> objListTemp = null;
		DBOpration dbo = new DBOpration();
		String selectBox = ca.getSelectBox();
		String sql = "";
		if ("0".equals(businessKey)){
			List<Department> depList = new ArrayList<Department>();
			List<Department> depListTemp = new ArrayList<Department>();
			Department dep = new Department();
			dep = ca.getDepartment();
			String depName = dep.getName();
			int depid = dep.getId();
			map.put("name", depName);
			if (!depName.isEmpty()){
				if ("1".equals(selectBox)){
					sql = dbo.selectLikeWhere("department", map);
				} else {
					sql = dbo.selectWhere("department", map);
				}
			} else {
				sql = dbo.tableforAll("department");
			}
			if ("1".equals(operation)) {
				if (dbo.updateById("department", map, depid)) {
					ca.setMessage("部门名称修改正确！！");
				} else {
					ca.setMessage("部门名称修改失败！！");
				}
			} else if ("2".equals(operation)) {
				map.put("id", String.valueOf(depid));
				int checkCount = dbo.countSql("department", map);
				if (checkCount == 0) {
					ca.setMessage("请在修改部门名称后点击修改按钮，否则冻结/解冻操作不成功！！");
				} else {
					map.remove("name");
					map.put("abateTime", "NULL");
					int count = dbo.countSql("department", map);
					if (count != 0) {
						map.remove("abateTime");
						map.remove("id");
						map.put("abateTime", new Common().sysDate("yyyy-MM-dd"));
						if (dbo.updateById("department", map, depid)) {
							ca.setMessage("部门冻结成功！！");
						} else {
							ca.setMessage("部门冻结失败！！");
						}
					} else {
						map.remove("id");
						if (dbo.updateById("department", map, depid)) {
							ca.setMessage("部门解冻成功！！");
						} else {
							ca.setMessage("部门解冻失败！！");
						}
					}
				}
			}
			objListTemp = dbo.excuteSQL(sql, Department.class);
			for(Object obj0 : objListTemp){
				depListTemp.add((Department) obj0);
			}
			ca.setDepCount(depListTemp.size());
			if (depListTemp.size()%20 == 0){
				ca.setDepCountPage(depListTemp.size()/20);
			} else {
				ca.setDepCountPage(depListTemp.size()/20 + 1);
			}
			sql = sql +" limit " + (ca.getDepCurPage() - 1)*20 + ", " + ca.getDepCurPage()*20;
			objList = dbo.excuteSQL(sql, Department.class);
			for(Object obj : objList){
				depList.add((Department) obj);
			}
			ca.setDepList(depList);
		} else if ("1".equals(businessKey)){
			List<Category> catList = new ArrayList<Category>();
			List<Category> catListTemp = new ArrayList<Category>();
			Category cat = new Category();
			cat = ca.getCategory();
			String categoryName = cat.getCategoryName();
			int catid = cat.getId();
			if (!categoryName.isEmpty()){
				map.put("categoryName", categoryName);
				if ("1".equals(selectBox)){
					sql = dbo.selectLikeWhere("category", map);
				} else {
					sql = dbo.selectWhere("category", map);
				}
			} else {
				sql = dbo.tableforAll("category");
			}
			if ("1".equals(operation)) {
				if (dbo.updateById("category", map, catid)) {
					ca.setMessage("一级分类名称修改正确！！");
				} else {
					ca.setMessage("一级分类名称修改失败！！");
				}
			} else if ("2".equals(operation)) {
				map.put("id", String.valueOf(catid));
				int checkCount = dbo.countSql("category", map);
				if (checkCount == 0) {
					ca.setMessage("请在修改一级分类名称后点击修改按钮，否则冻结/解冻操作不成功！！");
				} else {
					map.remove("categoryName");
					map.put("abateTime", "NULL");
					int count = dbo.countSql("category", map);
					if (count != 0) {
						map.remove("abateTime");
						map.remove("id");
						map.put("abateTime", new Common().sysDate("yyyy-MM-dd"));
						if (dbo.updateById("category", map, catid)) {
							ca.setMessage("一级分类冻结成功！！");
						} else {
							ca.setMessage("一级分类冻结失败！！");
						}
					} else {
						map.remove("id");
						if (dbo.updateById("category", map, catid)) {
							ca.setMessage("一级分类解冻成功！！");
						} else {
							ca.setMessage("一级分类解冻失败！！");
						}
					}
				}
			}
			objListTemp = dbo.excuteSQL(sql, Category.class);
			for(Object obj0 : objListTemp){
				catListTemp.add((Category) obj0);
			}
			ca.setCatCount(catListTemp.size());
			if (catListTemp.size()%20 == 0){
				ca.setCatCountPage(catListTemp.size()/20);
			} else {
				ca.setCatCountPage(catListTemp.size()/20 + 1);
			}
			sql = sql +" limit " + (ca.getCatCurPage() - 1)*20 + ", " + ca.getCatCurPage()*20;
			objList = dbo.excuteSQL(sql, Category.class);
			for(Object obj : objList){
				catList.add((Category) obj);
			}
			ca.setCategoryViewList(catList);
		
		} else if ("2".equals(businessKey)) {
			List<Type> typeList = new ArrayList<Type>();
			List<Type> typeListTemp = new ArrayList<Type>();
			Type type = new Type();
			type = ca.getType();
			String typeName = type.getTypeName();
			int typeid = type.getId();
			map.put("typeName", typeName);
			if (!typeName.isEmpty()){
				if ("1".equals(selectBox)){
					sql = dbo.selectLikeWhere("type", map);
				} else {
					sql = dbo.selectWhere("type", map);
				}
			} else {
				sql = dbo.tableforAll("type");
			}
			if ("1".equals(operation)) {
				if (dbo.updateById("type", map, typeid)) {
					ca.setMessage("二级分类名称修改正确！！");
				} else {
					ca.setMessage("二级分类名称修改失败！！");
				}
			} else if ("2".equals(operation)) {
				map.put("id", String.valueOf(typeid));
				int checkCount = dbo.countSql("type", map);
				if (checkCount == 0) {
					ca.setMessage("请在修改二级分类名称后点击修改按钮，否则冻结/解冻操作不成功！！");
				} else {
					map.remove("typeName");
					map.put("abateTime", "NULL");
					int count = dbo.countSql("type", map);
					if (count != 0) {
						map.remove("abateTime");
						map.remove("id");
						map.put("abateTime", new Common().sysDate("yyyy-MM-dd"));
						if (dbo.updateById("type", map, typeid)) {
							ca.setMessage("二级分类冻结成功！！");
						} else {
							ca.setMessage("二级分类冻结失败！！");
						}
					} else {
						map.remove("id");
						if (dbo.updateById("type", map, typeid)) {
							ca.setMessage("二级分类解冻成功！！");
						} else {
							ca.setMessage("二级分类解冻失败！！");
						}
					}
				}
			}
			objListTemp = dbo.excuteSQL(sql, Type.class);
			for(Object obj0 : objListTemp){
				typeListTemp.add((Type) obj0);
			}
			ca.setTypeCount(typeListTemp.size());
			if (typeListTemp.size()%20 == 0){
				ca.setTypeCountPage(typeListTemp.size()/20);
			} else {
				ca.setTypeCountPage(typeListTemp.size()/20 + 1);
			}
			sql = sql +" limit " + (ca.getTypeCurPage() - 1)*20 + ", " + ca.getTypeCurPage()*20;
			objList = dbo.excuteSQL(sql, Type.class);
			for(Object obj : objList){
				typeList.add((Type) obj);
			}
			ca.setTypeViewList(typeList);
		} else if ("3".equals(businessKey)) {
			List<IndexDetail> idList = new ArrayList<IndexDetail>();
			List<IndexDetail> idListTemp = new ArrayList<IndexDetail>();
			IndexDetail id = new IndexDetail();
			id = ca.getIndexDetail();
			String category_detail = id.getCategory_detail();
			String type_detail = id.getType_detail();
			String indexID = id.getIndexID();
			String indexName = id.getIndexName();
			String indexDetail = id.getIndexDetail();
			String indexFormula = id.getIndexFormula();
			String computingCycle = id.getComputingCycle();
			String cycleUnit = id.getCycleUnit();
			String department = id.getDepartment();
			String remark = id.getRemark();
			int idid = id.getId();
			map.put("category_detail", category_detail);
			map.put("type_detail", type_detail);
			map.put("indexID", indexID);
			map.put("indexName", indexName);
			map.put("indexDetail", indexDetail);
			map.put("indexFormula", indexFormula);
			map.put("computingCycle", computingCycle);
			map.put("cycleUnit", cycleUnit);
			map.put("department", department);
			map.put("remark", remark);
			if (!category_detail.isEmpty() || !type_detail.isEmpty() || !indexID.isEmpty() || !indexName.isEmpty() 
					|| !indexDetail.isEmpty() || !indexFormula.isEmpty() || !computingCycle.isEmpty() || !cycleUnit.isEmpty() 
					|| !department.isEmpty() || !remark.isEmpty()){
				if ("1".equals(selectBox)){
					sql = dbo.selectLikeWhere("indexdetail", map);
				} else {
					sql = dbo.selectWhere("indexdetail", map);
				}
			} else {
				sql = dbo.tableforAll("indexdetail");
			}
			if ("1".equals(operation)) {
				if (dbo.updateById("indexdetail", map, idid)) {
					ca.setMessage("指标明细修改正确！！");
				} else {
					ca.setMessage("指标明细修改失败！！");
				}
			} else if ("2".equals(operation)) {
				map.put("id", String.valueOf(idid));
				int checkCount = dbo.countSql("indexdetail", map);
				if (checkCount == 0) {
					ca.setMessage("请在修改指标明细后点击修改按钮，否则冻结/解冻操作不成功！！");
				} else {
					map.remove("category_detail");
					map.remove("type_detail");
					map.remove("indexID");
					map.remove("indexName");
					map.remove("indexDetail");
					map.remove("indexFormula");
					map.remove("computingCycle");
					map.remove("cycleUnit");
					map.remove("department");
					map.remove("remark");
					map.put("abateTime", "NULL");
					int count = dbo.countSql("indexdetail", map);
					if (count != 0) {
						map.remove("abateTime");
						map.remove("id");
						map.put("abateTime", new Common().sysDate("yyyy-MM-dd"));
						if (dbo.updateById("indexdetail", map, idid)) {
							ca.setMessage("指标明细冻结成功！！");
						} else {
							ca.setMessage("指标明细冻结失败！！");
						}
					} else {
						map.remove("id");
						if (dbo.updateById("indexdetail", map, idid)) {
							ca.setMessage("指标明细解冻成功！！");
						} else {
							ca.setMessage("指标明细解冻失败！！");
						}
					}
				}
			}
			objListTemp = dbo.excuteSQL(sql, IndexDetail.class);
			for(Object obj0 : objListTemp){
				idListTemp.add((IndexDetail) obj0);
			}
			ca.setIdCount(idListTemp.size());
			if (idListTemp.size()%20 == 0){
				ca.setIdCountPage(idListTemp.size()/20);
			} else {
				ca.setIdCountPage(idListTemp.size()/20 + 1);
			}
			sql = sql +" limit " + (ca.getIdCurPage() - 1)*20 + ", " + ca.getIdCurPage()*20;
			objList = dbo.excuteSQL(sql, IndexDetail.class);
			for(Object obj : objList){
				idList.add((IndexDetail) obj);
			}
			ca.setIdList(idList);
		}
		return ca;
	}
}
