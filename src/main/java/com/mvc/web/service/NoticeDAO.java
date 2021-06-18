package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.web.entity.content.Comment;
import com.jdbc.web.entity.content.EtcList;
import com.jdbc.web.entity.content.MenuList;
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
		int start = (page-1)*10;
		
		
		
		String sql1= "select dat.*, cnt.count "
				+ " from  (select low.* , it.lk			 "
				+ "		  from(select * 						"
				+ "		 		from tbl_board                         "
				+ "		 	   where (levenshtein(writer_id, ?) <= 2)	"
				+ "		 		 and useFlag ='Y' 						   		"
				+ "		 		 and boardid = ?						"
				+ "				order by regdate desc  limit 10 offset ?)low"
				+ "				LEFT OUTER JOIN "
				+ "			  (select pid, count(lid) as lk"
				+ "			     from tbl_like "
				+ "			    group by pid)it "
				+ "			  ON low.id = it.pid)dat, "
				+ "		(select count(id) as count						"
				+ "           from tbl_board 					   "
				+ "		  where (levenshtein(writer_id, ?) <= 2)						 "
				+ "            and useFlag ='Y' 						 "
				+ "            and boardid = ? )cnt  ";

		String sql2 = "	 select dat.*, cnt.count "
				+ " from(select low.*, "
				+ "             it.lk "
				+ "        from(select * 						"
				+ "			from tbl_board 					   "
				+ "          where title like ? 						 "
				+ "            and useFlag ='Y' 						 "
				+ "			and boardid =? 						"
				+ "		order by regdate desc  limit 10 offset ?)low					 "
				+ "		LEFT OUTER JOIN "
				+ "		(select pid, count(lid) as lk "
				+ "		   from tbl_like "
				+ "		  group by pid)it "
				+ " ON low.id = it.pid)dat, "
				+ " (select count(id) as count						"
				+ "           from tbl_board 					  "
				+ "		  where title like ?						"
				+ "            and useFlag ='Y' 						 "
				+ "            and boardid =?)cnt  "; // 조회 sql
		List<notice> list=new ArrayList<>();

		try {
			//검색 조건이 title 일때
			con = ConnectionProvider.getConnection();
			
			if(field.equals("title")) {
				psmt = con.prepareStatement(sql2);
				psmt.setString(1, "%" + qurry + "%");
				psmt.setString(2, rank);
				psmt.setString(4, "%" + qurry + "%");
				psmt.setString(5, rank);
				psmt.setInt(3, start);
			
				System.out.println(psmt);
			//검색 조건이 writer_id일때
			}else if(field.equals("writer_id")){
				psmt = con.prepareStatement(sql1);
				psmt.setString(1,  qurry);
				psmt.setString(2, rank);
				psmt.setString(4,  qurry);
				psmt.setString(5, rank);
				psmt.setInt(3, start);
			
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
				int like=rs.getInt("lk");
				count =rs.getInt("count");
				int cocnt=rs.getInt("cocnt");

				// 조회 된 값을 입력하여 초기화하는 생성자 생성
				notice ns = new notice(id1, title, writeid, content, regdate, hit, cocnt);
			
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
		String sql = " SELECT tb.id, bm.board_name, tb.title, tb.writer_id, tb.content, tb.regdate, tb.hit, tb.files "
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
				String files =rs.getString("files");
				int hit = rs.getInt("hit");

				ns = new notice(board, id1, title, writeid, content, regdate, hit,files);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ns;
	}
//글자수
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
//글쓰기
	public void regeditNotice(notice nt) {
		Connection con = null;
		PreparedStatement psmt = null;
	
		String sql = "insert into tbl_board(boardid, title,writer_id,content,files) values(?,?,?,?,?)";
	
		try {

		 con = ConnectionProvider.getConnection();
		 psmt = con.prepareStatement(sql);
			psmt.setInt(1, nt.getBoardid());
			psmt.setString(2, nt.getTitle());
			psmt.setString(3, nt.getWriter_id());
			psmt.setString(4, nt.getContent());
			psmt.setString(5, nt.getFiles());
		

			System.out.println(psmt);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
		}
	}
	//내용 수정
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
 //게시글 모두 조회
	public EtcList getAllContent(int page, String qurry, String rank) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count=0;
		int start = (page-1)*10;
		
		
		
		String sql= " select low.* , cnt.count"
				+ "							from(select * "
				+ "								from tbl_board "
				+ "						                     where useFlag ='Y' "
				+ "                                              and match(title, writer_id,content) against(? in boolean mode)"
				+ "									   		and boardid in (select boardID "
				+ "																		 	  from user_auth "
				+ "														                	 where rankcd= ?)"
				+ "											order by regdate desc  limit 10 offset ?)low,"
				+ "										 (select count(id) as count"
				+ "												from tbl_board "
				+ "										   where useFlag ='Y' "
				+ "                                                  and match(title, writer_id,content) against(? in boolean mode)"
				+ "												and boardid in (select boardID "
				+ "															from user_auth "
				+ "															  where rankcd= ?))cnt "; // 조회 sql
		List<notice> list=new ArrayList<>();

		
		
		
		try {
			//검색 조건이 title 일때
			con = ConnectionProvider.getConnection();

				psmt = con.prepareStatement(sql);
				psmt.setString(1,  qurry);
				psmt.setString(2, rank);
				psmt.setString(4, qurry);
				psmt.setString(5, rank);
				psmt.setInt(3, start);
			
			
				System.out.println(psmt);
			
			rs = psmt.executeQuery();
			

			while (rs.next()) {
				int id1 = rs.getInt("id");
				String title = rs.getString("title");
				String writeid = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				count =rs.getInt("count");
				int cocnt= rs.getInt("cocnt");

				// 조회 된 값을 입력하여 초기화하는 생성자 생성
				notice ns = new notice(id1, title, writeid, content, regdate, hit,cocnt);
			
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

	//rank 에 맞는 메뉴 조회
	public List<MenuList> getMymenu(String userRank) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
	
		String sql= " select board_id, board_name "
				+ " from board_master "
				+ " where useFlag ='Y' "
				+ " and board_id in ( select boardID "
				+ " 	from user_auth "
				+ "	  	where rankcd =?) "; // 조회 sql
		List<MenuList> list =new ArrayList<>();

		try {
			//검색 조건이 title 일때
			con = ConnectionProvider.getConnection();

				psmt = con.prepareStatement(sql);
				psmt.setString(1,  userRank);
				
			
				System.out.println(psmt);
			
			rs = psmt.executeQuery();
			

			while (rs.next()) {
				int menuid=rs.getInt("board_id");
				String menu = rs.getString("board_name");
				
				MenuList ml=new MenuList(menuid,menu);
				list.add(ml);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
	return list;
	}
//댓글 출력
	public List<Comment> getCommentList(int id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
	
		String sql= " select * "
				+ " from tbl_comment "
				+ " where useFlag ='Y' "
				+ " and pid=? "
				+ " order by regdate desc ; "; // 조회 sql
		List<Comment> list =new ArrayList<>(); //comment 배열 생성

		try {
			//검색 조건이 title 일때
			con = ConnectionProvider.getConnection();

				psmt = con.prepareStatement(sql);
				psmt.setInt(1,  id);
				
			
				System.out.println(psmt);
			
			rs = psmt.executeQuery();
			

			while (rs.next()) {
				int cid=rs.getInt("cid");
				int pid=rs.getInt("pid");
				String comment = rs.getString("comment");
				String writeid=rs.getString("writeid");
				Date regdate=rs.getDate("regdate");
				int rating =rs.getInt("rating");
				
				Comment ct=new Comment(cid, pid, comment, writeid, regdate, rating);
				list.add(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
	return list;
	}
//댓글 입력
	public void insertComment(int pid, String userID, String comment) {
		String sql = " insert into tbl_comment(pid, comment, writeid) "
				+ " values(?,?,?); ";
		
		try {

			Connection con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, pid);
			psmt.setString(2, comment);
			psmt.setString(3, userID);

			psmt.executeUpdate(); // 숫자로 나온다

		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
//댓글 카운트
	public int getCommentCount(int id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
	
		String sql= " select count(cid) as count "
				+ " from tbl_comment "
				+ " where useFlag ='Y' "
				+ " and pid=? ; "; // 조회 sql
		
		int count=0;
		try {
			//검색 조건이 title 일때
			con = ConnectionProvider.getConnection();

				psmt = con.prepareStatement(sql);
				psmt.setInt(1,  id);
				
			
				System.out.println(psmt);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt("count");
			}

		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
	return count;
	}
//댓글 갯수 세기
	public void addCommentCount(int pid) {
		
		Connection con = null;
		PreparedStatement psmt = null;

		String sql = " update tbl_board set cocnt=cocnt+1 "
				+ "  where id=? ";

		try {

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pid);
			System.out.println(psmt);
			 psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);

		}

	}
//좋아요 기능
	public void addLike(int pid, String userID) {
		String sql = " insert into tbl_like(pid, writeid) "
				+ " values(?,?); ";
		
		try {

			Connection con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, pid);
			psmt.setString(2, userID);
			

			psmt.executeUpdate(); // 숫자로 나온다

		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
//좋아요 눌렀는지 확인
	public int getlikeCount(String userID, int pid) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
	
		String sql= " select count(lid) as count "
				+ " from tbl_like "
				+ " where writeid=? "
				+ " and pid= ? "; // 조회 sql
		
		int count=0;
		try {
			//검색 조건이 title 일때
			con = ConnectionProvider.getConnection();

				psmt = con.prepareStatement(sql);
				psmt.setString(1,  userID);
				psmt.setInt(2,  pid);
				
			
				System.out.println(psmt);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt("count");
			}

		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
	return count;
	}
//좋아요 갯수 늘리기
	public void uplike(int likecount, String userID, int id ) {
		Connection con = null;
		PreparedStatement psmt = null;

		String sql = " update tbl_board set like=like+1 where id=? ";

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

	
}
