package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.que.Question;
import com.emyus.que.QuestionService;

@Controller
public class ListController {

	@Autowired
	private QuestionService questionService;

	@PostMapping(value = "/list")
	public String displayList(Question question) {
		List<Question> questionlist = questionService.findAll();
		question.addAttribute("questionlist", questionlist);
		return "/list";
	}
}
