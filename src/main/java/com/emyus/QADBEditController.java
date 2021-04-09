package com.emyus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public String DBregister(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("answer_id") String answer_id, @ModelAttribute("question") String question, Model model , QuestionRequest questionRequest, CARequest caRequest, HttpServletRequest request) {
		questionRequest.setId(questions_id);
		questionService.update(questionRequest);
		String[] answer = request.getParameterValues("answer");
		String[] answer_ids = request.getParameterValues("answer_id");
		//答えの数だけループ
		for(int i = 0; i <answer.length; i++) {
			System.out.println(answer_ids[i]);
			if (answer_ids[i] != null) {
				System.out.println("A");
				caRequest.setId(Integer.parseInt(answer_ids[i]));
				if (answer[i] != null) {
					System.out.println("B");
					caRequest.setAnswer(answer[i]);
					CAService.update(caRequest);
			} else {
					System.out.println("C");
					caRequest.setAnswer(answer[i]);
					CAService.create(caRequest);
				}
			}
		}
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
