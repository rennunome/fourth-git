package com.emyus.ca;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CARequest implements Serializable {

		@NotEmpty(message = "答えを入力してください。")
		@Size(max = 200, message= "答えは200文字以内で入力してください。")
		private String answer;

		public int getQuestionsId() {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}
	}
