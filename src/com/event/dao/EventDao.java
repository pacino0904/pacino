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
			System.out.println("没加载");
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useSSL=false&characterEncoding=UTF-8", "root", "123456");
	}
	//添加事件单
	public void add(Event event) {
		String sql = "insert into `oa-event` values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
			ps.setString(15, event.tjsj);
			ps.setString(16, event.eventNumber);
			
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//查看全部事件单
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
	
	//查看全部事件单的数量
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
	
	//根据id查看事件单详情
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
				String eventNumber = rs.getString(17);
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
				detailEvent.eventNumber = eventNumber;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//删除事件单
	public void delete(Event event) {
		String sql = "delete from `oa-event` where id=?;";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, event.id);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//事件单搜索功能
	public List<Event> search(String startTime, String endTime, String keyWord) {
		List<Event> events = new ArrayList<Event>();
		if(startTime.equals("null")) {
			startTime = "2000-01-01";
		}
		if(endTime.equals("null")) {
			endTime = "2050-01-01";
		}
		if(keyWord.equals("null")) {
			String sql = "select * from `oa-event` where `occ-time` between ? and ?";
			try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				ps.setString(1, startTime);
				ps.setString(2, endTime);
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
		}else {
			String sql = "SELECT * FROM `oa-event` WHERE CONCAT(`locale`,`department`,`level`,`disc-person`,`handle-person`,`event-desc`,`event-reason`) LIKE ? AND `occ-time` between ? and ?";
			try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				keyWord = "%"+keyWord+"%";
				ps.setString(1, keyWord);
				ps.setString(2, startTime);
				ps.setString(3, endTime);
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
		}
		return events;
	}
	
	//事件单，查询结果的数量
	public int searchTotal(String startTime, String endTime, String keyWord) {
		int totalCount = 0;
		if(startTime.equals("null")) {
			startTime = "2000-01-01";
		}
		if(endTime.equals("null")) {
			endTime = "2050-01-01";
		}
		if(keyWord.equals("null")) {
			String sql = "SELECT COUNT(*) FROM (select * from `oa-event` where `occ-time` between ? and ?) as total";
			try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				ps.setString(1, startTime);
				ps.setString(2, endTime);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					totalCount = rs.getInt(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			String sql = "SELECT COUNT(*) FROM (SELECT * FROM `oa-event` WHERE CONCAT(`locale`,`department`,`level`,`disc-person`,`handle-person`,`event-desc`,`event-reason`) LIKE ? AND `occ-time` between ? and ?) as total";
			try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				keyWord = "%"+keyWord+"%";
				ps.setString(1, keyWord);
				ps.setString(2, startTime);
				ps.setString(3, endTime);
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
	//事件单编号，查询当天提交的事件单数量
	public int sjdbh(String startTime, String endTime) {
		String sql = "SELECT COUNT(*) FROM (select * from `oa-event` where `tjsj` between ? and ?) as total";
		int todayCount = 0;
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, startTime);
			ps.setString(2, endTime);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				todayCount = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return todayCount;
	}
	
}