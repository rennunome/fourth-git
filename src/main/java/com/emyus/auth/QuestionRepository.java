package com.emyus.auth;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.que.Question;
import com.emyus.que.QuestionRequest;

@Mapper
public interface QuestionRepository {
	public List<Question> findAll();

	void create(QuestionRequest questionRequest);

	void update(QuestionRequest questionRequest);

	public Question findById(QuestionRequest questionRequest);

	void delete(QuestionRequest questionRequest);
}
