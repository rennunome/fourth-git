package com.emyus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.ca.CAService;
import com.emyus.que.QuestionService;

@Controller
public class QADBRegisterController {

	@Autowired
	QuestionService questionService;

	//@Autowired
	//QuestionRequest questionRequest;

	@Autowired
	CAService caService;

	//@Autowired
	//CARequest caRequest;

	@PostMapping("/qadbregister")
	public void qadbregister() {
		questionService.create(questionRequest);
		caService.create(caRequest);
	}

	@GetMapping("/list")
	public String list() {
		return "list";
	}
}
