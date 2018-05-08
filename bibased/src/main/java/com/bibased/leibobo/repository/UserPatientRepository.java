package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.UserPatient;
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
public interface UserPatientRepository extends JpaRepository<UserPatient,Long>{
	//根据Id修改密码
	@Transactional
	@Modifying
	@Query("update UserPatient u set u.passWord = ?1 where u.userName = ?2 ")
	void updatePassword(String passWord,String userName);

	//根据id查找用户名
	@Query("select u from UserPatient as u where u.id = ?1")
	public UserPatient getById(Long id);

	//查找用户名，不允许重复用户名
	public UserPatient findByUserName(String userName);
}
