package com.emyus.auth;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.que.Question;

@Mapper
public interface QuestionRepository {
	public Question identifyQuestion(int id, String question);
}
