package com.assimilate.user.services;

import java.util.ArrayList;
import java.util.List;

import com.assimilate.user.dao.UserDao;
import com.assimilate.user.models.User;

public class UserService {

	private UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public int addUser(User user) {
		return userDao.addUser(user);
	}
public List<String> validateUser(User user) {

		List<String> errors = new ArrayList<>();

		if (user.getName() == null || user.getName().isEmpty()) {
			errors.add("Name cannot be empty.");
		}

		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			errors.add("Password cannot be empty.");
		}

		if (user.getCountry() == null || user.getCountry().isEmpty()) {
			errors.add("Country cannot be empty.");
		}

		return errors;
	}

	public int deleteUser(String id) {
		return userDao.deleteUser(Integer.parseInt(id));
		
	}

	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	public int updateUser(User user) {
		
		return userDao.updateUser(user);
	}
}
