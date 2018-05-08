package com.bibased.leibobo.Model;

import lombok.Data;

/**
 * Created by boboLei on 2018/5/4.
 */
@Data
public class IdAndRole {
	private Long userId;
	private Integer roleCode;
	public IdAndRole(){}

	public IdAndRole(Long userId,Integer roleCode){
		this.userId = userId;
		this.roleCode = roleCode;
	}
}
