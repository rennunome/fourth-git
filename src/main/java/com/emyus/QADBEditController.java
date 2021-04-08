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
	public String DBregister(@ModelAttribute("questions_id") int questions_id, @ModelAttribute("question") String question, Model model , QuestionRequest questionRequest, CARequest caRequest, HttpServletRequest request) {
		questionRequest.setId(questions_id);
		questionService.update(questionRequest);
		String[] answer = request.getParameterValues("answer");
		String[] answer_id = request.getParameterValues("answer_id");
		//取得しているidの数は2
		System.out.println(answer_id.length);
		int[] cas_id = new int[answer_id.length];
		for(int i = 0; i < answer_id.length; i++) {
			//それぞれint化してcorrect_answersのidにセット
			int value = Integer.parseInt(answer_id[i]);
	    	cas_id[i] = value;
	    	caRequest.setId(cas_id[i]);
		}
		for (int i = 0; i < answer.length; i++) {
			    if (answer[i] != null) {
			        caRequest.setAnswer(answer[i]);
			        CAService.update(caRequest);
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
