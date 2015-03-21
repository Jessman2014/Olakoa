package com.dahirkanehl.olakoa;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dahirkanehl.olakoa.users.User;
import com.dahirkanehl.olakoa.users.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam(value="username", required=true) String username, 
						@RequestParam(value="password", required=true) String password,
						HttpSession session,
						HttpServletResponse response
						) throws IOException {
		User user = userService.getUser(username, password);
		if(user != null) {
			session.setAttribute("user", user);
			return "redirect:/tasks";
		} else {
			return "redirect:/login?error=true";
		}
	}
			
	@RequestMapping(value={"/", "/login"}, method=RequestMethod.GET)
	public String login(@RequestParam(value="error", required=false, defaultValue="false") Boolean error, Model model) {
		model.addAttribute("error", error);
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user",  null);
		return "redirect:/login";
	}
}
