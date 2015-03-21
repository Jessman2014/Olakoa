package com.dahirkanehl.olakoa.users;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private String csvFile = "users.db";
	private Map<String, User> users = new HashMap<String, User>();
	
	public void init() {
		List<String[]> userStringList = getUsersStrings();
		for (String[] strings : userStringList) {
			Role r;
			if (strings[6].equals("USER"))
				r = Role.USER;
			else if (strings[6].equals("ADMIN"))
				r = Role.ADMIN;
			else
				r = Role.SHOPPER;
			User u = new User.Builder().id(strings[0]).email(strings[1]).firstName(strings[2])
					.lastName(strings[3]).username(strings[4]).password(strings[5]).role(r)							
					.enabled(new Boolean(strings[7])).build();
			users.put(u.getId(), u);
		}
	}
	
	private List<String[]> getUsersStrings() {
		List<String[]> userStringList = new ArrayList<String[]>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = br.readLine();
			while(line != null) {
				String[] userString = line.split(",");
				userStringList.add(userString);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userStringList;
	}
}
