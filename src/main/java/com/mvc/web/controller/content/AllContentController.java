package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.web.entity.content.EtcList;
import com.jdbc.web.entity.content.notice;
import com.mvc.web.service.NoticeDAO;
@WebServlet("/board/content/allcontent")
public class AllContentController extends HttpServlet{
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
		//jsp 파라미터 초기화 및 불러오기
		int page=1;
		String page_=req.getParameter("p"); //page
	
		String qurry_=req.getParameter("q");//검색어
		
		String qurry=""; //검색어 초기값
		
		if(page_!=null&& !page_.equals("")) {
			page=Integer.parseInt(page_);
		}
		
		
		if(qurry_!=null&& !qurry_.equals("")) {
			qurry=qurry_;
			
		}
		
		
		
		//int count =NoticeDAO.getInstance().getCount(field,qurry,userRank); //총 글 갯수
		
		
		EtcList el = NoticeDAO.getInstance().getAllContent(page,qurry, userRank);
                    /*참조변수 넘어가는거 object 형태*/
		
		int count =el.getCount(); //EtcList 로부터 list  꺼냄
		List<notice> list=el.getList();
		
        req.setAttribute("name", userNm);
		req.setAttribute("count", count);
		req.setAttribute("list", list);
		String str =req.getParameter("cnt");
		
		req.getRequestDispatcher("/WEB-INF/board/content/AllContentList.jsp").forward(req, resp);

	}
}
