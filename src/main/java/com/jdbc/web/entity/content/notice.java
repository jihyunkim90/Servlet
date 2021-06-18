package com.jdbc.web.entity.content;

import java.util.Date;

public class notice {
	int boardid;
	String board;
	int id;
	String title;
	String writer_id;
	String content;
	Date regdate;
	int hit;
	int like;
	int cocnt;
	String files;
	
	
	
	

	public notice(int boardid, String board, int id, String title, String writer_id, String content, Date regdate,
			int hit, int like, int cocnt, String files) {
	
		this.boardid = boardid;
		this.board = board;
		this.id = id;
		this.title = title;
		this.writer_id = writer_id;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.like = like;
		this.cocnt = cocnt;
		this.files = files;
	
	}



	public int getBoardid() {
		return boardid;
	}



	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}



	public String getBoard() {
		return board;
	}



	public void setBoard(String board) {
		this.board = board;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getWriter_id() {
		return writer_id;
	}



	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public Date getRegdate() {
		return regdate;
	}



	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}



	public int getHit() {
		return hit;
	}



	public void setHit(int hit) {
		this.hit = hit;
	}



	public int getCocnt() {
		return cocnt;
	}



	public void setCocnt(int cocnt) {
		this.cocnt = cocnt;
	}



	public String getFiles() {
		return files;
	}



	public void setFiles(String files) {
		this.files = files;
	}



	public int getLike() {
		return like;
	}



	public void setLike(int like) {
		this.like = like;
	}



	public notice(String title, String writer_id, String content) {
		this.title = title;
		this.writer_id = writer_id;
		this.content = content;
	}

	
	
	@Override
	public String toString() {
		return "notice [board=" + board + ", id=" + id + ", title=" + title + ", writer_id=" + writer_id + ", content="
				+ content + ", regdate=" + regdate + ", hit=" + hit + ", cocnt=" + cocnt + "]";
	}



	


	public notice() {

	}
//detail보기
	public notice(String board, int id, String title, String writeid, String content, Date regdate, int hit, String files) {
		this.board = board;
		this.id = id;
		this.title = title;
		this.writer_id = writeid;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.files=files;
		
	}

	public notice(int id, String title, String writeid, String content, Date regdate, int hit, int cocnt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer_id = writeid;
		this.hit = hit;
		this.regdate = regdate;
		this.cocnt=cocnt;
	}


//글쓰기용 생성자
	public notice(int boardid,String writer_id, String title, String content, String files) {
		
		this.boardid = boardid;
		this.title = title;
		this.writer_id = writer_id;
		this.content = content;
		this.files = files;
	}

	

}
