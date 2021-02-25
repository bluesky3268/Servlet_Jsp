package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	// 목록을 얻기 위한 메소드
	public List<Notice> getNoticeList() {

		return getNoticeList("title", "", 1);
	}

	public List<Notice> getNoticeList(int page) {
		
		return getNoticeList("title", "", page);
	}

	public List<Notice> getNoticeList(String field, String query, int page) {
		 // field = title or wirter_id * field는 값이 아니라서 ?를 사용해서 값을 꽂아 넣을 수 없다.
		// query = 문자열 ('%A%')
		List<Notice> list = new ArrayList<>();
		String sql = "select * from("
				+" 	select rownum num, N.* from"
				+ " 	(select * from notice where "+field+" like ? order by regdate desc) N"
				+ ")"
			+ " where rownum between ? and ?"; //rownum id가 아닌 결과물에 대한 번호
		
		// 1, 11, 21, 31, ... -> an = 1 +(page-1)*10
		// 10, 20, 30, 40 .. -> page*10
		
		
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(url, "newlec", "newlec");
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, "%"+query+"%");
				st.setInt(2, 1+(page-1)*10);
				st.setInt(3, page*10);
				ResultSet rs = st.executeQuery(); 
				
				while(rs.next()){
					int id = rs.getInt("ID");
					String title = rs.getString("TITLE");
					String writerId = rs.getString("WRITER_ID");
					Date regdate = rs.getDate("REGDATE"); 
					String hit = rs.getString("HIT"); 
					String files = rs.getString("FILES"); 
					String content = rs.getString("CONTENT"); 
					
					
					Notice notice = new Notice(
							id,
							title,
							writerId,
							regdate,
							hit,
							files,
							content
							);
					list.add(notice);
				} // End while	
	
				rs.close();
				st.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return list;
	}
	// 카운트를 얻기 위한 메소드
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	public int getNoticeCount(String field, String query) {
		int count = 0;
		String sql = "select count(id) count from("
				+" 	select rownum num, N.* from"
				+ " 	(select * from notice where "+field+" like ? order by regdate desc) N"
				+ ")";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			
			ResultSet rs = st.executeQuery(); 
			
			count = rs.getInt("count");
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return count;
	}
	public Notice getNotice(int id) {
		Notice notice = null;
		
		String sql = "select * from notice where id=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); 
			
			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE"); 
				String hit = rs.getString("HIT"); 
				String files = rs.getString("FILES"); 
				String content = rs.getString("CONTENT"); 		
				
				notice = new Notice(
						nid,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
						);			
			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return notice;
		
	}
	public Notice getNexttNotice(int id) {
		Notice notice = null;

		String sql = "select * from notice "
					+ "where id =("
					+ "select id from notice where regdate > (select regdate from notice where id=?) "
					+ "and rownum = 1)";
	
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); 
			
			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE"); 
				String hit = rs.getString("HIT"); 
				String files = rs.getString("FILES"); 
				String content = rs.getString("CONTENT"); 		
				
				notice = new Notice(
						nid,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
						);			
			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
		return notice;
	}
	public Notice gePrevtNotice(int id) {
		Notice notice = null;

		String sql = "select id from ("
					+ "select * from notice order by regdate desc) "
					+ "where regdate < (select regdate from notice where id=?) "
					+ "and rownum = 1)";
		
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); 
			
			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE"); 
				String hit = rs.getString("HIT"); 
				String files = rs.getString("FILES"); 
				String content = rs.getString("CONTENT"); 		
				
				notice = new Notice(
						nid,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
						);			
			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return notice;
	}
}
