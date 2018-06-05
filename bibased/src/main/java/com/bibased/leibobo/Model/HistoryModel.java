package com.bibased.leibobo.Model;

import lombok.Data;

/**
 * Created by boboLei on 2018/5/27.
 */
@Data
public class HistoryModel {
	private String doctorName;
	private String patientName;
	private String orderTime;
	private Boolean isDone;
}
