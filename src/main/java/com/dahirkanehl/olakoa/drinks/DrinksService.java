package com.dahirkanehl.olakoa.drinks;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dahirkanehl.olakoa.users.User;

@Service
public class DrinksService {
	@Autowired
	private DrinksDao database;

	private boolean checkOwner(User user, Drink d) {
		return user != null && d != null && user.getId() != null && user.getId().equals(d.getOwnerId());
	}
	
	public void addDrink(User owner, Drink newDrink) {
		if (checkOwner(owner, newDrink)) {
			newDrink.setId(UUID.randomUUID().toString());
			database.addDrink(newDrink);
		}
		else
			throw new IllegalArgumentException();
	}

	public Drink findDrinkById(User owner, String did) {
		Drink d = database.findById(did);
		if (d.getOwnerId() != null && d.getOwnerId().equals(owner.getId()))
			return d;
		return null;
	}

	public void updateDrink(User owner, Drink d) {
		if (checkOwner(owner, d)) 
			database.updateDrink(d);
		else
			throw new IllegalArgumentException();
	}

	public List<Drink> findAllDrinksByUser(User user) {
		return database.findByOwner(user.getId());
	}

	public List<Drink> findAllDrinksForAdmin() {
		return database.findPostedEnabled();
	}

	public List<Drink> findAllDrinksForShopper() {
		return database.findAllDrinks();
	}
	
}
