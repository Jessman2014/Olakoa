/** Handles requests to the olakoa drink shop for
 *  all users. Gives USER roles the ability to create, update,
 *  and sort drinks. Gives SHOPPER roles the ability to
 *  see a filtered list of drinks. Gives ADMIN roles the ability
 *  to see all drinks. 
 * @author Jesse Dahir-Kanehl
 */

package com.dahirkanehl.olakoa.drinks;

import java.io.IOException;
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
	
	@ModelAttribute("drinks")
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
	
	@ModelAttribute("detail")
	private Drink detail (User user, @RequestParam(required=false, defaultValue="") String did) {
		Drink d = null;
		if(!did.equals(""))
			d = drinkService.findDrinkById(user, did);
		return d;
	}
	
	@RequestMapping(value={"/home/detail"}, method=RequestMethod.GET)
	public String getDrink(@ModelAttribute User user, @RequestParam(required=true) String did) {
		if(user == null) return "redirect:/login";
		if(user.getRole() != Role.USER) return "redirect:/home/shop";
		return "detail";
	}
	
	@RequestMapping(value="/home/drinks/edit", method=RequestMethod.GET)
	public String updateDrink(
			@ModelAttribute User user,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String desc,
			@RequestParam(required=true) URL thumb,
			@RequestParam(required=true) Integer cost,
			@RequestParam(required=false, defaultValue="false") Boolean posted,
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
	
	@RequestMapping(value="/home/drinks/create", method=RequestMethod.POST)
	public String createDrink(
			@ModelAttribute User user,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String desc,
			@RequestParam(required=true) String thumb,
			@RequestParam(required=true) Integer cost,
			@RequestParam(required=false, defaultValue="false") Boolean posted
			) throws IOException {
		URL url = new URL(thumb);
		
		Drink newDrink = new Drink.Builder()
			.ownerId(user.getId())
			.name(name)
			.description(desc)
			.thumbnail(url)
			.unitCost(cost)
			.posted(posted)
			.build();
				
		drinkService.addDrink(user, newDrink);	
		return "redirect:/user/drinks";
	}
	
	@RequestMapping(value="/home/drinks/create", method=RequestMethod.GET)
	public String createNewDrink(
			@ModelAttribute User user
			) {
		if(user == null) return "redirect:/login";
		return "new";
	}
	
	@RequestMapping(value={"/user/drinks", "/home/drinks"}, method=RequestMethod.GET)
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
