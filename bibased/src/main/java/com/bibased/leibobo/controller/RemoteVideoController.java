package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.support.AjaxResponse;
import javafx.beans.value.WeakChangeListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by boboLei on 2018/6/7.
 */
@RestController
@RequestMapping(value = "bibased")
public class RemoteVideoController {

	@RequestMapping(value = "/remote/video",method = RequestMethod.GET)
	public ModelAndView remoteVideo(){
		ModelAndView modelAndView = new ModelAndView("remoteVideo");
		return modelAndView;
	}

	@RequestMapping(value = "/remote/video/getYourOrders",method = RequestMethod.GET)
	public AjaxResponse getYourOrder(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			System.out.println(idAndRole.getRoleCode()+"   "+idAndRole.getUserId());
			return AjaxResponse.successed(null,idAndRole);
		}else {
			return AjaxResponse.failure("shibai");
		}
	}

}
