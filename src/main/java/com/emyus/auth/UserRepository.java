package com.emyus.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public User identifyUser(String id);

	public User getOne(String username);

	public List<User> findAll();

	public void create();
}
