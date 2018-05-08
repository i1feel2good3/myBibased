package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.CodeEnum;
import com.bibased.leibobo.domain.support.DescribeEnum;

/**
 * Created by boboLei on 2018/4/28.
 */
public enum DiagnosisDescType implements CodeEnum,DescribeEnum{

	DIAGNOSIS(4,"诊断书"),
	PRESCRIPTION(5,"说明书");

	private final int code;
	private final String descr;

	DiagnosisDescType(int code,String descr){
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
