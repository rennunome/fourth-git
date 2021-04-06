package com.emyus;

import javax.servlet.http.HttpServletRequest;

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
	public String qaregconfirm(@ModelAttribute("Question") String question, Model model, HttpServletRequest request) {
		String[] answer = request.getParameterValues("answer");
		String error_message_question = "";
		String error_message_answer = "";

		if (question != null && question.length() > 500) {
			for (int i = 0; i < answer.length; i++) {
				if (answer[i] != null && answer[i].length() > 200) {
					error_message_question = "500文字以上は入力できません。";
					error_message_answer = "200文字以上は入力できません。";
				}
			}
			model.addAttribute("error_message_question", error_message_question);
			model.addAttribute("error_message_answer", error_message_answer);
			return "/qaregister";
		}

		if(question != null && question.length() > 500) {
			error_message_question = "500文字以上は入力できません。";
			model.addAttribute("error_message_question", error_message_question);
			return "/qaregister";
		}
		if (answer != null) {
			for (int i = 0; i < answer.length; i++) {
				if (answer[i] != null && answer[i].length() > 200) {
					error_message_answer = "200文字以上は入力できません。";
					model.addAttribute("error_message_answer", error_message_answer);
					return "/qaregister";
				}
			}
		}
		model.addAttribute("Question", question);
		model.addAttribute("answer", answer);
		return "qaregconfirm";
	}
}
