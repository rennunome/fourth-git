package com.emyus;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.auth.DatabaseUserDetailsService;
import com.emyus.auth.User;
import com.emyus.his.Histories;
import com.emyus.his.HistoriesService;

@Controller
public class HistoriesController {

	@Autowired
	private HistoriesService historiesService;

	@Autowired
	private DatabaseUserDetailsService userService;

	@Autowired
	private HttpSession session;

	@PostMapping("/histories")
	public String displayHistories(Model model) {
		String id = (String)session.getAttribute("id");
		//StringでHttpセッションから取得したユーザーをintに変換
		int user_id = Integer.parseInt(id);
		List<Histories> historieslist = historiesService.findAll(user_id);
		model.addAttribute("historieslist", historieslist);
		User user = userService.getOne(id);
		model.addAttribute("username", user.getUsername());
		return "/histories";
	}
}