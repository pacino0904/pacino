package com.change.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.change.model.Change;

public class ChangeDao {
	public ChangeDao() {
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
	
	public void add(Change change){
		String sql = "insert into `oa-change` values(null,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, change.bglx);
			ps.setString(2, change.bgxm);
			ps.setString(3, change.bgdj);
			ps.setString(4, change.jhssr);
			ps.setString(5, change.jhkssj);
			ps.setString(6, change.jhjssj);
			ps.setString(7, change.bgsqr);
			ps.setString(8, change.sqsj);
			ps.setString(9, change.bgyy);
			ps.setString(10, change.bgfa);
			ps.setString(11, change.bghtfa);
			ps.setString(12, change.tjsj);
			ps.setString(13, change.eventNumber);
			
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchNumber() {
		
	}
	

}
