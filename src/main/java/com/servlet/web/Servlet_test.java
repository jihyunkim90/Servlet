package com.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class Servlet_test extends HttpServlet {
	@Override /* 메인 */
	/* 입력 */ /* 출력 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("hello servlet");
		PrintWriter out = res.getWriter();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		int cnt =0;
		if(req.getParameter("cnt")!=null&&req.getParameter("cnt")!="") {
			cnt=Integer.parseInt(req.getParameter("cnt"));
		}
				
		
			/* parameter는 글자 라서 숫자로 바꿔줘야함 */

		System.out.println("cnt :" + cnt);
		for (int i = 0; i <= cnt; i++) {
			
		}
		out.println("<h1>hello!<h1>");

		/* cnt 없을때? parameter 없을때?? */
	}
}
