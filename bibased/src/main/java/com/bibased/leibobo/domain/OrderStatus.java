package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.CodeEnum;
import com.bibased.leibobo.domain.support.DescribeEnum;

/**
 * Created by boboLei on 2018/4/28.
 */
public enum OrderStatus implements CodeEnum,DescribeEnum{
	DISABLE(0, "无效"),
	ENABLE(1, "有效");
	;

	private final int code;
	private final String descr;

	OrderStatus(int code,String descr){
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
