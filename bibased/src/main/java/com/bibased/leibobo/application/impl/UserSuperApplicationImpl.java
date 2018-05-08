package com.bibased.leibobo.application.impl;

import com.bibased.leibobo.domain.UserSuper;
import com.bibased.leibobo.application.UserSuperApplication;
import com.bibased.leibobo.repository.UserSuperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by boboLei on 2018/4/27.
 */
@Service
public class UserSuperApplicationImpl implements UserSuperApplication{

	@Autowired
	private UserSuperRepository userSuperRepository;

	@Override
	public UserSuper saveUserSuper(UserSuper userSuper) {
		return userSuperRepository.save(userSuper);
	}

	@Override
	public List<UserSuper> findAllUserSuper() {
		return userSuperRepository.findAll();
	}

	@Override
	public UserSuper findUserSuperName(String userName) {
		return userSuperRepository.findByUserName(userName);
	}

	@Override
	public UserSuper getUserSuperById(Long id) {
		return userSuperRepository.getById(id);
	}

	@Override
	public void updatePassword(String password,String userName) {
		//UserSuper userSuper = new UserSuper(userName,password);
		userSuperRepository.updatePassword(password,userName);
	}

}
