package com.emyus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.ca.CARequest;
import com.emyus.ca.CAService;
import com.emyus.ca.CorrectAnswer;
import com.emyus.que.Question;
import com.emyus.que.QuestionRequest;
import com.emyus.que.QuestionService;

public class QADBEditController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private CAService CAService;

	@PostMapping("/qaeditconfirm")
	//DBから取得した全questionsをList化し、model.addAttributeでlist.htmlに渡す
	public String displayEdit(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("id") int cas_id, @ModelAttribute("question") String question, @ModelAttribute("answer") String answer, Model model , QuestionRequest questionRequest, CARequest caRequest) {
		Question q = questionService.update(questionRequest);
		CorrectAnswer ca = CAService.update(caRequest);
		return "redirect:/list";
	}
}
