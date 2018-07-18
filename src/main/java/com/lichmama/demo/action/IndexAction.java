package com.lichmama.demo.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.lichmama.demo.common.util.StringUtil;
import com.lichmama.demo.core.annotation.CurrentUser;
import com.lichmama.demo.core.annotation.Logtag;
import com.lichmama.demo.entity.User;
import com.lichmama.demo.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexAction {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	@Autowired
	private Producer kaptchaProducer;

	@RequestMapping({ "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	@Logtag(module = "登录模块", operation = "用户登录")
	public String login(String username, String password, String vcode, ModelMap modelMap, HttpSession session) {
		logger.debug("username: {}, password: {}, vcode: {}", username, password, vcode);
		String kaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!kaptcha.equalsIgnoreCase(vcode)) {
			modelMap.addAttribute("loginFail", "验证码不正确！");
			return "index";
		}
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

	@RequestMapping("/captcha")
	public void captcha(HttpSession session, HttpServletResponse response) {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache,must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = kaptchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bi = kaptchaProducer.createImage(capText);
		try {
			ImageIO.write(bi, "jpg", response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
