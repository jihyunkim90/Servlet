package com.jdbc.web.entity.content;

public class MenuList {
	int boardid;
	String boardName;
	
	
	@Override
	public String toString() {
		return "MenuList [boardid=" + boardid + ", boardName=" + boardName + "]";
	}
	public MenuList(int boardid, String boardName) {
	
		this.boardid = boardid;
		this.boardName = boardName;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	
}
