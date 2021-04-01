package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.auth.DatabaseUserDetailsService;
import com.emyus.auth.User;

@Controller
public class UserListController {

	@Autowired
	DatabaseUserDetailsService userService;

	@PostMapping("/userlist")
	public String userlist(Model model){
		List<User> userlist = userService.findAll();
		model.addAttribute("userlist", userlist);
	return "/userlist";
	}
}
