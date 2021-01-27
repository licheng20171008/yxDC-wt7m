package com.yx.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    private static final String url = "jdbc:mysql://localhost:3306/yxdc?user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";
//    private static final String url = "jdbc:mysql://88.88.87.59:3306/indexlibrary?user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=true";
    private static final String name = "com.mysql.jdbc.Driver";
    private static  Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static Connection getConn(){
    	try {
			Class.forName(name);
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
    }

    public static Statement getStatement(Connection conn){
    	try {
			if(conn != null) {
				stmt = conn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
    }
    
    public static ResultSet getResultSet(Statement stmt, String sql){
    	try {
			if(stmt != null) {
				rs = stmt.executeQuery(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
    }
    
    public static boolean getResultSetInsert(Statement stmt, String sql){
    	try {
			if(stmt != null) {
				stmt.execute(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }

    public static void close(Connection conn) {
    	try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void close(Statement stmt) {
    	try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void close(ResultSet rs) {
    	try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
