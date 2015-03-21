package com.dahirkanehl.olakoa.drinks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class DrinksDao {

	private String csvFile = "drinks.db";
	private Map<String, Drink> drinks = new HashMap<String, Drink>();
	
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
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = br.readLine();
			while(line != null) {
				String[] drinkString = line.split(",");
				drinkStringList.add(drinkString);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return drinkStringList;
	}

	public List<Drink> findByOwner(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Drink> findPostedEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Drink> findAllDrinks() {
		return drinks.values();
	}

	public void addDrink(Drink newDrink) {
		// TODO Auto-generated method stub
		
	}

	public Drink findById(String did) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateDrink(Drink d) {
		// TODO Auto-generated method stub
		
	}
}
