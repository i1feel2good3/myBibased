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
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *1.登录
 * 2.注销
 * Created by boboLei on 2018/4/27.
 */
@RestController
@RequestMapping(value = "bibased")
public class UserSuperController {

	@Autowired
	private UserSuperApplication userSuperApplication;

	@Autowired
	private UserPatientApplication userPatientApplication;

	@Autowired
	private UserDoctorApplication userDoctorApplication;

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv =  new ModelAndView("index");
		return mv;
	}

	/**
	 * 1.借用了UserSuper帮忙，其实登录查了所有用户的，先判断用户名是不是存在的，存在再判断密码和角色
	 * 2.如果登录成功，把用户id和角色返回到用户，存做session
	 * 3.0超管，1医生，2病人
	 * @param login
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public AjaxResponse userLogin(@RequestBody Login login, HttpSession session) {
		if (login.code == 0){
			UserSuper userSuper = userSuperApplication.findUserSuperName(login.name);
			if (StringUtils.isEmpty(userSuper)){
				return AjaxResponse.failure("请输入正确的用户名！");
			}else {
				if (userSuper.getPassWord().equals(login.pw)){
					if (userSuper.getUserRole().getCode() == 0){

						IdAndRole idAndRole = new IdAndRole(userSuper.getId(),userSuper.getUserRole().getCode());
						session.setAttribute(WebSecurityConfig.SESSION_KEY,idAndRole);

						return AjaxResponse.succss(userSuper);
					}else {
						return AjaxResponse.failure("对不起，请选择正确的用户类型");
					}
				}else {
					return AjaxResponse.failure("请输入正确的密码");
				}
			}
		}
		if (login.code == 1){
			UserDoctor userDoctor = userDoctorApplication.findUserDoctorName(login.name);
			if (StringUtils.isEmpty(userDoctor)){
				return AjaxResponse.failure("请输入正确的用户名！");
			}else {
				if (userDoctor.getPassWord().equals(login.pw)){
					if (userDoctor.getUserRole().getCode() == 1){

						IdAndRole idAndRole = new IdAndRole(userDoctor.getId(),userDoctor.getUserRole().getCode());
						session.setAttribute(WebSecurityConfig.SESSION_KEY,idAndRole);

						return AjaxResponse.succss(userDoctor);
					}else {
						return AjaxResponse.failure("对不起，请选择正确的用户类型");
					}
				}else {
					return AjaxResponse.failure("请输入正确的密码！");
				}
			}
		}
		if (login.code == 2){
			UserPatient userPatient = userPatientApplication.findUserPatientName(login.name);
			if (StringUtils.isEmpty(userPatient)){
				return AjaxResponse.failure("请输入正确的用户名 ！");
			}else {
				if (userPatient.getPassWord().equals(login.pw)){
					if (userPatient.getUserRole().getCode() == 2){
						System.out.println(userPatient.getId());

						IdAndRole idAndRole = new IdAndRole(userPatient.getId(),userPatient.getUserRole().getCode());
						session.setAttribute(WebSecurityConfig.SESSION_KEY,idAndRole);

						return AjaxResponse.succss(userPatient);
					}else {
						return AjaxResponse.failure("对不起，请选择正确的用户类型");
					}
				}else {
					return AjaxResponse.failure("请输入正确的密码！");
				}
			}
		}
		else {
			return AjaxResponse.error("未知错误");
		}
	}

	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public AjaxResponse userLogout(HttpSession session){
		IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
		System.out.println(idAndRole.getUserId()+" kahah "+idAndRole.getRoleCode());
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return AjaxResponse.succss(null);
	}

	@Data
	static class Login{
		private String name;
		private String pw;
		private Integer code;
		public Login(){}
	}
}
