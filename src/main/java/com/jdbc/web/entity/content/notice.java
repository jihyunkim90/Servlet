package com.jdbc.web.entity.content;

import java.util.Date;

public class notice {
	String board;
	int id;
	String title;
	String writer_id;
	String content;
	Date regdate;
	int hit;

	public notice(String title, String writer_id, String content) {
		this.title = title;
		this.writer_id = writer_id;
		this.content = content;
	}

	@Override
	public String toString() {
		return "notice [id=" + id + ", title=" + title + ", writer_id=" + writer_id + ", content=" + content
				+ ", regdate=" + regdate + ", hit=" + hit + "]";
	}

	public notice() {

	}

	public notice(String board, int id, String title, String writeid, String content, Date regdate, int hit) {
		this.board = board;
		this.id = id;
		this.title = title;
		this.writer_id = writeid;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;

	}

	public notice(int id, String title, String writeid, String content, Date regdate, int hit) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer_id = writeid;
		this.hit = hit;
		this.regdate = regdate;
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

}
