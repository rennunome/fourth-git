package com.emyus;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
	public String mark(@ModelAttribute ("questions_id") int[] questions_id, @ModelAttribute("test_answer") String[] test_answer, Model model, Principal principal) {
		double score = 0.0;
		List<CorrectAnswer> calist = caService.findAll();
		//１つ目のfor文ループ:1つの解答欄につき同じ処理を繰り返す
		int[] question_ids = new int[questions_id.length];
		for (int i = 0; i < test_answer.length; i++) {
			//2つ目のfor文ループ:テスト問題の持っているquestions_idがcalistのどのquestions_idと一致するかを調べる（？）
			for (int j = 0; j < questions_id.length; j++) {
				question_ids[j] = getQuestionsId();
				System.out.println("A");
				//3つ目のfor文ループ:test_answerとanswerが一致していたら1ポイントゲット
				for (int k = 0; k < calist.size(); k++) {
					if (calist.get(k) != null && test_answer[i].equals(calist.get(k).getAnswer())) {
						score ++;
						System.out.println("B");
						break;
					}
				}
			}
		}
		//ユーザー情報の取得（ユーザー名）
		Authentication auth = (Authentication) principal;
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		String username = userDetails.getUsername();

		//100点満点中の得点を計算
		long total_score = Math.round(100 * score /(double)question_ids.length);
    	int total = (int) total_score;

    	//現在時刻（採点時）を取得
    	long millis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(millis);

		//総問題数・ユーザー名・正答数・得点・採点時時刻をmark.htmlに送る
		model.addAttribute("question_ids", question_ids);
		//System.out.println(question_ids.length);
		model.addAttribute("username", username);
		//System.out.println(username);
		model.addAttribute("score", (int)score);
		//System.out.println((int)score);
		model.addAttribute("total", total);
		//System.out.println(total);
		model.addAttribute("time", timestamp);

		//DBのhistoriesテーブルに結果を保存

		return "/mark";
	}

	private int getQuestionsId() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
