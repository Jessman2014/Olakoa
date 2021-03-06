/** Allows all enabled users to log in and routes
 * them based on their roles. Previously logged in
 * users will not have to log in again if session
 * is saved.
 * @author Jesse Dahir-Kanehl
 */

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

import com.dahirkanehl.olakoa.users.Role;
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
		if(user != null && user.isEnabled()) {
			session.setAttribute("user", user);
			if (user.getRole() == Role.USER)
				return "redirect:/user/drinks";
			else
				return "redirect:/home/shop";
		} else {
			return "redirect:/login?error=true";
		}
	}
			
	@RequestMapping(value={"/", "/login"}, method=RequestMethod.GET)
	public String login(
			HttpSession session,
			@RequestParam(value="error", required=false, defaultValue="false") Boolean error
			, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null || !user.isEnabled()) {
			model.addAttribute("error", error);
			return "login";
		}
		if (user.getRole() == Role.USER)
			return "redirect:/user/drinks";
		return "redirect:/home/shop";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user",  null);
		return "redirect:/login";
	}
}
