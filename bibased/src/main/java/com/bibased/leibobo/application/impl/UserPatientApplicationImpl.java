package com.bibased.leibobo.application.impl;

import com.bibased.leibobo.application.UserPatientApplication;
import com.bibased.leibobo.domain.UserPatient;
import com.bibased.leibobo.repository.UserPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
@Service
public class UserPatientApplicationImpl implements UserPatientApplication {
	@Autowired
	private UserPatientRepository userPatientRepository;

	@Override
	public UserPatient saveUserPatient(UserPatient userPatient) {
		return userPatientRepository.save(userPatient);
	}

	@Override
	public List<UserPatient> findAllUserPatient() {
		return userPatientRepository.findAll();
	}

	@Override
	public void updatePassword(String passWord, String userName) {
		userPatientRepository.updatePassword(passWord,userName);
	}

	@Override
	public UserPatient findUserPatientName(String userName) {
		return userPatientRepository.findByUserName(userName);
	}

	@Override
	public UserPatient getUserPatientById(Long id) {
		return userPatientRepository.getById(id);
	}
}
