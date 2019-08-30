package com.log.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.log.model.Log;

public class LogDao {
	public LogDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("√ªº”‘ÿ");
		}
	}
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useSSL=false&characterEncoding=UTF-8", "root", "123456");
	}
	public void add(Log log) {
		String sql = "insert into `oa-log` values(null,?,?,?,?,?,?,?,?,?,?);";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, log.user);
			ps.setString(2, log.time);
			ps.setString(3, log.ip);
			ps.setString(4, log.type);
			ps.setString(5, log.content1);
			ps.setString(6, log.content2);
			ps.setString(7, log.content3);
			ps.setString(8, log.content4);
			ps.setString(9, log.content5);
			ps.setString(10, log.content6);
			
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
