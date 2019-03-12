package com.event.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import com.event.model.Event;

public class EventDao {
	public EventDao() {
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
	
	public void add(Event event) {
		String sql = "insert into `oa-event` values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, event.occTime);
			ps.setString(2, event.locale);
			ps.setString(3, event.department);
			ps.setString(4, event.level);
			ps.setString(5, event.discPerson);
			ps.setString(6, event.discTime);
			ps.setString(7, event.handlePerson);
			ps.setString(8, event.eventDesc);
			ps.setString(9, event.effBus);
			ps.setString(10, event.incidence);
			ps.setString(11, event.effTime);
			ps.setString(12, event.eventHandle);
			ps.setString(13, event.eventReason);
			ps.setString(14, event.eventResult);
			
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Event> list(){
		return list(0, Short.MAX_VALUE);
	}
	
	public List<Event> list(int start, int count){
		List<Event> events = new ArrayList<Event>();
		String sql = "select * from `oa-event` order by id desc limit ?,?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Event event = new Event();
				int id = rs.getInt(1);
				String occTime = rs.getString(2);
				String locale = rs.getString(3);
				String department = rs.getString(4);
				String level = rs.getString(5);
				String discPerson = rs.getString(6);
				String discTime = rs.getString(7);
				String handlePerson = rs.getString(8);
				String effBus = rs.getString(10);
				String incidence = rs.getString(11);
				String effTime = rs.getString(12);
				event.id = id;
				event.occTime = occTime.substring(0, occTime.length()-2);
				event.locale = locale;
				event.department = department;
				event.level = level;
				event.discPerson = discPerson;
				event.discTime = discTime;
				event.handlePerson = handlePerson;
				event.effBus = effBus;
				event.incidence = incidence;
				event.effTime = effTime;
				events.add(event);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public int total() {
		String sql = "SELECT COUNT(*) FROM `oa-event`";
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
	
	public void detail(Event detailEvent) {
		String sql = "select * from `oa-event` where id=?;";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, detailEvent.id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String occTime = rs.getString(2);
				String locale = rs.getString(3);
				String department = rs.getString(4);
				String level = rs.getString(5);
				String discPerson = rs.getString(6);
				String discTime = rs.getString(7);
				String handlePerson = rs.getString(8);
				String eventDesc = rs.getString(9);
				String effBus = rs.getString(10);
				String incidence = rs.getString(11);
				String effTime = rs.getString(12);
				String eventHandle = rs.getString(13);
				String eventReason = rs.getString(14);
				String eventResult = rs.getString(15);
				detailEvent.occTime = occTime.substring(0, occTime.length()-2);
				detailEvent.locale = locale;
				detailEvent.department = department;
				detailEvent.level = level;
				detailEvent.discPerson = discPerson;
				detailEvent.discTime = discTime.substring(0, discTime.length()-2);
				detailEvent.handlePerson = handlePerson;
				detailEvent.eventDesc = eventDesc;
				detailEvent.effBus = effBus;
				detailEvent.incidence = incidence;
				detailEvent.effTime = effTime;
				detailEvent.eventHandle = eventHandle;
				detailEvent.eventReason = eventReason;
				detailEvent.eventResult = eventResult;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Event event) {
		String sql = "delete from `oa-event` where id=?;";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, event.id);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Event> search(String startTime, String endTime) {
		List<Event> events = new ArrayList<Event>();
		String sql = "select * from `oa-event` where 'occ-time' between ? and ?";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, startTime);
			ps.setString(2, endTime);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Event event = new Event();
				String occTime = rs.getString(2);
				String locale = rs.getString(3);
				String department = rs.getString(4);
				String level = rs.getString(5);
				String discPerson = rs.getString(6);
				String discTime = rs.getString(7);
				String handlePerson = rs.getString(8);
				String eventDesc = rs.getString(9);
				String effBus = rs.getString(10);
				String incidence = rs.getString(11);
				String effTime = rs.getString(12);
				String eventHandle = rs.getString(13);
				String eventReason = rs.getString(14);
				String eventResult = rs.getString(15);
				event.occTime = occTime;
				event.locale = locale;
				event.department = department;
				event.level = level;
				event.discPerson = discPerson;
				event.discTime = discTime;
				event.handlePerson = handlePerson;
				event.eventDesc = eventDesc;
				event.effBus = effBus;
				event.incidence = incidence;
				event.effTime = effTime;
				event.eventHandle = eventHandle;
				event.eventReason = eventReason;
				event.eventResult = eventResult;
				events.add(event);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public int searchTotal() {
		String sql = "SELECT COUNT(*) FROM (select * from `oa-event` where 'occ-time' between ? and ?) as total";
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