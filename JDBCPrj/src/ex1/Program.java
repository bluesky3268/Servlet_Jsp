package ex1;

import java.sql.*;


public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// 데이터베이스 연결과 실행
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "SELECT * FROM NOTICE WHERE HIT > 10";
		
		Class.forName("oracle.jdbc.driver.OracleDriver"); //class.forName메소드를 통해서 "oracle.jdbc.driver.OracleDriver(얘가 DriverManager)"를 객체화(->로드)
		Connection con = DriverManager.getConnection(url, "newlec", "newlec"); // 연결객체를 얻음
		Statement st = con.createStatement(); // 연결 후 실행도구 생성
		ResultSet rs = st.executeQuery(sql); // 쿼리 실행
		
		// JDBC를 통해 데이터 값을 가져오기(Select)
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			System.out.printf("id : %d, title : %s, writeId : %s, regDate : %s, content : %s, hit : %d%n",
					id, title, writerId, regDate, content, hit);			
		}
		
		rs.close();
		st.close();
		con.close();
		
	}

}
