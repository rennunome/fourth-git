package com.emyus.auth;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.que.Question;
import com.emyus.que.QuestionRequest;

@Mapper
public interface QuestionRepository {
	public List<Question> findAll();

	public void create(QuestionRequest questionRequest);

	public void update(QuestionRequest questionRequest);

	public Question findById(QuestionRequest questionRequest);

	public void delete(QuestionRequest questionRequest);

	public List<Question> findAllRandom();
}
