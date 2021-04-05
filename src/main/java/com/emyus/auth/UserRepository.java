package com.emyus.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.user.UserRequest;

@Mapper
public interface UserRepository {
	public User identifyUser(String id);

	public User getOne(String username);

	public List<User> findAll();

	public void create(UserRequest userRequest);

	public void update(UserRequest userRequest);

	public void delete(UserRequest userRequest);
}
