package com.bibased.leibobo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.assertj.core.util.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by boboLei on 2018/4/24.
 */
@Entity
@Data
@Table(name = "b_users")
public class UserPatient extends UserSuper{

	@Column(name = "age")
	@Setter(AccessLevel.PRIVATE)
	private Integer age;	//年龄

	@Column(name = "email")
	@Setter(AccessLevel.PRIVATE)
	private String email;	//邮箱 可选

	@Column(name = "address")
	@Setter(AccessLevel.PRIVATE)
	private String address;	//住址

	public UserPatient(){

	}

	public UserPatient(String userName,String realName,String passWord,String mobilePhone,
					   Integer userRoleCode,Integer idTypeCode,String idNumber,Integer age,
					   String email,String address){
		super(userName,realName,passWord,mobilePhone,
				userRoleCode,idTypeCode,idNumber);
		Preconditions.checkArgument(age != null,"The String age is null");
		Preconditions.checkArgument(email != null,"The String idNumber is null");
		Preconditions.checkArgument(address != null,"The String idNumber is null");
		init();
		/*
		*这里的init（）函数在上面super中已经调用过了，为什么不能生效，需要重新调用呢？
		* */
		setAge(age);
		setEmail(email);
		setAddress(address);
	}

	public UserPatient(String userName,String realName,String passWord,String mobilePhone,
					   Integer idTypeCode,String idNumber,Integer age,
					   String email,String address){
		super(userName,realName,passWord,mobilePhone,
				idTypeCode,idNumber);
		Preconditions.checkArgument(age != null,"The String age is null");
		Preconditions.checkArgument(email != null,"The String idNumber is null");
		Preconditions.checkArgument(address != null,"The String idNumber is null");
		init();
		/*
		*这里的init（）函数在上面super中已经调用过了，为什么不能生效，需要重新调用呢？
		* */
		setAge(age);
		setEmail(email);
		setAddress(address);
	}

	@Override
	public void init() {
		super.init();
	}
}
