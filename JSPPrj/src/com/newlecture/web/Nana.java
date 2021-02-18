package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/hello_")
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); // utf-8로 써서 보내는 거.
		response.setContentType("text/html; charset=UTF-8"); // 브라우저에게 내가 utf-8로 작성했으니 그걸로 읽어! 하는 거.
		
		PrintWriter out = response.getWriter();
		
		int cnt = 100;
		String cnt_ = request.getParameter("cnt");
		// Query String- 입력값이 없으면 오류가 발생하기 때문에 기본값을 설정해줘야 한다.
		if(cnt_ != null && !cnt_.equals("")) {
			cnt = Integer.parseInt(cnt_);
		}
		for(int i = 0; i < cnt; i++) {
			out.println((i+1) + ": 안녕 Servlet!!!<br>");
			
		}
	}

}
