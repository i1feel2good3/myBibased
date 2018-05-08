package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.PatientModel;
import com.bibased.leibobo.application.UserPatientApplication;
import com.bibased.leibobo.domain.UserPatient;
import com.bibased.leibobo.domain.support.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1.注册页面
 * 2.修改患者密码
 * 3.更新或者保存（注册用）患者数据
 * Created by boboLei on 2018/5/1.
 */
@RestController
@RequestMapping(value = "bibased")
public class UserPatientController {

	@Autowired
	private UserPatientApplication userPatientApplication;

	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public ModelAndView toRegister(){
		ModelAndView registerMv = new ModelAndView("register");
		return registerMv;
	}

	/**
	 * 1.修改患者的密码，医生和超管密码不可改
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "updatePassWord",method = RequestMethod.GET)
	public AjaxResponse updatePassWord(String userName, String passWord){
		userPatientApplication.updatePassword(passWord,userName);
		return AjaxResponse.succss(null);
	}

	/**
	 * 1 只有患者可以注册
	 * 2 管理员暂时通过直接操作数据库来
	 * 3 不知道包含了父实体的类，前端如何传值，对象不一致可以解析吗
	 * @return
	 * @param patient
	 */
	@RequestMapping(value = "/patientUpdate",method = RequestMethod.POST)
	public AjaxResponse patientSaveUpdate(UserPatient patient){
		UserPatient userPatient;
		userPatient = userPatientApplication.getUserPatientById(patient.getId());
		userPatientApplication.saveUserPatient(userPatient);
		return AjaxResponse.succss(null);
	}

	/**
	 * @param patientModel
	 * @return
	 */
	@RequestMapping(value = "patientSave",method = RequestMethod.POST)
	public AjaxResponse patientSave(@RequestBody PatientModel patientModel){
		UserPatient patientSave = new UserPatient(patientModel.getUserName(),patientModel.getRealName(),
				patientModel.getPassWord(),patientModel.getMobilePhone(),patientModel.getIdType(),
				patientModel.getIdNumber(),patientModel.getAge(),patientModel.getEmail(),
				patientModel.getAddress());
		userPatientApplication.saveUserPatient(patientSave);
		return AjaxResponse.succss(patientSave);
	}

}
