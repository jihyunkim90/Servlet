package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.web.entity.content.MenuList;
import com.jdbc.web.entity.content.notice;
import com.mvc.web.service.NoticeDAO;

@WebServlet("/board/etc/side")
public class sidebarController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//세션값 불러오기
				String userID=req.getSession().getAttribute("userID").toString();
				String userNm=req.getSession().getAttribute("UserNm").toString();
				String userRank=req.getSession().getAttribute("userRank").toString();
				//세션값 테스트
				System.out.println("content userID" +userID);
				System.out.println("content userNm" +userNm);
				System.out.println("content userRank" +userRank);
				
				
				List<MenuList> menu=new ArrayList<>();

		
		
		 menu = NoticeDAO.getInstance().getMymenu(userRank);
		
	
		req.setAttribute("menu", menu);
	

		req.getRequestDispatcher("/WEB-INF/board/etc/sidebar.jsp").forward(req, resp);

	}

}
