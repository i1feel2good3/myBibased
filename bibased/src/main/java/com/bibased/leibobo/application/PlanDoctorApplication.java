package com.bibased.leibobo.application;

import com.bibased.leibobo.domain.PlanDoctor;

import java.util.Date;
import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
public interface PlanDoctorApplication {
	public void savePlan(PlanDoctor planDoctor);

	public PlanDoctor getOnePlan(Long docId,String date);

	public PlanDoctor getDoctorTopPlan(Long docId);

	public List<PlanDoctor> getDoctorAllPlan(Long docId);

	public void updatePlan(String plan,Long id);

	public PlanDoctor getDoctorPlanById(Long id);
}
