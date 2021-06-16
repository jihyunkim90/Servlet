package com.mvc.web.etc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.web.entity.content.notice;
import com.mvc.web.service.NoticeDAO;
@WebServlet("/board/content/serach")
public class searchController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<notice> list= NoticeDAO.getInstance().getContentAll();
		
		req.getRequestDispatcher("/WEB-INF/board/content/ContentList.jsp").forward(req, resp);
}
}
