package ex1;

import java.sql.*;


public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String title = "TEST2";
		String writerId = "ben";
		String content = "haahahah";
		String files = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		// JDBC를 통해 데이터 Insert(삽입)하기 
		String sql = "INSERT INTO notice ( "
				+ "    title,"
				+ "    writer_id,"
				+ "    content, "
				+ "    files   "
				+ ") VALUES (?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "newlec");
//		Statement st = con.createStatement(); 
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();
		// insert, delete, update등 데이터를 조작(결과집합이 없음.)을 할 때는 executeQuery()가 아니라 executeUpdate()를 사용 
		System.out.println(result);

		st.close();
		con.close();
		
	}

}
