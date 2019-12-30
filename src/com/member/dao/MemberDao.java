package com.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.event.model.Event;
import com.member.model.Member;

public class MemberDao {
	public MemberDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("没加载");
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useSSL=false&characterEncoding=UTF-8", "root", "123456");
	}
	
	//查看全部人员信息
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
				String department = rs.getString(2);
				String subDepartment = rs.getString(3);
				String job = rs.getString(4);
				String name = rs.getString(5);
				String phoneNumber = rs.getString(6);
				String function = rs.getString(7);
				member.id = id;
				member.department = department;
				member.subDepartment = subDepartment;
				member.job = job;
				member.name = name;
				member.phoneNumber = phoneNumber;
				member.function = function;
				members.add(member);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	//人员数量
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
	
	//人员信息搜索功能
	
	public List<Member> search(String keyWord) {
		List<Member> members = new ArrayList<Member>();
		if(keyWord.equals("null")) {
			String sql = "select * from `employeeinformationsheet`";
			try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Member member = new Member();
					int id = rs.getInt(1);
					String department = rs.getString(2);
					String subDepartment = rs.getString(3);
					String job = rs.getString(4);
					String name = rs.getString(5);
					String phoneNumber = rs.getString(6);
					String function = rs.getString(7);
					member.id = id;
					member.department = department;
					member.subDepartment = subDepartment;
					member.department = department;
					member.job = job;
					member.name = name;
					member.phoneNumber = phoneNumber;
					member.function = function;
					members.add(member);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			String sql = "SELECT * FROM `employeeinformationsheet` WHERE CONCAT(`department`,`subDepartment`,`job`,`name`,`phoneNumber`,`function`) LIKE ?";
			try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				keyWord = "%"+keyWord+"%";
				ps.setString(1, keyWord);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Member member = new Member();
					int id = rs.getInt(1);
					String department = rs.getString(2);
					String subDepartment = rs.getString(3);
					String job = rs.getString(4);
					String name = rs.getString(5);
					String phoneNumber = rs.getString(6);
					String function = rs.getString(7);
					member.id = id;
					member.department = department;
					member.subDepartment = subDepartment;
					member.department = department;
					member.job = job;
					member.name = name;
					member.phoneNumber = phoneNumber;
					member.function = function;
					members.add(member);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return members;
	}
	
	//人员信息搜索功能，查询结果的数量
		public int searchTotal(String keyWord) {
			int totalCount = 0;
			if(keyWord.equals("null")) {
				String sql = "SELECT COUNT(*) FROM (select * from `employeeinformationsheet`) as total";
				try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						totalCount = rs.getInt(1);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}else {
				String sql = "SELECT COUNT(*) FROM (SELECT * FROM `employeeinformationsheet` WHERE CONCAT(`department`,`subDepartment`,`job`,`name`,`phoneNumber`,`function`) LIKE ?) as total";
				try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
					keyWord = "%"+keyWord+"%";
					ps.setString(1, keyWord);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						totalCount = rs.getInt(1);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return totalCount;
		}
}
