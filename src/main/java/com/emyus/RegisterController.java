package com.emyus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

	@PostMapping("/qaregister")
	public String qaregister() {
		return "qaregister";
	}

	@PostMapping("/qaregconfirm")
	public String qaregconfirm(@ModelAttribute("Question") String question, @ModelAttribute("answer") String answer, Model model) {
		model.addAttribute(question, "Question");
		model.addAttribute(answer, "answer");
		return "qaregconfirm";
	}
}
