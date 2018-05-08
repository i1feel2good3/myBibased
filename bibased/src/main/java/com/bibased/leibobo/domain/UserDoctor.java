package com.bibased.leibobo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.assertj.core.util.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by boboLei on 2018/4/24.
 */
@Entity
@Data
@Table(name = "b_users")
public class UserDoctor extends UserPatient{
	@Column(name = "title")
	@Setter(AccessLevel.PRIVATE)
	private String title;//职称

	@Column(name = "subject")
	@Setter(AccessLevel.PRIVATE)
	private String subject;//所属科目

	@Column(name = "fields")
	@Setter(AccessLevel.PRIVATE)
	private String fields;//擅长

	public UserDoctor(){

	}

	public UserDoctor(String userName,String realName,String passWord,String mobilePhone,
					  Integer userRoleCode,Integer idTypeCode,String idNumber,Integer age,
					  String email,String address,String title,String subject,String fields){
		super(userName,realName,passWord,mobilePhone,userRoleCode,
				idTypeCode,idNumber,age,email,address);
		Preconditions.checkArgument(title != null,"the String title is null");
		Preconditions.checkArgument(subject != null,"the String subject is null");
		Preconditions.checkArgument(fields != null,"the String fields is null");
		init();
		setTitle(title);
		setSubject(subject);
		setFields(fields);
	}

	@Override
	public void init() {
		super.init();
	}
}
