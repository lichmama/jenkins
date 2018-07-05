package com.lichmama.demo.core.aop;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lichmama.demo.core.annotation.Logtag;
import com.lichmama.demo.entity.OperationLog;
import com.lichmama.demo.entity.User;
import com.lichmama.demo.service.ILogService;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class OperationLogAscpect {

	@Autowired
	private ILogService logService;

	@Pointcut("@annotation(com.lichmama.demo.core.annotation.Logtag)")
	public void pointcut() {
	};

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		User loginUser;
		Map<String, Object> result;
		HttpServletRequest request = getCurrentRequest();
		if (getLoginUser(request) == null) {
			result = proceed(joinPoint);
			loginUser = getLoginUser(request);
		} else {
			loginUser = getLoginUser(request);
			result = proceed(joinPoint);
		}
		if (loginUser == null) {
			log.debug("skip over saving log because user not logined");
		} else {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Logtag tag = signature.getMethod().getAnnotation(Logtag.class);
			saveOperationLog(request, loginUser, tag, (long) result.get("usage"));
		}
		return result.get("object");
	}

	private void saveOperationLog(HttpServletRequest request, User loginUser, Logtag tag, long usage) {
		OperationLog oplog = new OperationLog();
		oplog.setUserId(loginUser.getId());
		oplog.setModule(tag.module());
		oplog.setOperation(tag.operation());
		oplog.setSource(request.getRemoteAddr());
		oplog.setUserAgent(request.getHeader("User-Agent"));
		oplog.setDevice(getDeviceType(request.getHeader("User-Agent")));
		oplog.setDescription("执行成功，共耗时" + usage + "(ms)");
		oplog.setCreateTime(new Date());
		logService.saveLog(oplog);
	}

	private String getDeviceType(String userAgent) {
		if (userAgent.contains("Windows NT"))
			return "Windows";
		if (userAgent.contains("Android"))
			return "Android";
		if (userAgent.contains("Linux"))
			return "Linux";
		if (userAgent.contains("Macintosh") || userAgent.contains("Mac OS"))
			return "Mac OS";
		if (userAgent.contains("iPhone") || userAgent.contains("iPod") || userAgent.contains("iPad"))
			return "iOS";
		return "Unknown";
	}

	private HttpServletRequest getCurrentRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	private User getLoginUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("loginUser");
	}

	private Map<String, Object> proceed(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, Object> map = new HashMap<String, Object>();
		long begin = System.currentTimeMillis();
		Object object = joinPoint.proceed();
		long end = System.currentTimeMillis();
		long usage = end - begin;
		map.put("object", object);
		map.put("usage", usage);
		return map;
	}
}
