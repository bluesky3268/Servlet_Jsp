package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService  {
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String uid = "newlec";
	private String pwd = "newlec";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	// CRUD 서비스 클래스 만들기
	public List<Notice> getList() throws ClassNotFoundException, SQLException{

		// 데이터베이스 연결과 실행
			String sql = "SELECT * FROM NOTICE";
			
			Class.forName(driver); 
			Connection con = DriverManager.getConnection(url, uid, pwd); 
			Statement st = con.createStatement(); 
			ResultSet rs = st.executeQuery(sql); 
			
			List<Notice> list = new ArrayList<Notice>();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				
				Notice notice = new Notice(
									id, 
									title, 
									writerId,
									regDate,
									content,
									hit,
									files
									);
				
				list.add(notice);
				
			rs.close();
			st.close();
			con.close();
		
		}
		return list;
	}		

	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		// JDBC를 통해 데이터 Insert(삽입)하기 
		String sql = "INSERT INTO notice ( "
				+ "    title,"
				+ "    writer_id,"
				+ "    content, "
				+ "    files   "
				+ ") VALUES (?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement(); 
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();

		st.close();
		con.close();
		
		return result;
	}
	public int update(Notice notice) throws ClassNotFoundException, SQLException{
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		// JDBC를 통해 데이터 데이터 수정 구현하기
		String sql ="UPDATE NOTICE " + 
					"SET" + 
				    "	TITLE=?," + 
				    "	CONTENT=?," + 
				    "	FILES=?" +
				    " WHERE ID=?";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();

		st.close();
		con.close();
		
		return result;
	}
	public int delete(int id) throws ClassNotFoundException, SQLException {
		
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		String sql ="DELETE NOTICE WHERE ID=?";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);	
		
		st.setInt(1, id);
		
		int result = st.executeUpdate();
		System.out.println(result);

		st.close();
		con.close();
		return result;
	}
}
