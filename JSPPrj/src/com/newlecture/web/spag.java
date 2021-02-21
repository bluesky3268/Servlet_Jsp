package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/spag")
public class spag extends HttpServlet{
	// 실행은 항상 컨트롤러에서 하는 것!
	// result데이터를 컨트롤러에서 만들어야만 뷰에서 출력할 것이 있기 때문(뷰는 껍데기다)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
				num = Integer.parseInt(num_);
		
		String result = "";
		if(num%2!= 0)
			result = "홀수";
		else 
			result = "짝수";
		
		request.setAttribute("result", result);
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice);
		
		
		// 저장소
		// 	<서버상의 저장소>
		//pageContext : 페이지 내에서 혼자 사용할 수 있는 저장소.
		//request : forward관계에서 할 수 있는 저장소.
		//session : 현재 session에서 공유될 수 있는 저장소.
		//page : 모든 session, page에서 공유될 수 있는 저장소. 
		
		//cookie : 클라이언트에 저장하는 저장소.
		
		
		//redirect (새로 요청할 때)
		//forward(현재 작업하는 일을 이어갈 때)
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
		// result의 값을 jsp(servlet)으로 넘겨주기 위해서 사용하는 저장소가 request
	}
	

}
