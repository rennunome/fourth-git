package com.emyus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		questionRequest.setId(questions_id);
		Question question = questionService.findById(questionRequest);
		List<CorrectAnswer> calist = CAService.findByQuestionId(questionRequest.getId());
		model.addAttribute("question", question.getQuestion());
		model.addAttribute("questions_id", questions_id);
		model.addAttribute("calist",calist);
		return "/qaedit";
	}

	@PostMapping("/qaeditconfirm")
	public String displayEditConfirm(@ModelAttribute("questions_id") String questions_id, @ModelAttribute("question") String question, Model model, QuestionRequest questionRequest, CARequest caRequest, HttpServletRequest request) {
		String[] answer = request.getParameterValues("answer");
		String[] cas_id = request.getParameterValues("answer_id");
		model.addAttribute("question", question);
		model.addAttribute("answer", answer);
		model.addAttribute("questions_id", questions_id);
		model.addAttribute("answer_id", cas_id);
		questionRequest.setId(Integer.parseInt(questions_id));
		List<CorrectAnswer> calist = CAService.findByQuestionId(questionRequest.getId());
		model.addAttribute("calist",calist);
		return "qaeditconfirm";

	}

}
