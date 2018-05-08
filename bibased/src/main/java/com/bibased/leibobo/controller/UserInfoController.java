package com.bibased.leibobo.controller;

import com.bibased.leibobo.application.UserDoctorApplication;
import com.bibased.leibobo.application.UserPatientApplication;
import com.bibased.leibobo.application.UserSuperApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.domain.UserDoctor;
import com.bibased.leibobo.domain.UserPatient;
import com.bibased.leibobo.domain.UserSuper;
import com.bibased.leibobo.domain.support.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 1.用户信息页面跳转
 * 2.用户个人信息
 * Created by boboLei on 2018/5/1.
 */
@RestController
@RequestMapping(value = "bibased")
public class UserInfoController extends  BaseController{

	@Autowired
	private UserDoctorApplication userDoctorApplication;

	@Autowired
	private UserSuperApplication userSuperApplication;

	@Autowired
	private UserPatientApplication userPatientApplication;

	@RequestMapping(value = "/personal/info",method = RequestMethod.GET)
	public ModelAndView toRegister(){
		ModelAndView infoMv = new ModelAndView("myInfo");
		return infoMv;
	}

	/**
	 * 1.直接使用最大的用户类，按照id查找，然后返回用户信息
	 * 2.判断用户类可以在前端进行，同时选择那些属性隐藏
	 * 3.也可以在前端用来查找当前用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/personal/roleInfo",method = RequestMethod.GET)
	public AjaxResponse getUser(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole userIdRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			//超管
			if (userIdRole.getRoleCode() == 0){
				UserSuper userSuper = userSuperApplication.getUserSuperById(userIdRole.getUserId());
				if (userSuper != null){
					return AjaxResponse.succss(userSuper);
				}else {
					return AjaxResponse.error("获取您的信息错误");
				}

			}
			//医生
			if (userIdRole.getRoleCode() == 1){
				UserDoctor userDoctor = userDoctorApplication.getUserDoctorById(userIdRole.getUserId());
				if (userDoctor != null){
					return AjaxResponse.succss(userDoctor);
				}else {
					return AjaxResponse.error("获取您的信息错误");
				}
			}
			//病人
			if (userIdRole.getRoleCode() == 2){
				UserPatient userPatient = userPatientApplication.getUserPatientById(userIdRole.getUserId());
				if (userPatient != null){
					return AjaxResponse.succss(userPatient);
				}
				return AjaxResponse.error("获取您的信息错误");
			}
			else {
				return AjaxResponse.error("获取您的信息发生未知错误");
			}
		}else {
			return AjaxResponse.failure("您还没有登录,请登录");
		}
	}

}
