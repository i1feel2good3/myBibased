package com.bibased.leibobo.application.impl;

import com.bibased.leibobo.application.UserDoctorApplication;
import com.bibased.leibobo.domain.UserDoctor;
import com.bibased.leibobo.repository.UserDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
@Service
public class UserDoctorApplicationImpl implements UserDoctorApplication{

	@Autowired
	private UserDoctorRepository userDoctorRepository;

	@Override
	public UserDoctor saveUserDoctor(UserDoctor userDoctor) {
		return userDoctorRepository.save(userDoctor);
	}

	@Override
	public List<UserDoctor> findAllUserDoctor() {
		return userDoctorRepository.findAll();
	}

	@Override
	public void updatePassword(String passWord, String userName) {
		userDoctorRepository.updatePassword(passWord,userName);
	}

	@Override
	public UserDoctor findUserDoctorName(String userName) {
		return userDoctorRepository.findByUserName(userName);
	}

	@Override
	public UserDoctor getUserDoctorById(Long id) {
		return userDoctorRepository.getById(id);
	}
}
