package com.mvc.web.controller.content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.jdbc.web.entity.content.notice;
import com.mvc.web.service.NoticeDAO;

@MultipartConfig(
		fileSizeThreshold =1024*1024, //1메가
		maxFileSize = 1024*1024*5,//5메가
		maxRequestSize = 1024*1024*5*5//25메가
		)


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

	
		//파일올리기 파츠
		Collection<Part> parts=req.getParts();
		StringBuilder builder=new StringBuilder(); //파일 여러개 글자 이어붙이기
		
		for(Part p : parts) {
			if(!p.getName().equals("file")) continue; //input box 의 name file 이 아닌 경우 지나가기
			
			if(p.getSize()==0) continue; //파일 크기가 0일 때 지나가기
			
			Part filePart =p;
			String fileName = filePart.getSubmittedFileName();
			
			System.out.println("file name :" +fileName);
			
			
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis =filePart.getInputStream();
			
			//파일 불러오기
			String realPath =req.getServletContext().getRealPath("/upload");
			
			System.out.println("realpath : "+realPath);
			
			File path = new File(realPath);
			
		
			
			if(!path.exists()) {
				path.mkdirs();
			}
			
			String filePath =realPath +File.separator+fileName;
			System.out.println("File path :" +filePath);
			
			FileOutputStream fos = new FileOutputStream(filePath);
			//올리기
			
			byte[] buf =new byte[1024];
			int size =0;
			
			
			while((size = fis.read(buf))!=-1) {
				fos.write(buf,0,size);
			}
		
			fos.close();
			fos.close();
			
			builder.delete(builder.length()-1, builder.length());
			
		
		}
		
		notice nt= new notice(1, userID, title, content, builder.toString());
		
		NoticeDAO.getInstance().regeditNotice(nt);

			resp.sendRedirect("list");
			
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userNm = (String) session.getAttribute("UserNm");
		req.setAttribute("name", userNm);

		req.getRequestDispatcher("/WEB-INF/board/content/regedit.jsp").forward(req, resp);
	}
}
