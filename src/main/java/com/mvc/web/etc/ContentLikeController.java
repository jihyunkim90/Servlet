package com.mvc.web.etc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.service.NoticeDAO;

@WebServlet("/board/content/like")
public class ContentLikeController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		 resp.setContentType("text/html; charset=UTF-8");
		
		//세션값 불러오기
				String userID=req.getSession().getAttribute("userID").toString();
				String userNm=req.getSession().getAttribute("UserNm").toString();
				String userRank=req.getSession().getAttribute("userRank").toString();
				//세션값 테스트
				System.out.println("content userID" +userID);
				System.out.println("content userNm" +userNm);
				System.out.println("content userRank" +userRank);
				
				String pid_=req.getParameter("pid");
				int pid=Integer.parseInt(pid_);
		
		NoticeDAO.getInstance().addLike(pid, userID);
		
		
		resp.sendRedirect("/board/content/detail?id="+pid);
	}
}
