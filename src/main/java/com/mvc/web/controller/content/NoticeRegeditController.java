package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.service.NoticeDAO;

@WebServlet("/board/content/regedit")
public class NoticeRegeditController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 req.setCharacterEncoding("UTF-8");
		 resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
		//=String userID =req.getSession().getAttribute("userID").toString();
		String userID = (String) session.getAttribute("userID");
		String userNm = (String) session.getAttribute("UserNm");
		req.setAttribute("name", userNm);
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		NoticeDAO nd = new NoticeDAO();
		int result = nd.regeditNotice(userID, title, content);

		if (result == 0) {
			req.setAttribute("ment", "작성 실패");
			doGet(req, resp);
		} else if (result == 1) {
			resp.sendRedirect("/board/content/list");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userNm = (String) session.getAttribute("UserNm");
		req.setAttribute("name", userNm);

		req.getRequestDispatcher("/WEB-INF/board/content/regedit.jsp").forward(req, resp);
	}
}
