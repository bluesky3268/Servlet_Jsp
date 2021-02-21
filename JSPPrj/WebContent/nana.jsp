<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%

	int cnt = 100;
	String cnt_ = request.getParameter("cnt");
	// Query String- 입력값이 없으면 오류가 발생하기 때문에 기본값을 설정해줘야 한다.
	if(cnt_ != null && !cnt_.equals("")) {
		cnt = Integer.parseInt(cnt_);
	}
	for(int i = 0; i < cnt; i++) {
		out.println((i+1) + ": 안녕 Servlet!!!<br>");		
	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% for(int i = 0; i < cnt; i++){ %>
		안녕ㅎ Servlet!!ㅎ<br>
	<%} %>
</body>
</html>