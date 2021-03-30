package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emyus.ca.CARequest;
import com.emyus.ca.CAService;
import com.emyus.ca.CorrectAnswer;
import com.emyus.que.Question;
import com.emyus.que.QuestionRequest;
import com.emyus.que.QuestionService;
@Controller
public class QADBEditController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private CAService CAService;

	@PostMapping("/qaeditconfirm2")
	public String DBregister(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("answer_id") int cas_id, @ModelAttribute("question") String question, @ModelAttribute("answer") String answer, Model model , QuestionRequest questionRequest, CARequest caRequest) {
		questionRequest.setId(questions_id);
		caRequest.setId(cas_id);
		Question q = questionService.update(questionRequest);
		CorrectAnswer ca = CAService.update(caRequest);
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public String displayList(Model model) {
		List<Question> questionlist = questionService.findAll();
		List<CorrectAnswer> calist = CAService.findAll();
		model.addAttribute("questionlist", questionlist);
		model.addAttribute("calist", calist);
		return "/list";
	}
}
