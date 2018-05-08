package com.bibased.leibobo.application;

import com.bibased.leibobo.domain.UserDoctor;
import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
public interface UserDoctorApplication {
	public UserDoctor saveUserDoctor(UserDoctor userDoctor);

	public List<UserDoctor> findAllUserDoctor();

	void updatePassword(String passWord,String userName);

	//根据用户名查找用户，返回的信息判断密码是否正确
	//判断该用户是否已经存在，存在就不可注册该用户名
	public UserDoctor findUserDoctorName(String userName);

	public UserDoctor getUserDoctorById(Long id);
}
