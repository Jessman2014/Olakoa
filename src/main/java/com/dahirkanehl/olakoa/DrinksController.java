package com.dahirkanehl.olakoa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DrinksController {
	
	@RequestMapping(value = "/user/drinks", method = RequestMethod.GET) 
	public String userDrinks () {
		
	}
}
