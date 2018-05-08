package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.CodeEnum;
import com.bibased.leibobo.domain.support.DescribeEnum;

/**
 * Created by boboLei on 2018/4/24.
 */
public enum UserRoleEnum implements CodeEnum,DescribeEnum{
	USER_SUPER(0,"超管"),

	USER_DOCTOR(1,"医生"),

	USER_PATIENT(2,"普通");

	private final int code;
	private final String descr;

	UserRoleEnum(int code, String descr) {
		this.code = code;
		this.descr = descr;
	}

	@Override
	public int getCode(){
		return this.code;
	}

	@Override
	public String getDesc(){
		return this.descr;
	}
}
