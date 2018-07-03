package com.lichmama.demo.core.spring;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.lichmama.demo.common.util.StringUtil;
import com.lichmama.demo.core.annotation.CurrentUser;

/**
 * 用户参数解析器：对Controller方法增加@CurrentUser的支持
 * @author kongdl@asiainfo-sec.com
 *
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
	
	private String userTypeClass;

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return webRequest.getAttribute("loginUser", RequestAttributes.SCOPE_SESSION);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {
			String className = parameter.getParameterType().getCanonicalName();
			if (className.equals(userTypeClass))
				return true;
		}
		return false;
	}

	public String getUserTypeClass() {
		return userTypeClass;
	}

	public void setUserTypeClass(String userTypeClass) {
		if (StringUtil.isEmpty(userTypeClass))
			throw new IllegalArgumentException("参数错误，不能设置userTypeClass为空");
		this.userTypeClass = userTypeClass;
	}
}
