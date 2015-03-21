package com.dahirkanehl.olakoa.users;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private String csvFile = "user.db";
	
	public List<String[]> getUsersStrings() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userStringList;
	}
}
