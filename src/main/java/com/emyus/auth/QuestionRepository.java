package com.emyus.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.que.Question;

@Mapper
public interface QuestionRepository {
	public List<Question> findAll();
}
