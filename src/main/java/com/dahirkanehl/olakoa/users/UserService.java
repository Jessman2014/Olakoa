package com.dahirkanehl.olakoa.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao database;
	private List<User> registeredUsers = new ArrayList <User>();
	
	public void init() {
		List<String[]> userStringList = database.getUsersStrings();
		for (String[] strings : userStringList) {
			User u = new User.Builder().id(strings[0]).email(strings[1]).firstName(strings[2])
					.lastName(strings[3]).username(strings[4]).password(strings[5]).role(strings[6])
					.enabled(new Boolean(strings[7])).build();
			registeredUsers.add(u);
		}
	}
	
	public User getUser(String username, String password) {
		if(username == null || password == null) return null;
		for(User user : registeredUsers) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
		}
		return null;
	}
}
