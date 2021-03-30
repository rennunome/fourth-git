package com.emyus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.ca.CARequest;
import com.emyus.ca.CAService;
import com.emyus.ca.CorrectAnswer;
import com.emyus.que.Question;
import com.emyus.que.QuestionRequest;
import com.emyus.que.QuestionService;
@Controller
public class QAEditController {


	@Autowired
	private QuestionService questionService;

	@Autowired
	private CAService CAService;

	@PostMapping("/qaedit")
	public String displayEdit(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("id") int cas_id, Model model , QuestionRequest questionRequest, CARequest caRequest) {
		Question question = questionService.findById(questionRequest);
		CorrectAnswer ca = CAService.findById(caRequest);
		model.addAttribute("question", question.getQuestion());
		model.addAttribute("answer", ca.getAnswer());
		model.addAttribute("questions_id", questions_id);
		model.addAttribute("id", cas_id);
		return "/qaedit";
	}

	@PostMapping("/qaeditconfirm")
	public String displayEditConfirm(@ModelAttribute("questions_id") String questions_id, @ModelAttribute("answer_id") String cas_id, @ModelAttribute("question") String question, @ModelAttribute("answer") String answer, Model model, QuestionRequest questionRequest, CARequest caRequest) {
		model.addAttribute("question", question);
		model.addAttribute("answer", answer);
		model.addAttribute("questions_id", questions_id);
		model.addAttribute("answer_id", cas_id);
		return "qaeditconfirm";

	}

}
