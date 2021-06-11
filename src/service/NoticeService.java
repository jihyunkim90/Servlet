package com.jdbc.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.app.entity.notice;

public class NoticeService {
	final private static String url = "jdbc:mysql://localhost:3306/study?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	final private static String id = "jihyunkim90";
	final private static String pass = "1234";
	final private static String driver = "com.mysql.jdbc.Driver";

	/* 글목록 */
	public List<notice> getList() {
		String sql = "select *from notice"; // 조회 sql
		List<notice> list = new ArrayList<>(); // list 배열 생성
		try {

			Class.forName(driver); // 필드 값 으로 driver 선언
			Connection con = DriverManager.getConnection(url, id, pass);
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {// 구현 notice 이용
				int id=rs.getInt("id");
				String title =rs.getString("title");
				String writer_id=rs.getString("writer_id");
				String content =rs.getString("content");
				Date regdate =rs.getTimestamp("regdate");
				int hit =rs.getInt("hit");
				//조회 된 값을 입력 초기화 생성자생성
				notice nt=new notice(id, title,writer_id, content, regdate,hit);
				//list 조회 값 저장된 notice 객체추가
				list.add(nt);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;

	}

	/* 글 갯수 */
	public int getCount() {
		int count = 0; // 글갯수 변수 count 만들기
		String sql = "select count(id) as count from notice"; // 조회 sql
		List<notice> list = new ArrayList<>(); // list 배열 생성
		try {

			Class.forName(driver); // 필드 값 으로 driver 선언
			Connection con = DriverManager.getConnection(url, id, pass);
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return count;
	}
}
