package com.yx.DBConnect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DBOpration {

	public boolean excuteSQL(String sql) {

		Connection conn = DBConnect.getConn();
		Statement stmt = DBConnect.getStatement(conn);
		boolean flag = DBConnect.getResultSetInsert(stmt, sql);
		try {
			// 关闭连接
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}

	public List<Object> excuteSQL(String sql, Class<?> clazz) {

		Field[] fs = clazz.getDeclaredFields();
		Method[] me = clazz.getMethods();
		List<Object> daoList = new ArrayList<Object>();
		Connection conn = DBConnect.getConn();
		Statement stmt = DBConnect.getStatement(conn);
		ResultSet rs = DBConnect.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				try {
					Object cla = clazz.newInstance();
					for (Field field : fs) {
						field.setAccessible(true);
						String name = field.getName();
						String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
						Method method = null;
						for (Method m : me) {
							if (("set" + methodName).equals(m.getName())) {
								method = m;
								break;
							}
						}
						Object value = null;
						if ("String".equals(field.getType().getSimpleName())) {
							value = rs.getString(name);
						} else if ("int".equals(field.getType().getName())) {
							value = rs.getInt(name);
						} else if ("Date".equals(field.getType().getSimpleName())) {
							value = rs.getDate(name);
						}
						method.invoke(cla, value);
					}
					daoList.add(cla);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return daoList;
	}

	public int excuteID(String sql) {

		Connection conn = DBConnect.getConn();
		Statement stmt = DBConnect.getStatement(conn);
		ResultSet rs = DBConnect.getResultSet(stmt, sql);
		int index = 0;
		try {
			rs.last();
			index = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return index + 1;
	}

	public String tableforAll(String tableName) {
		String sql = "select * from " + tableName;
		return sql;
	}

	public String objInsertSql(Object obj) {
		@SuppressWarnings("rawtypes")
		Class clazz = obj.getClass();
		String objName = clazz.getSimpleName().toLowerCase();
		String sql = "insert into " + objName + " (";
		String sqlValue = ") values (";
		int id = this.excuteID(tableforAll(objName));
		int index = 1;
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getMethods();
		for (Field field : fields) {
			String name = field.getName();
			sql = sql + name;
			if ("id".equals(name)) {
				sqlValue = sqlValue + id;
			} else {
				String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
				Method method = null;
				for (Method m : methods) {
					if (("get" + methodName).equals(m.getName())) {
						method = m;
						break;
					}
				}
				Object value = null;
				try {
					Object mValue = method.invoke(obj);
					if ("String".equals(field.getType().getSimpleName())) {
						value = (mValue == null)?"":mValue;
						sqlValue = sqlValue + "'" +value +"'";
					} else if ("int".equals(field.getType().getName())) {
						value = (mValue == null)?0:mValue;
						sqlValue = sqlValue + value;
					} else if ("Date".equals(field.getType().getSimpleName())) {
						sqlValue = sqlValue + value;
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			if (index != fields.length){
				sql = sql + ", ";
				sqlValue = sqlValue +", ";
				index++;
			}
		}
		return sql + sqlValue + ")";
	}
	
	public String selectWhere(String tableName, Map<String, String> map) {

		String sql = "select a.* from " + tableName + " a";
		if (map.isEmpty()) {
			return sql;
		}
		sql = sql + " where ";
		Iterator<String> it = map.keySet().iterator();
		boolean flag = true;
		while (it.hasNext()) {
			String key = it.next();
			if (flag) {
				sql = sql + " a. " + key + " = '" + map.get(key) + "'";
				flag = false;
			} else {
				sql = sql + " and "+ " a. " + key + " = '" + map.get(key) + "'";
			}
		}
		
		return sql;
	}
	
	public String selectLikeWhere(String tableName, Map<String, String> map) {

		String sql = "select a.* from " + tableName + " a";
		if (map.isEmpty()) {
			return sql;
		}
		sql = sql + " where ";
		Iterator<String> it = map.keySet().iterator();
		boolean flag = true;
		while (it.hasNext()) {
			String key = it.next();
			if (flag) {
				sql = sql + " a. " + key + " like '%" + map.get(key) + "%'";
				flag = false;
			} else {
				sql = sql + " and "+ " a. " + key + " like '%" + map.get(key) + "%'";
			}
		}
		
		return sql;
	}
	
	public int countSql(String tableName, Map<String, String> map){
		String sql = "select count(*) count from " + tableName;
		if (!map.isEmpty()) {
			sql = sql + " where ";
			Iterator<String> it = map.keySet().iterator();
			boolean flag = true;
			while (it.hasNext()) {
				String key = it.next();
				if (flag) {
					if ("NULL".equals(map.get(key))){
						sql = sql + key + " is null ";
					} else if ("NOTNULL".equals(map.get(key))){
						sql = sql + key + " is not null ";
					} else {
						sql = sql + key + " = '" + map.get(key) + "'";
					}
					flag = false;
				} else {
					if ("NULL".equals(map.get(key))){
						sql = sql + " and "+ key + " is null ";
					} else if ("NOTNULL".equals(map.get(key))){
						sql = sql + " and "+ key + " is not null ";
					} else {
						sql = sql + " and "+ key + " = '" + map.get(key) + "'";
					}
				}
			}
		}
		
		Connection conn = DBConnect.getConn();
		Statement stmt = DBConnect.getStatement(conn);
		ResultSet rs = DBConnect.getResultSet(stmt, sql);
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return count;
		} finally{
			try {
				// 关闭连接
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return count;
			}
		}
		return count;
	}
	
	public String nameToCode(String columnName, String name, String tableName){
		Map<String, String> argMap = new HashMap<String, String>();
		argMap.put(columnName, name);
		String sql = this.selectWhere(tableName, argMap);
		Connection conn = DBConnect.getConn();
		Statement stmt = DBConnect.getStatement(conn);
		ResultSet rs = DBConnect.getResultSet(stmt, sql);
		String id = "";
		try {
			while(rs.next()){
				id = String.valueOf(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		} finally{
			try {
				// 关闭连接
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "";
			}
		}
		return id;
	}
	
	public boolean updateById(String tableName, Map<String, String> map, int id) {

		String sql =  "update " + tableName + " set ";
		if (map.isEmpty()) {
			return false;
		}
		Iterator<String> it = map.keySet().iterator();
		boolean flag = true;
		while (it.hasNext()) {
			String key = it.next();
			if (flag) {
				if("NULL".equals(map.get(key))) {
					sql = sql + key + " = " + map.get(key);
				} else {
					sql = sql + key + " = '" + map.get(key) + "'";
				}
				
				flag = false;
			} else {
				if("NULL".equals(map.get(key))) {
					sql = sql + ", "+ key + " = " + map.get(key);
				} else {
					sql = sql + ", "+ key + " = '" + map.get(key) + "'";
				}
			}
		}
		sql = sql +" where id = " + id;
		return this.excuteSQL(sql);
	}
	
	public String javaBeanSQL(Object obj, String mark) {

		Class<?> clazz = obj.getClass();
		Field[] fs = clazz.getDeclaredFields();
		Method[] me = clazz.getMethods();
		String tableName = clazz.getSimpleName().toLowerCase();
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : fs) {
			field.setAccessible(true);
			String name = field.getName();
			String type = field.getType().getSimpleName();
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method method = null;
			for (Method m : me) {
				if (("get" + methodName).equals(m.getName())) {
					method = m;
					break;
				}
			}
			try {
				if ("int".equals(type) && (int) method.invoke(obj) != 0) {
					map.put(name, String.valueOf(method.invoke(obj)));
				} else if ("String".equals(type) && !method.invoke(obj).toString().isEmpty()){
					map.put(name, (String) method.invoke(obj));
				} else {
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("1".equals(mark)){
			return this.selectLikeWhere(tableName, map);
		} else {
			return this.selectWhere(tableName, map);
		}
	}
	
	public Object InitBean(Class<?> clazz) {

		Field[] fs = clazz.getDeclaredFields();
		Method[] me = clazz.getMethods();
		Object cla = null;
		try {
			cla = clazz.newInstance();
			for (Field field : fs) {
				field.setAccessible(true);
				String name = field.getName();
				String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
				Method method = null;
				for (Method m : me) {
					if (("set" + methodName).equals(m.getName())) {
						method = m;
						break;
					}
				}
				if ("String".equals(field.getType().getSimpleName())) {
					method.invoke(cla, "");
				} else if ("int".equals(field.getType().getName())) {
					method.invoke(cla, 0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return cla;
	}
}