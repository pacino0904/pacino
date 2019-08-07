package com.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.member.model.Member;

public class MemberDao {
	public MemberDao() {
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
	public List<Member> list(){
		return list(0, Short.MAX_VALUE);
	}
	public List<Member> list(int start, int count){
		List<Member> members = new ArrayList<Member>();
		
		String sql = "select * from `employeeinformationsheet` order by id asc limit ?,?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				int id = rs.getInt(1);
				String group = rs.getString(2);
				String jobPosition = rs.getString(3);
				String name = rs.getString(4);
				String phoneNumber = rs.getString(5);
				String position = rs.getString(6);
				String secondFunction = rs.getString(7);
				member.id = id;
				member.group = group;
				member.jobPosition = jobPosition;
				member.name = name;
				member.phoneNumber = phoneNumber;
				member.position = position;
				member.secondFunction = secondFunction;
				members.add(member);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	public int total() {
		String sql = "SELECT COUNT(*) FROM `employeeinformationsheet`";
		int totalCount = 0;
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				totalCount = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}
}
