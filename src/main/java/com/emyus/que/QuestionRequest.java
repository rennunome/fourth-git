package com.emyus.que;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class QuestionRequest implements Serializable {

	@NotEmpty(message = "問題を入力してください。")
	@Size(max = 500, message= "質問は500文字以内で入力してください。")
	private String question;

	private int id;

	QuestionRequest(String question, int id){
		this.question = "くまもんの出身は？";
		this.id = 1;
	}

	QuestionRequest(){

	}
}
