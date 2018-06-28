package com.lichmama.demo.service;

import com.lichmama.demo.entity.User;

public interface IUserService {

	User getUserById(int id);

	User getUserByName(String username);

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(int id);
	
	void updateUserWithException();
}
