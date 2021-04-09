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
		System.out.println(answer.length);
		List<CorrectAnswer> c_alist = CAService.findByQuestionId(questions_id);
		System.out.println(c_alist.size());
		for(int i = 0; i < c_alist.size(); i++) {
			for(int j=0; j <answer_id.length; j++) {
				System.out.println("Answer_id =" + answer_id[j]);
				if(Integer.parseInt(answer_id[j]) == c_alist.get(i).getId()) {
					caRequest.setId(Integer.parseInt(answer_id[j]));
					caRequest.setAnswer(answer[i]);
					CAService.update(caRequest);
				} else {
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
