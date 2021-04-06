package com.emyus.ca;

import java.io.Serializable;

import lombok.Data;

@Data
public class CARequest implements Serializable {

		private String answer;
		private int questions_id;
		private int id;

		CARequest(String answer, int questions_id, int id){
			this.answer = answer;
			this.questions_id = questions_id;
			this.id = id;
		}

		CARequest(){
		}

		public int getQuestionsId() {
			// TODO 自動生成されたメソッド・スタブ
			return questions_id;
		}
	}