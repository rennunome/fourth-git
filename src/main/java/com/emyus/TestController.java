package com.emyus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.ca.CAService;
import com.emyus.ca.CorrectAnswer;
import com.emyus.que.Question;
import com.emyus.que.QuestionService;

@Controller
public class TestController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private CAService caService;

	@PostMapping("/test")
	public String displayTest(Model model) {
		List<Question> questionlist = questionService.findAllRandom();
		model.addAttribute("questionlist", questionlist);
		return "/test";
	}

	@PostMapping("/mark")
	public String mark(@ModelAttribute ("questions_id") int[] questions_id, @ModelAttribute("test_answer") String[] test_answer) {
		double score = 0.0;
		List<CorrectAnswer> calist = caService.findAll();
		//１つ目のfor文ループ:1つの解答欄につき同じ処理を繰り返す
		int[] question_ids = new int[questions_id.length];
		for (int i = 0; i < test_answer.length; i++) {
			//2つ目のfor文ループ:テスト問題の持っているquestions_idがcalistのどのquestions_idと一致するかを調べる（？）
			for (int j = 0; j < questions_id.length; j++) {
				//question_ids[j] = Integer.parseInt(questions_id[j]);
				question_ids[j] = ((CorrectAnswer) calist).getQuestionsId();
				//3つ目のfor文ループ:test_answerとanswerが一致していたら1ポイントゲット
				for (int k = 0; k < calist.size(); k++) {
					if (calist.get(k) != null && test_answer[i].equals(calist.get(k).getAnswer())) {
						score ++;
						break;
					}
				}
			}
		}
		System.out.println(score);

		return "/mark";
	}
}
