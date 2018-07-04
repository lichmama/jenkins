package com.lichmama.demo.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lichmama.demo.common.constant.ActionMessage;
import com.lichmama.demo.common.constant.ActionStatus;
import com.lichmama.demo.common.util.ConfigUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/config")
@Slf4j
public class ConfigAction {

	@RequestMapping("/set")
	@ResponseBody
	public ActionMessage setConfig(@RequestParam String key, @RequestParam String value) {
		log.debug("key: {}, value: {}", key, value);
		ConfigUtil.setConfig(key, value);
		return ActionStatus.success();
	}

	@RequestMapping("/get")
	@ResponseBody
	public String getConfig(@RequestParam String key) {
		String value = ConfigUtil.getString(key);
		log.debug("key: {}, value: {}", key, value);
		return value;
	}
}
