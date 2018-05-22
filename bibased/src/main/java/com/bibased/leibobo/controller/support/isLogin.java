package com.bibased.leibobo.controller.support;
import com.bibased.leibobo.config.WebSecurityConfig;
import javax.servlet.http.HttpSession;

/**
 * Created by boboLei on 2018/5/21.
 */
public final class isLogin {

	public static Boolean Logined(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			return true;
		}else {
			return false;
		}
	}
}
