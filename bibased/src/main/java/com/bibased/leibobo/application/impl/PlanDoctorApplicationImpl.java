package com.bibased.leibobo.application.impl;

import com.bibased.leibobo.application.PlanDoctorApplication;
import com.bibased.leibobo.domain.PlanDoctor;
import com.bibased.leibobo.repository.PlanDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
@Service
public class PlanDoctorApplicationImpl implements PlanDoctorApplication{

	@Autowired
	private PlanDoctorRepository planDoctorRepository;

	@Override
	public List<PlanDoctor> getDoctorAllPlan(Long docId) {
		return planDoctorRepository.findAllByDoctorId(docId);
	}

	@Override
	public void updatePlan(String plan, Long id) {
		planDoctorRepository.updatePlan(plan,id);
	}

	@Override
	public PlanDoctor getDoctorPlanById(Long id) {
		return planDoctorRepository.findById(id);
	}

	@Override
	public void savePlan(PlanDoctor planDoctor) {
		planDoctorRepository.save(planDoctor);
	}

	@Override
	public PlanDoctor getOnePlan(Long docId, String date) {
		return planDoctorRepository.findDoctorOnePlan(docId,date);
	}

	@Override
	public PlanDoctor getDoctorTopPlan(Long docId) {
		return planDoctorRepository.findPlanDoctorTopPlan(docId);
	}
}
