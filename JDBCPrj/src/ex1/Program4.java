package ex1;

import java.sql.*;


public class Program4 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		int id = 5;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		// JDBC를 통해 데이터 삭제하기
		String sql ="DELETE NOTICE WHERE ID=?";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "newlec");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);	
		
		st.setInt(1, id);
		
		int result = st.executeUpdate();
		// insert, delete, update등 데이터를 조작(결과집합이 없음.)을 할 때는 executeQuery()가 아니라 executeUpdate()를 사용 
		System.out.println(result);

		st.close();
		con.close();
		
	}

}
