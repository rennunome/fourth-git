package com.emyus.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.ca.CARequest;
import com.emyus.ca.CorrectAnswer;

	@Mapper
	public interface CARepository{
	public List<CorrectAnswer> findAll();

	void create(CARequest caRequest);

	void update(CARequest caRequest);

	public CorrectAnswer findById(CARequest caRequest);

	void delete(CARequest caRequest);

	public List<CorrectAnswer> findByQuestionId(int question_id);

	}
