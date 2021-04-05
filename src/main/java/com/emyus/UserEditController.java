package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emyus.auth.DatabaseUserDetailsService;
import com.emyus.auth.User;
import com.emyus.user.UserRequest;

@Controller
public class UserEditController {

	@Autowired
	DatabaseUserDetailsService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/useredit")
	public String edit(@ModelAttribute("user_id") int user_id, @ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password, @ModelAttribute("admin_flag") byte admin_flag, Model model) {
			model.addAttribute("user_id", user_id);
			model.addAttribute("user_name", user_name);
			model.addAttribute("password", password);
			model.addAttribute("admin_flag", admin_flag);
		return "/useredit";
	}

	@PostMapping("/usereditconfirm")
	public String editconfirm(@ModelAttribute("user_id") int user_id, @ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password, @ModelAttribute("admin_flag") byte admin_flag, Model model) {
		model.addAttribute("user_id", user_id);
		model.addAttribute("user_name", user_name);
		model.addAttribute("password", password);
		model.addAttribute("admin", admin_flag);
		return "/usereditconfirm";
	}

	@PostMapping("/usereditsave")
	public String editsave(@ModelAttribute("user_id") int user_id, @ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password, @ModelAttribute("admin_flag") byte admin_flag, UserRequest userRequest) {
		userRequest.setId(user_id);
		userRequest.setPassword(passwordEncoder.encode(password));
		userRequest.setAdminFlag(admin_flag);
		userRequest.setUsername(user_name);
		userService.update(userRequest);
		return "redirect:/userlist";
	}

	@RequestMapping("/userlist")
	public String userlist(Model model){
		List<User> userlist = userService.findAll();
		model.addAttribute("userlist", userlist);
	return "/userlist";
	}
}
