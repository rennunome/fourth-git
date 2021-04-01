package com.emyus;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.auth.DatabaseUserDetailsService;
import com.emyus.auth.User;
import com.emyus.ca.CAService;
import com.emyus.ca.CorrectAnswer;
import com.emyus.his.HistoriesRequest;
import com.emyus.his.HistoriesService;
import com.emyus.que.Question;
import com.emyus.que.QuestionService;

@Controller
public class TestController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private CAService caService;

	@Autowired
	private DatabaseUserDetailsService userService;

	@Autowired
	HistoriesService historiesService;

	@Autowired
	HttpSession session;

	@PostMapping("/test")
	public String displayTest(Model model) {
		List<Question> questionlist = questionService.findAllRandom();
		model.addAttribute("questionlist", questionlist);
		return "/test";
	}

	@PostMapping("/mark")
	public String mark(HttpServletRequest request,
			Model model, Principal principal, HistoriesRequest historiesRequest, ModelMap modelMap) {
		String[] test_answers = request.getParameterValues("test_answer");
		String[] question_id = request.getParameterValues("question_id");

		double score = 0.0;
		List<CorrectAnswer> calist = new ArrayList<CorrectAnswer>();
		//１つ目のfor文ループ:1つの解答欄につき同じ処理を繰り返す
		for (int i = 0; i < test_answers.length; i++) {
			//2つ目のfor文ループ:テスト問題の持っているquestions_idがcalistのどのquestions_idと一致するかを調べる（？）
			for (int j = 0; j < question_id.length; j++) {
				calist = caService.findByQuestionId(Integer.parseInt(question_id[j]));
				//3つ目のfor文ループ:test_answerとanswerが一致していたら1ポイントゲット
				for (int k = 0; k < calist.size(); k++) {
					if (calist.get(k) != null && test_answers[i].equals(calist.get(k).getAnswer())) {
						score ++;
						break;
					}
				}
			}
		}

		//ユーザー情報の取得（ユーザー名）
		String id = (String)session.getAttribute("id");
		//StringでHttpセッションから取得したユーザーをintに変換
		int user_id = Integer.parseInt(id);
		//IDをもとにユーザー情報を取得
		User user = userService.getOne(id);
		modelMap.addAttribute("user", user);

		//100点満点中の得点を計算
		long total_score = Math.round(100 * score /(double)question_id.length);
    	int total = (int) total_score;

    	//現在時刻（採点時）を取得
    	long millis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(millis);

		//総問題数・ユーザー名・正答数・得点・採点時時刻をmark.htmlに送る
		model.addAttribute("question_ids", question_id.length);
		model.addAttribute("score", (int)score);
		model.addAttribute("total", total);
		model.addAttribute("time", timestamp);

		//DBのhistoriesテーブルに結果を保存
		historiesRequest.setUserId(user_id);
		historiesRequest.setPoint(total);
		historiesRequest.setCreatedAt(timestamp);
		historiesService.create(historiesRequest);

		return "/mark";
	}

	private int[] getParameterValues(String string) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
