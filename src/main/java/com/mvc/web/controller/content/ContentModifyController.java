package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.web.entity.content.notice;
import com.mvc.web.service.NoticeDAO;
@WebServlet("/board/content/modify")
public class ContentModifyController extends HttpServlet {
 
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			 req.setCharacterEncoding("UTF-8");
			 resp.setContentType("text/html; charset=UTF-8");
			
			HttpSession session = req.getSession();
			//=String userID =req.getSession().getAttribute("userID").toString();
			String userID = (String) session.getAttribute("userID");
			String userNm = (String) session.getAttribute("UserNm");
			req.setAttribute("name", userNm);
			String pid_=req.getParameter("id");
			
			int pid=Integer.parseInt(pid_);
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			System.out.println("title :" +title);
			System.out.println("content :" +content);
			
			int result = NoticeDAO.getInstance().updateContent(pid, userID, title, content);

			if(result>0)
				resp.sendRedirect("/board/content/detail?id="+pid);
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			
			notice nt = NoticeDAO.getInstance().getDetail(id);

			req.setAttribute("nt", nt);
			req.setAttribute("f", field);
			req.setAttribute("q", qurry);

			req.getRequestDispatcher("/WEB-INF/board/content/modify.jsp").forward(req, resp);

	}

}