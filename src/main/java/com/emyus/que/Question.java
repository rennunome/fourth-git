package com.emyus.que;

import java.sql.Timestamp;

public class Question {
	private int id;
	private String question;
	private Timestamp created_at;
	private Timestamp updated_at;

	public Question(int id, String question) {
		this.id = id;
		this.question = question;

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Timestamp getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}
