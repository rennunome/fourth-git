package com.emyus;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RootController {

	@Autowired
	private HttpSession session;

//	@Autowired
//	private QuestionService questionService;
//
//	@Autowired
//	private CAService CAService;

	@GetMapping("/")
	public String root(Model model) {
		byte adflag = (byte)session.getAttribute("admin_flag");
		model.addAttribute("admin_flag" ,adflag);
		return "index";
	}

	@PostMapping("/index")
	public String index(Model model) {
		byte adflag = (byte)session.getAttribute("admin_flag");
		model.addAttribute("admin_flag",adflag);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
//		List<Question> questionlist = questionService.findAll();
//		List<CorrectAnswer> calist = CAService.findAll();
//		model.addAttribute("questionlist", questionlist);
//		model.addAttribute("calist", calist);
		return "login";
	}

}
