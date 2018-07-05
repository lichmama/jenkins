package com.lichmama.demo.action;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichmama.demo.common.util.StringUtil;
import com.lichmama.demo.core.annotation.CurrentUser;
import com.lichmama.demo.core.annotation.Logtag;
import com.lichmama.demo.entity.User;
import com.lichmama.demo.service.IUserService;

@Controller
public class IndexAction {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	@RequestMapping({ "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	@Logtag(module = "登录模块", operation = "用户登录")
	public String login(String username, String password, ModelMap modelMap, HttpSession session) {
		logger.debug("username: {}, password: {}", username, password);
		User user = userService.getUserByName(username);
		if (user == null) {
			modelMap.addAttribute("loginFail", "用户不存在！");
			return "index";
		}
		if (StringUtil.isNotEqual(password, user.getPassword())) {
			modelMap.addAttribute("loginFail", "账号或密码不正确！");
			return "index";
		}
		session.setAttribute("loginUser", user);
		return "redirect:/main";
	}

	@RequestMapping("/main")
	public String main(ModelMap modelMap, @CurrentUser User user) {
		modelMap.addAttribute("username", user.getRealname());
		return "main";
	}

	@RequestMapping("/logout")
	@Logtag(module = "登录模块", operation = "用户退出")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
}
