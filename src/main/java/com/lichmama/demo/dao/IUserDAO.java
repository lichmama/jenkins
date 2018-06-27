package com.lichmama.demo.dao;

import com.lichmama.demo.entity.User;

public interface IUserDAO {

	User getUserById(int id);
	
	User getUserByName(String username);
	
	void addUser(User user);
}
