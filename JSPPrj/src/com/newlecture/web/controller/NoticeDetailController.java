package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

// 데이터는 모두 여기 있음. detail.jsp는 껍데기다. 여기서 실행해야함.

@WebServlet("/notice/detail")

public class NoticeDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));	 	
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "newlec");
			PreparedStatement st = con.prepareStatement(sql); // sql문에 ?를 써서 값을 채우려면 그냥 Statement(그냥 실행도구로서의 의미)가 아니라 PreparedStatement를 써야하고, 이 때 sql문을 받아서 미리 준비해놓고 실행할 때는 미리 준비한 쿼리문을 실행함.
			st.setInt(1, id); // 1번째 ?에 값을 넣을 변수 
			ResultSet rs = st.executeQuery(); // PreparedStatement를 사용하면 sql문을 받지 않고 그냥 실행.
			rs.next(); // 다음 레코드 가져오기.
			
			// MVC 중 Model 준비하기 
			// close를 같이 위로 가지고 왔기 때문에 닫기 전에 변수에 먼저 옮겨놓고 사용.
			
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
			request.setAttribute("n", notice);
			
			/*
			request.setAttribute("title", title);
			request.setAttribute("writerId", writerId);
			request.setAttribute("regdate", regdate);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			*/			
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
		// redirect : servlet을 호출했는데 아예 다른 페이지로 보내버리는 것.(예_ 이 페이지는 로그인이 필요한 페이지인데 로그인을 안하고 이 페이지를 호출하면 로그인 페이지로 보내버리는 것)
		
		//forward하기 전에 여기서 만든 모델들을 request에서 담아서 detail.jsp에서 사용할 수 있게 함.		
		// forward : 이 페이지에서 하던 작업을 이어받아서 처리하고 싶을 때 처리하는 전이방식
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response); // dispatcher를 이용해서 detail.jsp를 호출하면서 이 페이지에서 사용하고 있는 저장소(request)와 출력도구(response)를 같이 공유
		
		
		
		
	}
	
}
