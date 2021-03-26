package com.emyus.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.ca.CorrectAnswer;

	@Mapper
	public interface CARepository{
	public List<CorrectAnswer> findAll();

	public void create();
}
