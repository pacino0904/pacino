package com.change.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.change.model.Change;

public class ChangeDao {
	public ChangeDao() {
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
	
	//变更工作单添加功能
	public void add(Change change){
		String sql = "insert into `oa-change` values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
			ps.setString(13, change.changeNumber);
			ps.setString(14, change.status);
			
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查看全部变更工作单
		public List<Change> list(){
			return list(0, Short.MAX_VALUE);
		}
		
		public List<Change> list(int start, int count){
			List<Change> changes = new ArrayList<Change>();
			String sql = "select * from `oa-change` where status='true'";
			try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){		
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Change change = new Change();
					int id = rs.getInt(1);
					String bglx = rs.getString(2);
					String bgxm = rs.getString(3);
					String bgdj = rs.getString(4);
					String jhssr = rs.getString(5);
					String jhkssj = rs.getString(6);
					String jhjssj = rs.getString(7);
					String bgsqr = rs.getString(8);
					String sqsj = rs.getString(9);
					String bgyy = rs.getString(10);
					String bgfa = rs.getString(11);
					String bghtfa = rs.getString(12);
					String tjsj = rs.getString(13);
					change.id = id;
					change.bglx = bglx;
					change.bgxm = bgxm;
					change.bgdj = bgdj;
					change.jhssr = jhssr;
					change.jhkssj = jhkssj.substring(0, jhkssj.length()-11);
					change.jhjssj = jhjssj.substring(0, jhjssj.length()-11);
					change.bgsqr = bgsqr;
					change.sqsj = sqsj.substring(0, sqsj.length()-11);
					change.bgyy = bgyy;
					change.bgfa = bgfa;
					change.bghtfa = bghtfa;
					change.tjsj = tjsj;
					changes.add(change);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return changes;
		}
	
	//查看全部变更工作单的数量
	public int total() {
		String sql = "SELECT COUNT(*) FROM `oa-change` where status='true'";
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
	//变更单编号，查询当天提交的变更单数量
		public int bgdbh(String startTime, String endTime) {
			String sql = "SELECT COUNT(*) FROM (select * from `oa-change` where `tjsj` between ? and ?) as total";
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
		
		//变更单搜索功能
		public List<Change> search(String startTime, String endTime, String keyWord) {
			List<Change> changes = new ArrayList<Change>();
			if(startTime.equals("null")) {
				startTime = "2000-01-01";
			}
			if(endTime.equals("null")) {
				endTime = "2050-01-01";
			}
			if(keyWord.equals("null")) {
				String sql = "select * from `oa-change` where `sqsj` between ? and ? AND status='true'";
				try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
					ps.setString(1, startTime + " 00:00:00");
					ps.setString(2, endTime + " 23:59:59");
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						Change change = new Change();
						int id = rs.getInt(1);
						String bglx = rs.getString(2);
						String bgxm = rs.getString(3);
						String bgdj = rs.getString(4);
						String jhssr = rs.getString(5);
						String jhkssj = rs.getString(6);
						String jhjssj = rs.getString(7);
						String bgsqr = rs.getString(8);
						String sqsj = rs.getString(9);
						String bgyy = rs.getString(10);
						String bgfa = rs.getString(11);
						String bghtfa = rs.getString(12);
						String tjsj = rs.getString(13);
						change.id = id;
						change.bglx = bglx;
						change.bgxm = bgxm;
						change.bgdj = bgdj;
						change.jhssr = jhssr;
						change.jhkssj = jhkssj.substring(0, jhkssj.length()-11);
						change.jhjssj = jhjssj.substring(0, jhjssj.length()-11);
						change.bgsqr = bgsqr;
						change.sqsj = sqsj.substring(0, sqsj.length()-11);
						change.bgyy = bgyy;
						change.bgfa = bgfa;
						change.bghtfa = bghtfa;
						change.tjsj = tjsj;
						changes.add(change);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}else {
				String sql = "SELECT * FROM `oa-change` WHERE CONCAT(`bglx`,`bgxm`,`bgdj`,`jhssr`,`bgsqr`,`bgyy`,`bgfa`,`bghtfa`) LIKE ? AND `sqsj` between ? and ? AND status='true'";
				try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
					keyWord = "%"+keyWord+"%";
					ps.setString(1, keyWord);
					ps.setString(2, startTime + " 00:00:00");
					ps.setString(3, endTime + " 23:59:59");
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						Change change = new Change();
						int id = rs.getInt(1);
						String bglx = rs.getString(2);
						String bgxm = rs.getString(3);
						String bgdj = rs.getString(4);
						String jhssr = rs.getString(5);
						String jhkssj = rs.getString(6);
						String jhjssj = rs.getString(7);
						String bgsqr = rs.getString(8);
						String sqsj = rs.getString(9);
						String bgyy = rs.getString(10);
						String bgfa = rs.getString(11);
						String bghtfa = rs.getString(12);
						String tjsj = rs.getString(13);
						change.id = id;
						change.bglx = bglx;
						change.bgxm = bgxm;
						change.bgdj = bgdj;
						change.jhssr = jhssr;
						change.jhkssj = jhkssj.substring(0, jhkssj.length()-11);
						change.jhjssj = jhjssj.substring(0, jhjssj.length()-11);
						change.bgsqr = bgsqr;
						change.sqsj = sqsj.substring(0, sqsj.length()-11);
						change.bgyy = bgyy;
						change.bgfa = bgfa;
						change.bghtfa = bghtfa;
						change.tjsj = tjsj;
						changes.add(change);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return changes;
		}
		
		//变更单搜索功能，查询结果的数量
		public int searchTotal(String startTime, String endTime, String keyWord) {
			int totalCount = 0;
			if(startTime.equals("null")) {
				startTime = "2000-01-01";
			}
			if(endTime.equals("null")) {
				endTime = "2050-01-01";
			}
			if(keyWord.equals("null")) {
				String sql = "SELECT COUNT(*) FROM (select * from `oa-change` where `sqsj` between ? and ? AND status='true') as total";
				try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
					ps.setString(1, startTime + " 00:00:00");
					ps.setString(2, endTime + " 23:59:59");
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						totalCount = rs.getInt(1);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}else {
				String sql = "SELECT COUNT(*) FROM (SELECT * FROM `oa-change` WHERE CONCAT(`bglx`,`bgxm`,`bgdj`,`jhssr`,`bgsqr`,`bgyy`,`bgfa`,`bghtfa`) LIKE ? AND `sqsj` between ? and ? AND status='true') as total";
				try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
					keyWord = "%"+keyWord+"%";
					ps.setString(1, keyWord);
					ps.setString(2, startTime + " 00:00:00");
					ps.setString(3, endTime + " 23:59:59");
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
		//变更单详情功能，根据id查看
		public void detail(Change detailChange) {
			String sql = "select * from `oa-change` where id=?;";
			try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				ps.setInt(1, detailChange.id);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String bglx = rs.getString(2);
					String bgxm = rs.getString(3);
					String bgdj = rs.getString(4);
					String jhssr = rs.getString(5);
					String jhkssj = rs.getString(6);
					String jhjssj = rs.getString(7);
					String bgsqr = rs.getString(8);
					String sqsj = rs.getString(9);
					String bgyy = rs.getString(10);
					String bgfa = rs.getString(11);
					String bghtfa = rs.getString(12);
					String changeNumber = rs.getString(14);
					detailChange.bglx = bglx;
					detailChange.bgxm = bgxm;
					detailChange.bgdj = bgdj;
					detailChange.jhssr = jhssr;
					detailChange.jhkssj = jhkssj.substring(0, jhkssj.length()-11);
					detailChange.jhjssj = jhjssj.substring(0, jhjssj.length()-11);
					detailChange.bgsqr = bgsqr;
					detailChange.sqsj = sqsj.substring(0, sqsj.length()-11);
					detailChange.bgyy = bgyy;
					detailChange.bgfa = bgfa;
					detailChange.bghtfa = bghtfa;
					detailChange.changeNumber = changeNumber;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		//事件单删除功能
		public void delete(Change change) {
			String sql = "update `oa-change` set status='delete' where id=?;";
			try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				ps.setInt(1, change.id);
				ps.execute();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

}
