package com.dahirkanehl.olakoa.drinks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dahirkanehl.olakoa.users.UserDao;

@Repository
public class DrinksDao {

	private @Autowired ServletContext servletContext;
	private Map<String, Drink> drinks = new HashMap<String, Drink>();
	private UserDao userDatabase;
	
	@PostConstruct
	public void init() {
		List<String[]> DrinkStringList = getDrinksStrings();
		for (String[] strings : DrinkStringList) {
			Drink d = null;
			try {
				d = new Drink.Builder().id(strings[0]).name(strings[1]).thumbnail(new URL(strings[2]))
						.description(strings[3]).unitCost(new Integer(strings[4])).ownerId(strings[5])
						.posted(new Boolean(strings[6])).build();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			drinks.put(d.getId(), d);
		}
	}
	
	private List<String[]> getDrinksStrings() {
		List<String[]> drinkStringList = new ArrayList<String[]>();
		
		try {
			InputStream inputStream = null;
			
			inputStream = servletContext.getResourceAsStream("/WEB-INF/content/drinks.db");
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line = br.readLine();
			while(line != null) {
				String[] drinkString = line.split(",");
				drinkStringList.add(drinkString);
				line = br.readLine();
			}
			br.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return drinkStringList;
	}
	/*
	private void writeNewDrink(Drink d) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true));
			bw.newLine();
			bw.write(d.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void replaceDrinks() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, false));
			for(Drink d: drinks.values()) {
				bw.write(d.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public List<Drink> findByOwner(String id) {
		List<Drink> drinkList = new ArrayList<Drink>();
		for(Drink d: drinks.values()) {
			if(d.getOwnerId().equals(id))
				drinkList.add(d);
		}
		return drinkList;
	}

	public List<Drink> findPostedEnabled() {
		List<Drink> drinkList = new ArrayList<Drink>();
		List<String> postedEnabledUserIds = userDatabase.getPostedEnabledUserIds();
		for(Drink d: drinks.values()) {
			if(d.isPosted() && postedEnabledUserIds.contains(d.getOwnerId()))
				drinkList.add(d);
		}
		return drinkList;
	}

	public List<Drink> findAllDrinks() {
		List<Drink> drinkList = new ArrayList<Drink>();
		for(Drink d: drinks.values()) {
			drinkList.add(d);
		}
		return drinkList;
	}

	public void addDrink(Drink newDrink) {
		drinks.put(newDrink.getId(), newDrink);
		//writeNewDrink(newDrink);
	}

	public Drink findById(String did) {
		return drinks.get(did);
	}

	public void updateDrink(Drink d) {
		drinks.replace(d.getId(), d);
		//replaceDrinks();
	}
}
