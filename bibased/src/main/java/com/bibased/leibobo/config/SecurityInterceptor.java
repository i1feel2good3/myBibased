package com.bibased.leibobo.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by boboLei on 2018/5/3.
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null)
			return true;

		// 跳转登录
		String url = "bibased/index";
		response.sendRedirect(url);
		return false;
	}
}
