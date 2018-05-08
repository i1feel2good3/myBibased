package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 1.doctorPlan字符串（根据医生设置计划表，展示计划，111111六个字符串表示上午3个时间段，和下午3个时间段可以预约，000000则不可约）
 * Created by boboLei on 2018/4/28.
 */
@Data
@Entity
@Table(name = "b_plan_doctor")
public class PlanDoctor extends AbstractEntity{

	@Column(name = "doctor_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long doctorId;

	@Column(name = "doctor_plan")
	@Setter(AccessLevel.PRIVATE)
	private String doctorPlan;

	@Column(name = "plan_time")
	@Setter(AccessLevel.PRIVATE)
	private Date planTime;

	public PlanDoctor(){

	}
	public PlanDoctor(Long doctorId,String doctorPlan,Date planTime){
		setDoctorId(doctorId);
		setDoctorPlan(doctorPlan);
		setPlanTime(planTime);
	}

	@Override
	public void init() {
		super.init();
		setDoctorPlan("000000");
	}

}
