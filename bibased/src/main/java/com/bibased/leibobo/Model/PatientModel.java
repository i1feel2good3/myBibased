package com.bibased.leibobo.Model;

import lombok.Data;

/**
 * Created by boboLei on 2018/5/1.
 */
@Data
public class PatientModel {
	private String userName;	//	用户名
	private String realName;	//	真实姓名
	private String passWord;	//	密码
	private String mobilePhone;	//	手机号
	private Integer idType;		//身份类型
	private String idNumber;	//身份号
	private Integer age;
	private String email;	//邮箱 可选
	private String address;	//住址

}
