package com.lichmama.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichmama.demo.common.constant.ServiceException;
import com.lichmama.demo.dao.IUserDAO;
import com.lichmama.demo.entity.User;
import com.lichmama.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByName(String username) {
		return userDAO.getUserByName(username);
	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}
}
