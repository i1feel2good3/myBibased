package com.bibased.leibobo.Model;

import lombok.Data;

/**
 * Created by boboLei on 2018/5/25.
 */
@Data
public class NewsSaveModel {
	private Long fromUserId;
	private Long toUserId;
	private String newsTheme;
	private String newsContent;
}
