package com.lichmama.demo.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lichmama.demo.common.util.StringUtil;
import com.lichmama.demo.dao.IUserDAO;
import com.lichmama.demo.entity.User;

@Repository
public class UserDAOImpl implements IUserDAO {

	private Map<Integer, User> userMap = Collections.synchronizedMap(new HashMap<Integer, User>());

	@Override
	public User getUserById(int id) {
		return userMap.get(id);
	}

	@Override
	public User getUserByName(String username) {
		if (StringUtil.isEmpty(username))
			return null;
		Iterator<Map.Entry<Integer, User>> iter = userMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, User> entry = iter.next();
			User user = entry.getValue();
			if (username.equals(user.getUsername()))
				return user;
		}
		return null;
	}

	@Override
	public void addUser(User user) {
		if (userMap.containsKey(user.getId()))
			throw new IllegalArgumentException("user exists!");
		userMap.put(user.getId(), user);
	}

}
