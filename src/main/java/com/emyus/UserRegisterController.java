package com.emyus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emyus.auth.DatabaseUserDetailsService;
import com.emyus.auth.User;
import com.emyus.user.UserRequest;

@Controller
public class UserRegisterController {

	@Autowired
	private DatabaseUserDetailsService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private HttpSession session;

	@PostMapping("/userregister")
	public String display() {
		return "/userregister";
	}

	@PostMapping("/userconfirm")
	public String register(@ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password ,@ModelAttribute("admin") String admin, @ModelAttribute("passwordconfirm") String password_confirm, Model model, @Validated UserRequest userRequest) {
		String error_message_un = "";
		String error_message_pw = "";
		boolean is_error = false;

		//入力値に対してのバリデーション
		//ユーザー名のみが半角英数字入力ではない場合
		if (user_name != null && !user_name.matches("^[A-Za-z0-9]+$")) {
			error_message_un = "ユーザー名は半角英数字で入力してください。";
			model.addAttribute("error_message_un", error_message_un);
			is_error = true;
		}
		//PWが8文字以下、半角英数字入力ではない場合
		if (password != null && password.length() < 8 || !password.matches("^[A-Za-z0-9]+$")){
			error_message_pw = "パスワードは半角英数字・8文字以上で入力してください。";
			model.addAttribute("error_message_pw", error_message_pw);
			is_error = true;
		}
		if (!password.equals(password_confirm)) {
			error_message_pw ="パスワードとパスワード（確認）は一致する必要があります。";
			model.addAttribute("error_message_pw", error_message_pw);
			is_error = true;
		}
		// エラーがあったらuserregister画面に遷移
		if (is_error) {
			return "/userregister";
		}

		//新規登録成功した場合
		if (error_message_un.isEmpty() && error_message_pw.isEmpty()){
			model.addAttribute("user_name", user_name);
			model.addAttribute("password", password);
			model.addAttribute("admin", admin);
		}

		return "/userconfirm";
	}
	@PostMapping("/user_register")
	public String dbregister(@ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password ,@ModelAttribute("admin") String admin, Model model, UserRequest userRequest, HttpServletRequest request) {
		// セッション取得
		session = request.getSession(false);
		String register_check = (String) session.getAttribute("register_check");
		// 登録フラグを設定
		register_check = "OK";
		session.setAttribute("register_check", register_check);

		userRequest = new UserRequest();
		if(admin.equals("なし")) {
			byte admin_flag = 0;
			userRequest.setUsername(user_name);
			userRequest.setPassword(passwordEncoder.encode(password));
			userRequest.setAdminFlag(admin_flag);
			userService.create(userRequest);
		}
		else{
			byte admin_flag = 1;
			userRequest.setUsername(user_name);
			userRequest.setPassword(passwordEncoder.encode(password));
			userRequest.setAdminFlag(admin_flag);
			userService.create(userRequest);
		}
		return "redirect:/userlist";
	}

	@GetMapping("/userlist")
	public String userlist(Model model){
		List<User> userlist = userService.findAll();
		model.addAttribute("userlist", userlist);
		return "/userlist";
	}
}
