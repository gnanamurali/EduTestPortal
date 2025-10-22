package com.EduTestPortal.model;

import java.sql.Timestamp;

public class Quiz {

	private int qid;
	private String title;
	private String subject;
	private int tid;
	private Timestamp createdAt;
	private int duration;
	private boolean isVisible;
	private String teacherName;
	
	

	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public boolean isVisible() 
	{ return isVisible; }
	public void setVisible(boolean isVisible) 
	{ this.isVisible = isVisible; }

	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public int getDuration() {
	    return duration;
	}
	public void setDuration(int duration) {
	    this.duration = duration;
	}
	
	
}
