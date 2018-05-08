package com.bibased.leibobo.application;

import com.bibased.leibobo.domain.UserSuper;

import java.util.List;

/**
 * Created by boboLei on 2018/4/27.
 */
public interface UserSuperApplication {
	public UserSuper saveUserSuper(UserSuper userSuper);

	public List<UserSuper> findAllUserSuper();

	void updatePassword(String passWord,String userName);

	//根据用户名查找用户，返回的信息判断密码是否正确
	//判断该用户是否已经存在，存在就不可注册该用户名
	public UserSuper findUserSuperName(String userName);

	public UserSuper getUserSuperById(Long id);
}
