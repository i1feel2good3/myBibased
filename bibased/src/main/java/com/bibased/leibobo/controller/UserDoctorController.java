package com.bibased.leibobo.controller;

import com.bibased.leibobo.application.UserDoctorApplication;
import com.bibased.leibobo.domain.UserDoctor;
import com.bibased.leibobo.domain.support.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1.修改或者更新医生的信息
 * Created by boboLei on 2018/5/1.
 */
@RestController
@RequestMapping(value = "bibased")
public class UserDoctorController {

	@Autowired
	private UserDoctorApplication userDoctorApplication;

	/**
	 * 1.存储或者修改医生信息
	 * @param doctor
	 * @param action
	 * @return
	 */
	@RequestMapping(value = "/doctorSaveOrUpdate",method = RequestMethod.GET)
	public AjaxResponse doctorSaveUpdate(UserDoctor doctor, String action){
		UserDoctor userDoctor;
		if (action.equals("update")){
			userDoctor = userDoctorApplication.getUserDoctorById(doctor.getId());
		}else {
			userDoctor = doctor;
		}
		userDoctorApplication.saveUserDoctor(userDoctor);
		return AjaxResponse.succss(null);
	}
}
