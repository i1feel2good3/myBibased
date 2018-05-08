package com.bibased.leibobo.domain.support;

/**
 * Created by boboLei on 2018/4/16.
 */
public interface Entity {
	Long getId();
	void init();
	int getVersion();
	void validate();
}
