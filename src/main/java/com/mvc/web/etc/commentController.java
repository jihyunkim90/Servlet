package com.mvc.web.etc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.web.entity.content.Comment;
import com.mvc.web.service.NoticeDAO;

@WebServlet("/board/content/comment")
public class commentController extends HttpServlet {
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
		//jsp 파라미터 초기화 및 불러오기
		int id=0;
		int count=0;
		
	String id_=req.getParameter("id"); //글 아이디
		
		if(id_!=null&& !id_.equals("")) {
			id=Integer.parseInt(id_);
		}
		
		count =NoticeDAO.getInstance().getCommentCount(id);
		System.out.println("카운트 값 : " +count);
		
		if(count!=0) {
		
		
		//댓글 출력
		List<Comment> list =NoticeDAO.getInstance().getCommentList(id);
		
		req.setAttribute("colist", list);
		}
		
		req.setAttribute("count", count);
		req.setAttribute("pid", id);
		req.getRequestDispatcher("/WEB-INF/board/etc/comment.jsp").forward(req, resp);
}
 
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
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
			
			int pid=0;
			String pid_=req.getParameter("pid");
			String comment=req.getParameter("comment");
			
			if(pid_!=null&& !pid_.equals("")) {
				pid =Integer.parseInt(pid_);
			}
					
			//댓글쓰기
			NoticeDAO.getInstance().insertComment(pid,userID,comment);
			//댓글 갯수
			NoticeDAO.getInstance().addCommentCount(pid);
			
			
			//디테일 jsp 에 id 값 보내서 출력하기
			resp.sendRedirect("/board/content/detail?id="+pid);
			
			
	}
}
