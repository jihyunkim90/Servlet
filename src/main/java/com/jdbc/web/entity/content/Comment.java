package com.jdbc.web.entity.content;

import java.util.Date;

public class Comment {
 int cid;
 int pid;
 String comment;
 String writeid;
 Date regdate;
 int rating;
 
 
public Comment(int cid, int pid, String comment, String writeid, Date regdate, int rating) {
	
	this.cid = cid;
	this.pid = pid;
	this.comment = comment;
	this.writeid = writeid;
	this.regdate = regdate;
	this.rating = rating;
}
@Override
public String toString() {
	return "Comment [cid=" + cid + ", pid=" + pid + ", comment=" + comment + ", writeid=" + writeid + ", regdate="
			+ regdate + ", rating=" + rating + "]";
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public String getWriteid() {
	return writeid;
}
public void setWriteid(String writeid) {
	this.writeid = writeid;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
 
 
}
