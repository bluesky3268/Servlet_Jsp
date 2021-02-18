package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//출력을 위한 것.
		response.setCharacterEncoding("UTF-8"); // utf-8로 써서 보내는 거.
		response.setContentType("text/html; charset=UTF-8"); // 브라우저에게 내가 utf-8로 작성했으니 그걸로 읽어! 하는 거.
		//request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
	}

}
