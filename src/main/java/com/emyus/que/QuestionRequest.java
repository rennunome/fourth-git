package com.emyus.que;

import java.io.Serializable;

import lombok.Data;

@Data
public class QuestionRequest implements Serializable {

	private String question;
	private int id;

	QuestionRequest(String question, int id){
		this.question = question;
		this.id = id;
	}

	QuestionRequest(){
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
