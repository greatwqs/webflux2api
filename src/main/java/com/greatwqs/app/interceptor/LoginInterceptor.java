package com.greatwqs.app.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatwqs.app.common.exception.AppException;
import com.greatwqs.app.common.exception.ErrorCode;
import com.greatwqs.app.component.RequestComponent;
import com.greatwqs.app.domain.po.UserPo;
import com.greatwqs.app.interceptor.annotation.LoginRequired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.greatwqs.app.common.AppConstants;
import com.greatwqs.app.utils.PublicUtils;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * LoginInterceptor
 *
 * @author wang.qingsong
 * Create on 2019/09/25
 */
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RequestComponent requestComponent;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod)handler;
		LoginRequired loginRequired = PublicUtils.getClazzOrMethodAnnotation(handlerMethod, LoginRequired.class);
		if (loginRequired == null) {
			return true;
		}

		final String token = getUserLoginTokenNotNull(request);
		final UserPo loginUser = null; // getUserByToken(token);
		RequestContextHolder.currentRequestAttributes()
			.setAttribute(AppConstants.REQUEST_USER, loginUser, RequestAttributes.SCOPE_REQUEST);
		RequestContextHolder.currentRequestAttributes()
			.setAttribute(AppConstants.REQUEST_USER_TOKEN, token, RequestAttributes.SCOPE_REQUEST);
		return true;
	}

	/**
	 * get user login token not null
	 * @param request
	 * @return
	 */
	private String getUserLoginTokenNotNull(HttpServletRequest request) {
		String token = getUserLoginToken(request);
		if (StringUtils.isEmpty(token)) {
			log.warn("token is em, url: " + requestComponent.getRequestUrl());
			throw new AppException(ErrorCode.UNAUTHORIZED);
		}
		return token;
	}

	/**
	 * get user login token
	 * @param request
	 * @return
	 */
	private String getUserLoginToken(HttpServletRequest request) {
		final Cookie cookie = WebUtils.getCookie(request, AppConstants.APP_LOGIN_TOKEN);
		if (cookie != null && StringUtils.isNotEmpty(cookie.getValue())) {
			return cookie.getValue();
		}
		return request.getHeader(AppConstants.APP_LOGIN_TOKEN);
	}
}
