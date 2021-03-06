package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.CodeEnum;
import com.bibased.leibobo.domain.support.DescribeEnum;

/**
 * Created by boboLei on 2018/4/28.
 */
public enum NewsType implements CodeEnum,DescribeEnum{

	PERSONAL_LETTER(0,"私信"),
	NOTICE(1,"预约通知");

	private final int code;
	private final String descr;

	NewsType(int code,String descr){
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
