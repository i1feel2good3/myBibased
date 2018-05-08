package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.CodeEnum;
import com.bibased.leibobo.domain.support.DescribeEnum;

/**
 * Created by boboLei on 2018/4/26.
 */
public enum UserIdType implements CodeEnum,DescribeEnum{
	IDENTITY_CARD(0,"居民身份证"),
	MILITARY_CARD(1,"军人证"),
	OTHER(2,"其他")
	;

	private final int code;
	private final String descr;

	UserIdType(int code,String descr){
		this.code = code;
		this.descr = descr;
	}

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getDesc() {
		return this.descr;
	}
}
