package com.EduTestPortal.model;

import java.sql.Timestamp;

public class Result {
	
	private int rid;
	private int qid;
	private int sid;
	private int score;
	private Timestamp takenAt;
	private String quizTitle;
	private String quizSubject;

	
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Timestamp getTakenAt() {
		return takenAt;
	}
	public void setTakenAt(Timestamp takenAt) {
		this.takenAt = takenAt;
	}
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	public String getQuizSubject() {
		return quizSubject;
	}
	public void setQuizSubject(String quizSubject) {
		this.quizSubject = quizSubject;
	}
	
	

}
