package com.bibased.leibobo.application;

import com.bibased.leibobo.domain.PlanDoctor;

import java.util.Date;
import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
public interface PlanDoctorApplication {
	public void savePlan(PlanDoctor planDoctor);

	public List<PlanDoctor> getPlan(Long docId,Date date);
}
