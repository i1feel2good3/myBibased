package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.application.UserDoctorApplication;
import com.bibased.leibobo.application.UserPatientApplication;
import com.bibased.leibobo.application.UserSuperApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.UserDoctor;
import com.bibased.leibobo.domain.UserPatient;
import com.bibased.leibobo.domain.UserSuper;
import com.bibased.leibobo.domain.support.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by boboLei on 2018/5/5.
 */
@RestController
@RequestMapping(value = "bibased")
public class UserManageController {

	@Autowired
	private UserSuperApplication userSuperApplication;
	@Autowired
	private UserPatientApplication userPatientApplication;
	@Autowired
	private UserDoctorApplication userDoctorApplication;

	@RequestMapping(value = "/user/manager",method = RequestMethod.GET)
	public ModelAndView userManager(){
		ModelAndView userManager = new ModelAndView("userManage");
		return userManager;
	}

	/**
	 * 1.查询超级管理员
	 * 2.//使用函数式编程过滤
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/userManager/super",method = RequestMethod.GET)
	public AjaxResponse querySuper(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			List<UserSuper> allSupers;
			allSupers = userSuperApplication.findAllUserSuper();
			allSupers = allSupers.stream().filter(x -> x.getUserRole().getCode() == 0).collect(Collectors.toList());
			return AjaxResponse.successed(allSupers,idAndRole.getRoleCode());
		}else {
			return AjaxResponse.failure("请登录");
		}
	}

	@RequestMapping(value = "/userManager/patient",method = RequestMethod.GET)
	public AjaxResponse queryPatient(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			List<UserPatient> allPatients;
			allPatients = userPatientApplication.findAllUserPatient();
			allPatients = allPatients.stream().filter(x -> x.getUserRole().getCode() == 2).collect(Collectors.toList());
			return AjaxResponse.succss(allPatients);
		}else {
			return AjaxResponse.failure("请登录");
		}
	}

	@RequestMapping(value = "/userManager/doctor",method = RequestMethod.GET)
	public AjaxResponse queryDoctor(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			List<UserDoctor> allDoctors;
			allDoctors = userDoctorApplication.findAllUserDoctor();
			allDoctors = allDoctors.stream().filter(x -> x.getUserRole().getCode() == 1).collect(Collectors.toList());
			return AjaxResponse.succss(allDoctors);
		}else {
			return AjaxResponse.failure("请登录");
		}
	}
}
