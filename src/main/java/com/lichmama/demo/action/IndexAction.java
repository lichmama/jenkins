package com.lichmama.demo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lichmama.demo.common.util.StringUtil;
import com.lichmama.demo.entity.User;
import com.lichmama.demo.service.IUserService;

@Controller
public class IndexAction {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	@RequestMapping({ "/", "/index" })
	public String index(ModelMap modelMap, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		if (user == null)
			return "login";
		modelMap.addAttribute("username", user.getUsername());
		modelMap.addAttribute("sessionId", session.getId());
		return "index";
	}

	@RequestMapping("/login")
	public String login(String username, String password, ModelMap modelMap, HttpSession session) {
		logger.debug("username: {}", username);
		logger.debug("password: {}", password);
		User user = userService.getUserByName(username);
		if (user == null) {
			modelMap.addAttribute("failReason", "user not exists");
			return "loginFailed";
		}
		if (StringUtil.isNotEqual(password, user.getPassword())) {
			modelMap.addAttribute("failReason", "username or password is incorrect");
			return "loginFailed";
		}
		session.setAttribute("loginUser", user);
		return "redirect:/index";
	}

	@RequestMapping("/addUser")
	public @ResponseBody Object addUser(@RequestBody User user) {
		userService.addUser(user);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("text", "success");
		return result;
	}
	
	@RequestMapping("/testRedisClient")
	public @ResponseBody Object testRedisClient() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("text", "success");
		return result;
	}
}
