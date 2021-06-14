package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.web.entity.content.EtcList;
import com.jdbc.web.entity.content.Picture;
import com.jdbc.web.entity.content.notice;
import com.mvc.web.connection.ConnectionProvider;
import com.mvc.web.connection.jdbcUtil;

import com.mysql.cj.protocol.x.Notice;

public class NoticeDAO {

	private static NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getInstance() {
		return instance;
	}

//	public List<notice> getList(){
//		return getList(1, "");
//	}
//	
//	public List<notice> getList(int page){
//		return getList(page,"");
//	}

	public EtcList getList(int page, String qurry, String field, String rank) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count=0;
		int start = 1 + (page - 1) * 10;
		int end = page * 10;
		
		
		
		String sql1= "select * ,  (select count(id) as count "
				+ "				     from tbl_board "
				+ "				       	where (LEVENSHTEIN(writer_id, ?)<=2)"
				+ "                          and useFlag ='Y' "
				+ "				           	  and boardid in (select boardID "
				+ "				         				    from user_auth "
				+ "				            			       where rankcd= ?)) as count "
				+ "					  from (select @rownum:=@rownum+1 as num ,n.* "
				+ "						          from( select * "
				+ "					                 from tbl_board "
				+ "										    where (LEVENSHTEIN(writer_id, ?)<=2) "
				+ "											and useFlag ='Y' "
				+ "					                   and boardid in (select boardID "
				+ "										    from user_auth "
				+ "											   where rankcd=? ) "
				+ "						      			order by regdate desc)n, "
				+ "								  (SELECT @rownum:=0)low) num "
				+ "							  where num.num between ? and ?";

		String sql2 = "	select * ,  (select count(id) as count "
				+ "			  			              from tbl_board "
				+ "				    	           	where "+field+" like ? "
				+ "						           	  and useFlag ='Y' "
				+ "						           	  and boardid in (select boardID "
				+ "						             				    from user_auth "
				+ "						             			       where rankcd= ?)) as count "
				+ "					  from (select @rownum:=@rownum+1 as num ,n.* "
				+ "					          from( select * "
				+ "						                 from tbl_board "
				+ "									    where "+field+" like ? "
				+ "										  and useFlag ='Y' "
				+ "					                   and boardid in (select boardID "
				+ "														    from user_auth "
				+ "														   where rankcd=? ) "
				+ "						      			order by regdate desc)n, "
				+ "							  (SELECT @rownum:=0)low) num "
				+ "					  where num.num between ? and ? "; // 조회 sql
		List<notice> list=new ArrayList<>();

		
		
		
		try {
			//검색 조건이 title 일때
			con = ConnectionProvider.getConnection();
			
			if(field.equals("title")) {
				psmt = con.prepareStatement(sql2);
				psmt.setString(1, "%" + qurry + "%");
				psmt.setString(2, rank);
				psmt.setString(3, "%" + qurry + "%");
				psmt.setString(4, rank);
				psmt.setInt(5, start);
				psmt.setInt(6, end);
				System.out.println(psmt);
			//검색 조건이 writer_id일때
			}else if(field.equals("writer_id")){
				psmt = con.prepareStatement(sql1);
				psmt.setString(1,  qurry);
				psmt.setString(2, rank);
				psmt.setString(3,  qurry);
				psmt.setString(4, rank);
				psmt.setInt(5, start);
				psmt.setInt(6, end);
				System.out.println(psmt);
			}
			rs = psmt.executeQuery();
			

			while (rs.next()) {
				int id1 = rs.getInt("id");
				String title = rs.getString("title");
				String writeid = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				count =rs.getInt("count");

				// 조회 된 값을 입력하여 초기화하는 생성자 생성
				notice ns = new notice(id1, title, writeid, content, regdate, hit);
			
				// list 에 조회된 값이 저장된 notice 객체 추가
				list.add(ns);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		
		EtcList el=new EtcList(count, list);
		return el;
	}

	/* 자세히보기 */
	public notice getDetail(int no) {
		notice ns = null;
		String sql = " SELECT tb.id, bm.board_name, tb.title, tb.writer_id, tb.content, tb.regdate, tb.hit "
				+ "				    FROM tbl_board tb, "
				+ "			        board_master bm "
				+ "			     WHERE bm.board_id = tb.boardid "
				+ "			     AND tb.useFlag = 'Y' "
				+ "				      AND tb.id = ? ";
		try {

			Connection con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			System.out.println(psmt);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				String board=rs.getString("board_name");
				int id1 = rs.getInt("id");
				String title = rs.getString("title");
				String writeid = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");

				ns = new notice(board, id1, title, writeid, content, regdate, hit);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ns;
	}

	public int getCount(String field, String qurry, String rank) {
		int count = 0;
		String sql = "select count(*) as count " 
					+ " from tbl_board " 
					+ " where useFlag ='Y' "
					+ " and " + field
					+ " like ? ";

		try {

			Connection con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%" + qurry + "%");
			System.out.println(psmt);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
				System.out.println("service :" + count);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int regeditNotice(String writer_id, String title, String content) {
		String sql = "insert into notice(title,writer_id,content) values(?,?,?)";
		int result = 0;
		try {

			Connection con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, writer_id);
			psmt.setString(3, content);

			result = psmt.executeUpdate(); // 숫자로 나온다

			if (result == 0) {
				result = 0;
				System.out.println("실패");
			} else if (result == 1) {
				result = 1;
				System.out.println("성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateContent(int pid, String userID, String title, String content) {
		int result = 0;
		Connection con = null;
		PreparedStatement psmt = null;

		String sql = " update tbl_board set title=? ,content =? , writer_id= ? where id=?; ";

		try {

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, userID);
			psmt.setInt(4, pid);
			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);

		}

		return result;
	}

	//조회수 증가
	public void upHit(int id) {
	
		Connection con = null;
		PreparedStatement psmt = null;

		String sql = " update tbl_board set hit=hit+1 where id=? ";

		try {

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);

		}
		
	}

	
 //그림 리스트 불러오기
	public List<Picture> getPictureList() {
		String sql = " SELECT * FROM tbl_picture  ";
		List<Picture> list=new ArrayList<>();
		try {

			Connection con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
		
			System.out.println(psmt);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				int id1 = rs.getInt("id");
				String ptitle = rs.getString("ptitle");
				String writeid = rs.getString("writeid");
				String path = rs.getString("path");
				Date regdate = rs.getTimestamp("regdate");
		
				Picture pt = new Picture( id1, ptitle, writeid, path, regdate);
				list.add(pt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

}
