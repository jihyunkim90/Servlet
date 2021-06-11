
package com.mvc.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.web.entity.user.Login;
import com.jdbc.web.entity.user.User;
import com.mvc.web.service.UserDAO;

@WebServlet("/board/login")
public class LoginController extends HttpServlet {
	private static final Integer cookieExpire =60*60*24*30; ///1month
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		 
		 
		String pid = req.getParameter("id");
		String ppass = req.getParameter("pass");
		String remember=req.getParameter("remember");

		
		Login lg=new Login(pid,ppass);
		
				
		User ur=(UserDAO.getInstance().logincheck(lg));
		
		int result=ur.getNumber();
		
		switch (result) {
		case -1:
			req.setAttribute("ment", "패스워드 틀림");
			doGet(req,resp);
			break;
		case 0:
			req.setAttribute("ment", "존재하지 않는 ID");
			doGet(req,resp);
			break;
		case 1://로그인 성공
			String userID=ur.getId();
			String userNm =ur.getName();
			String userRank= ur.getRank();
			String userEmail=ur.getEmail();
			
			System.out.println("userID :" +userID );
			System.out.println("userNm :" +userNm );
			System.out.println("userRank :" +userRank );
			System.out.println("userEmail :" +userEmail );
			
			HttpSession session =req.getSession();
			session.setAttribute("userID", userID);
			session.setAttribute("UserNm", userNm);
			session.setAttribute("userRank", userRank);
			session.setAttribute("userEmail", userEmail);
			
			if(remember!=null) {
				setCookie("sid",pid,resp);
			}
			
			System.out.println("세선 이름 :" +(req.getSession().getAttribute("userID")).toString());
			
			resp.sendRedirect("/board/content/list");
			break;

		default:
			break;
		}

		System.out.println("결과 :" + result);

	
	}

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userID =getCookie("sid",req);
		req.setAttribute("id", userID);
		
		req.getRequestDispatcher("/WEB-INF/board/user/login.jsp").forward(req, resp);
	}
	//쿠키 불러오기
	private String getCookie(String cid, HttpServletRequest req) {
		String ret="";
		
		if(req ==null) {
			return ret;
		}
		
		Cookie[] cookies=req.getCookies();
		
		if(cookies ==null) {
			return ret;
		}
		
		for(Cookie ck :cookies) {
			if(ck.getName().equals(cid)) {
				
				ret=ck.getValue();
				System.out.println("ck.getname"+ck.getName());
				System.out.println("ck value :" +ck.getValue());
				System.out.println("ret :" +ret);
				ck.setMaxAge(cookieExpire);
				break;
			}
		}
		return ret;
	}



	//쿠키생성
	private void setCookie(String cid, String pid, HttpServletResponse resp) {
		Cookie ck=new Cookie(cid, pid); //sid, id(접속한 사람 id)
		ck.setPath("/");
		ck.setMaxAge(cookieExpire);
		resp.addCookie(ck);
	}
}
