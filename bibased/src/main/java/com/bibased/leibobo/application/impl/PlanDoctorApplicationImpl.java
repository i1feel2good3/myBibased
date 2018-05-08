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
	public void savePlan(PlanDoctor planDoctor) {
		planDoctorRepository.save(planDoctor);
	}

	@Override
	public List<PlanDoctor> getPlan(Long docId, Date date) {
		return null;
	}
}
