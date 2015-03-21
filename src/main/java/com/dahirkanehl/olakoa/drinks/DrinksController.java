package com.dahirkanehl.olakoa.drinks;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dahirkanehl.olakoa.users.Role;
import com.dahirkanehl.olakoa.users.User;

@Controller
@SessionAttributes(value="user", types=User.class)
public class DrinksController {
	@Autowired
	private DrinksService drinkService;
	
	private enum Sort { NAME, COST, POSTED };
	private enum Order { ASC, DESC };
	
	@ModelAttribute("Drinks")
	private List<Drink> drinks (User user, 
			@RequestParam(required=false, defaultValue="NAME") Sort sort,  
			@RequestParam(required=false, defaultValue="ASC") Order order) {
		List<Drink> result;
		if (user.getRole() == Role.USER)
			result = drinkService.findAllDrinksByUser(user);
		else if (user.getRole() == Role.ADMIN)
			result = drinkService.findAllDrinksForAdmin();
		else
			result = drinkService.findAllDrinksForShopper();
		switch (sort) {
		case NAME:
			Collections.sort(result, new Comparator<Drink>() {
				public int compare(Drink t1, Drink t2) {
					return t1.getName().compareToIgnoreCase(t2.getName());
				}
			});
			break;
		case COST:
			Collections.sort(result, new Comparator<Drink>() {
				public int compare(Drink t1, Drink t2) {
					return t1.getUnitCost() - t2.getUnitCost();
				}
			});
			break;
		case POSTED:
			Collections.sort(result, new Comparator<Drink>() {
				public int compare(Drink t1, Drink t2) {
					return Boolean.compare(t1.isPosted(), t2.isPosted());
				}
			});
			break;
		}
		
		if(order == Order.DESC) {
			Collections.reverse(result);
		}

		return result;
	}
	
	@RequestMapping(value="/home/drinks/create", method=RequestMethod.POST)
	public String createDrink(
			@ModelAttribute User user,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String desc,
			@RequestParam(required=true) URL thumb,
			@RequestParam(required=true) Integer cost,
			@RequestParam(required=true) Boolean posted
			) {
		
		Drink newDrink = new Drink.Builder()
			.name(name)
			.description(desc)
			.thumbnail(thumb)
			.unitCost(cost)
			.posted(posted)
			.build();
				
		drinkService.addDrink(user, newDrink);		
		return "redirect:/user/drinks";
	}
	
	@RequestMapping(value="/home/drinks/edit", method=RequestMethod.GET)
	public String updateDrink(
			@ModelAttribute User user,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String desc,
			@RequestParam(required=true) URL thumb,
			@RequestParam(required=true) Integer cost,
			@RequestParam(required=true) Boolean posted,
			@RequestParam(required=true) String did
			) {
		Drink d = drinkService.findDrinkById(user, did);
		if (d != null) {
			d.setName(name);
			d.setDescription(desc);
			d.setThumbnail(thumb);
			d.setUnitCost(cost);
			d.setPosted(posted);
			drinkService.updateDrink(user, d);
		}
		return "redirect:/user/drinks";

	}
	
	@RequestMapping(value="/user/drinks", method=RequestMethod.GET)
	public String getUserDrinks(@ModelAttribute User user) {
		if(user == null) return "redirect:/login";
		if(user.getRole() != Role.USER) return "redirect:/home/shop";
		return "drinks";
	}
	
	@RequestMapping(value="/home/shop", method=RequestMethod.GET)
	public String getShopperDrinks(@ModelAttribute User user) {
		if(user == null) return "redirect:/login";
		return "drinks";
	}
}
