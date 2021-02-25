package com.newlecture.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// list?f=title&q=a
		
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
//
		String field = "title";
		if(field_ != null)
			field = field_;
		
		String query="";
		if(query_ != null)
			query = query_;
	
		// 목록을 만들기 위한 객체 생성
		NoticeService service = new NoticeService();
		
		List<Notice> list = service.getNoticeList(field, query, 1);

		request.setAttribute("list", list);

		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response); // dispatcher를 이용해서
																									// detail.jsp를 호출하면서
																									// 이 페이지에서 사용하고 있는
																									// 저장소(request)와
																									// 출력도구(response)를
																									// 같이 공유

	}
}
