package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
// 어노테이션으로 필터하는 법
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request
				, ServletResponse response
				, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8"); // 필터, 서블릿이 실행 되기 전에 먼저 실행 됨. 그래서 모든 서블릿이 인코딩필터가 적용됨. 
		chain.doFilter(request, response); // 다음 필터나 서블릿이 실행
		
	}

}
