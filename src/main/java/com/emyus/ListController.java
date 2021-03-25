package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.que.Question;
import com.emyus.que.QuestionService;

@Controller
public class ListController {

	@Autowired
	private QuestionService questionService;

	//@PostMappingでlist.htmlに画面遷移
	@PostMapping("/list")
	//DBから取得した全questionsをList化し、model.addAttributeでlist.htmlに渡す
	public String displayList(Model model) {
		List<Question> questionlist = questionService.findAll();
		//List<Question> calist = correctAnswerService.findAll();
		model.addAttribute("questionlist", questionlist);
		//model.addAttribute("calist", calist);
		return "/list";
	}
}
