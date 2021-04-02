package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.auth.DatabaseUserDetailsService;
import com.emyus.auth.User;
import com.emyus.user.UserRequest;

@Controller
public class UserRegisterController {

	@Autowired
	private DatabaseUserDetailsService userService;

	@PostMapping("/userregister")
	public String display() {
		return "/userregister";
	}

	@PostMapping("/userconfirm")
	public String register(@ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password ,@ModelAttribute("admin") String admin, Model model) {
		model.addAttribute("user_name", user_name);
		model.addAttribute("password", password);
		model.addAttribute("admin", admin);
		return "/userconfirm";
	}

	@PostMapping("/user_register")
	public String dbregister(@ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password ,@ModelAttribute("admin") String admin, Model model, UserRequest userRequest) {
		userRequest = new UserRequest();
		if(admin.isEmpty()) {
			byte admin_flag = 0;
			userRequest.setUsername(user_name);
			userRequest.setPassword(password);
			userRequest.setAdminFlag(admin_flag);
			userService.create(userRequest);
		}
		else{
			byte admin_flag = 1;
			userRequest.setUsername(user_name);
			userRequest.setPassword(password);
			userRequest.setAdminFlag(admin_flag);
			userService.create(userRequest);
		}
		return "redirect:/userlist";
	}

	@GetMapping("/userlist")
	public String userlist(Model model){
		List<User> userlist = userService.findAll();
		model.addAttribute("userlist", userlist);
	return "/userlist";
	}
}
