package com.bibased.leibobo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by boboLei on 2018/5/3.
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{

	public static final String SESSION_KEY = "useridAndRole";

	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

		// 排除配置
		addInterceptor.excludePathPatterns("bibased/login");
		addInterceptor.excludePathPatterns("bibased/register");

		// 拦截配置
		addInterceptor.addPathPatterns("bibased/**");
	}
}
