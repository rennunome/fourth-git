package com.emyus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.ca.CARequest;
import com.emyus.ca.CAService;
import com.emyus.ca.CorrectAnswer;
import com.emyus.que.Question;
import com.emyus.que.QuestionRequest;
import com.emyus.que.QuestionService;

@Controller
public class QADBRegisterController {

	@Autowired
	QuestionService questionService;
	@Autowired
	CAService caService;

	@PostMapping("/qadbregister")
	public String qadbregister(QuestionRequest questionRequest, CARequest caRequest, HttpServletRequest request) {
		String[] answer = request.getParameterValues("answer");
		for (int i = 0; i < answer.length; i++) {
			    if (answer[i] != null) {
			        caRequest.setAnswer(answer[i]);
			        caService.create(caRequest);
			    }
			}
		questionService.create(questionRequest);
		return "redirect:/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<Question> questionlist = questionService.findAll();
		List<CorrectAnswer> calist = caService.findAll();
		model.addAttribute("questionlist", questionlist);
		model.addAttribute("calist", calist);
		return "/list";
	}
}
