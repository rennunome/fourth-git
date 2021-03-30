package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emyus.ca.CARequest;
import com.emyus.ca.CAService;
import com.emyus.ca.CorrectAnswer;
import com.emyus.que.Question;
import com.emyus.que.QuestionRequest;
import com.emyus.que.QuestionService;

@Controller
public class QADeleteController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private CAService CAService;

	@PostMapping("/qadeleteconfirm")
	public String deleteconfirm(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("id") int cas_id, Model model , QuestionRequest questionRequest, CARequest caRequest) {
		Question question = questionService.findById(questionRequest);
		CorrectAnswer ca = CAService.findById(caRequest);
		model.addAttribute("question", question.getQuestion());
		model.addAttribute("answer", ca.getAnswer());
		model.addAttribute("questions_id", questions_id);
		model.addAttribute("id", cas_id);
		return "/qadeleteconfirm";
	}

	@PostMapping("/qadelete")
	public String delete(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("answer_id") int cas_id, Model model , QuestionRequest questionRequest, CARequest caRequest) {
		System.out.println("0");
		System.out.println(questions_id);
		System.out.println(cas_id);
		System.out.println("1");
		questionService.delete(questionRequest);
		CAService.delete(caRequest);
		System.out.println("2");
		return "redirect:/list";
	}

	@RequestMapping(value = "display", params = "displaylist", method = RequestMethod.POST)
	public String displayList(Model model) {
		List<Question> questionlist = questionService.findAll();
		List<CorrectAnswer> calist = CAService.findAll();
		model.addAttribute("questionlist", questionlist);
		model.addAttribute("calist", calist);
		return "/list";
	}
}
