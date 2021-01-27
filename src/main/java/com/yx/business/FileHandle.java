package com.yx.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yx.DBConnect.DBConnect;
import com.yx.DBConnect.DBOpration;
import com.yx.dao.IndexDetail;

public class FileHandle {

	public String checkFile(List<IndexDetail> idList) {
		String message = "";

		// 数据检证
		for (IndexDetail id : idList) {

			// 行数
			int index = id.getId();

			// 一级分类检证
			String category_detail = id.getCategory_detail();
			message = checkData(message, category_detail, index, "categoryName", "一级分类", "category");

			// 二级分类检证
			String type_detail = id.getType_detail();
			message = checkData(message, type_detail, index, "typeName", "二级分类", "type");

			// 一级分类与二级分类关联关系检证
			if (!category_detail.isEmpty() && !type_detail.isEmpty()) {
				String sql = "select * from type a left join category b on a.category_type = b.id where a.typeName = '"
						+ type_detail + "' and b.categoryName = '" + category_detail + "'";
				if (new DBOpration().excuteID(sql) == 1) {
					message = message + "一级分类(" + category_detail + ")与二级分类(" + type_detail + ")无关联关系，请确认。";
				}
			}

			// 部门检证
			String[] department = id.getDepartment().split(",");
			for (String dep : department) {
				message = checkData(message, dep, index, "name", "部门", "department");
			}
		}
		return message;
	}

	private String checkData(String message, String arg, int index, String key, String type, String tableName) {
		if (!arg.isEmpty()) {
			Map<String, String> argMap = new HashMap<String, String>();
			argMap.put(key, arg);
			DBOpration dbo = new DBOpration();
			int count = dbo.countSql(tableName, argMap);
			if (count == 0) {
				message = message + "第" + index + "行的" + type + "(" + arg + ")不存在，请检查。";
			}
		} else {
			message = message + "第" + index + "行的" + type + "为空，请检查。";
		}
		return message;
	}

	public String excute(List<IndexDetail> idList) {

		DBOpration dbo = new DBOpration();
		Connection conn = DBConnect.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into indexdetail (id, category_detail, type_detail, indexID, indexName, indexDetail, indexFormula, computingCycle, cycleUnit, department, remark) values (?,?,?,?,?,?,?,?,?,?,?)";
		int index = dbo.excuteID(dbo.tableforAll("indexdetail"));
		try {
			pstmt = conn.prepareStatement(sql);
			for (IndexDetail id : idList) {
				pstmt.setInt(1, index);
				pstmt.setString(2, dbo.nameToCode("categoryName", id.getCategory_detail(), "category"));
				pstmt.setString(3, dbo.nameToCode("typeName", id.getType_detail(), "type"));
				pstmt.setString(4, id.getIndexID());
				pstmt.setString(5, id.getIndexName());
				pstmt.setString(6, id.getIndexDetail());
				pstmt.setString(7, id.getIndexFormula());
				pstmt.setString(8, id.getComputingCycle());
				pstmt.setString(9, id.getCycleUnit());
				pstmt.setString(10, id.getDepartment());
				pstmt.setString(11, id.getRemark());
				pstmt.addBatch();
				index++;
			}
			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			return "数据插入失败！！";
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "数据插入失败！！";
			}
		}
		return "数据插入成功！！";
	}
}