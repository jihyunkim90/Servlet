package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.web.entity.content.notice;
import com.mvc.web.service.NoticeDAO;
import com.mysql.cj.protocol.x.Notice;

@WebServlet("/board/content/detail")
public class NoticeDetailController extends HttpServlet {
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
				
				
		String par = req.getParameter("id");
		
		String field_=req.getParameter("f");
		String qurry_=req.getParameter("q");
		String field="title";
		String qurry="";

		int id = 0;

		if (par != null && !par.equals("")) {
			id = Integer.parseInt(par);
		}
		
		if (field_ != null && !field_.equals("")) {
			field=field_;
		}
		
		if (qurry_ != null && !qurry_.equals("")) {
			qurry =qurry_;
		}
		
		NoticeDAO.getInstance().upHit(id);
		
		notice nt = NoticeDAO.getInstance().getDetail(id);
		
		req.setAttribute("sid", userID);
		req.setAttribute("srank", userRank);
		
		req.setAttribute("nt", nt);
		req.setAttribute("f", field);
		req.setAttribute("q", qurry);

		req.getRequestDispatcher("/WEB-INF/board/content/Detail.jsp").forward(req, resp);

	}

	
}
