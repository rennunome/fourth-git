package com.emyus;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.ca.CARequest;
import com.emyus.ca.CAService;
import com.emyus.que.QuestionRequest;
import com.emyus.que.QuestionService;
@Controller
public class QADBEditController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private CAService caService;

	@PostMapping("/qaeditconfirm2")
	public String DBregister(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("answer_id") int cas_id, @ModelAttribute("question") String question, Model model , QuestionRequest questionRequest, CARequest caRequest, HttpServletRequest request) {
		questionService.update(questionRequest);
		String[] answer = request.getParameterValues("answer");
		for (int i = 0; i < answer.length; i++) {
			    if (answer[i] != null) {
			        caRequest.setAnswer(answer[i]);
			        caService.update(caRequest);
			    }
			};
		return "redirect:/list";
	}

//	@RequestMapping("/list")
//	public String displayList(Model model) {
//		List<Question> questionlist = questionService.findAll();
//		List<CorrectAnswer> calist = caService.findAll();
//		model.addAttribute("questionlist", questionlist);
//		model.addAttribute("calist", calist);
//		return "/list";
//	}
}
