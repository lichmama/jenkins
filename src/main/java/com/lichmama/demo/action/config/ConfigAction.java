package com.lichmama.demo.action.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichmama.demo.common.constant.ActionMessage;
import com.lichmama.demo.common.constant.ActionStatus;
import com.lichmama.demo.common.util.ConfigUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/config")
@Api(value = "配置管理接口", tags = { "配置信息管理" })
@Slf4j
public class ConfigAction {

	@PostMapping("/set")
	@ApiOperation(value = "更改或新增配置信息")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "系统错误"), @ApiResponse(code = 0, message = "成功") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "key", value = "键", paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "value", value = "值", paramType = "form", dataType = "string") })
	public ActionMessage setConfig(String key, String value) {
		log.debug("key: {}, value: {}", key, value);
		ConfigUtil.setConfig(key, value);
		return ActionStatus.success();
	}

	@GetMapping("/get")
	@ApiOperation(value = "获取配置信息")
	@ApiImplicitParam(name = "key", value = "键", paramType = "query", dataType = "string")
	public Map<String, Object> getConfig(String key) {
		Map<String, Object> map = new HashMap<>();
		Object value = ConfigUtil.getConfig(key);
		map.put(key, value);
		return map;
	}
}
