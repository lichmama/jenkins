package com.lichmama.demo.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lichmama.demo.common.constant.ActionMessage;
import com.lichmama.demo.common.constant.ActionStatus;
import com.lichmama.demo.entity.User;
import com.lichmama.demo.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private IUserService userService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping("/find/{id}")
	@ResponseBody
	public ActionMessage getUser(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		return ActionStatus.success(user);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ActionMessage addUser(@RequestBody User user) {
		userService.addUser(user);
		return ActionStatus.success();
	}

	@RequestMapping("/update")
	@ResponseBody
	public ActionMessage updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return ActionStatus.success();
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public ActionMessage deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return ActionStatus.success();
	}
}
