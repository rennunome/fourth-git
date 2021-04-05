package com.emyus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emyus.auth.DatabaseUserDetailsService;
import com.emyus.auth.User;
import com.emyus.user.UserRequest;

@Controller
public class UserEditController {

	@Autowired
	DatabaseUserDetailsService userService;

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private HttpSession session;

	@PostMapping("/useredit")
	public String edit(@ModelAttribute("user_id") int user_id, @ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password, @ModelAttribute("admin_flag") byte admin_flag, @ModelAttribute("passwordconfirm") String password_confirm, Model model, HttpServletRequest request) {
		// セッション取得
		session = request.getSession(false);
		String register_check = (String) session.getAttribute("register_check");
		// 登録フラグが立ってないかを判定
		if (register_check == null ||!register_check.equals("OK")) {

			model.addAttribute("user_id", user_id);
			model.addAttribute("user_name", user_name);
			model.addAttribute("password", password);
			model.addAttribute("password_confirm", password_confirm);
			model.addAttribute("admin_flag", admin_flag);
		}
		return "/useredit";

	}

	@PostMapping("/usereditconfirm")
	public String editconfirm(@ModelAttribute("user_id") int user_id, @ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password, @ModelAttribute("admin_flag") byte admin_flag, @ModelAttribute("passwordconfirm") String password_confirm, Model model) {

		String error_message_pw = "";
		boolean is_error = false;

		//入力値に対してのバリデーション
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
		// エラーがあったらuseredit画面に遷移
		if (is_error) {
			return "/useredit";
		}

		//編集登録が成功した場合
		if (error_message_pw.isEmpty()){
			model.addAttribute("user_id", user_id);
			model.addAttribute("user_name", user_name);
			model.addAttribute("password", password);
			model.addAttribute("password_confirm", password_confirm);
			model.addAttribute("admin", admin_flag);
		}
		return "/usereditconfirm";
	}

	@PostMapping("/usereditsave")
	public String editsave(@ModelAttribute("user_id") int user_id, @ModelAttribute("user_name") String user_name, @ModelAttribute("password") String password, @ModelAttribute("admin_flag") byte admin_flag, UserRequest userRequest) {
		userRequest.setId(user_id);
		userRequest.setPassword(passwordEncoder.encode(password));
		userRequest.setAdminFlag(admin_flag);
		userRequest.setUsername(user_name);
		userService.update(userRequest);
		return "redirect:/userlist";
	}

	@RequestMapping("/userlist")
	public String userlist(Model model){
		List<User> userlist = userService.findAll();
		model.addAttribute("userlist", userlist);
		return "/userlist";
	}
}
