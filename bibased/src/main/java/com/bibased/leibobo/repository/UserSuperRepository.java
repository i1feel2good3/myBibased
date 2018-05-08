package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.UserSuper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by boboLei on 2018/4/27.
 */
@Repository
public interface UserSuperRepository extends JpaRepository<UserSuper,Long>{

	//根据Id修改密码,非原生sql
	@Transactional
	@Modifying
	@Query("update UserSuper u set u.passWord = ?1 where u.userName = ?2 ")
	void updatePassword(String passWord,String userName);

	//根据id查找用户名
	@Query("select u from UserSuper as u where u.id = ?1")
	public UserSuper getById(Long id);

	//查找用户名，不允许重复用户名,用户注册
	//用户登录，用户根据用户名登录
	public UserSuper findByUserName(String userName);

}
