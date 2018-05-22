package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.AbstractEntity;
import com.bibased.leibobo.utils.DateUtils;
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
	private String planTime;

	public PlanDoctor(){

	}
	public PlanDoctor(Long doctorId,String doctorPlan,String planTime){
		setDoctorId(doctorId);
		setDoctorPlan(doctorPlan);
		setPlanTime(planTime);
		init();
	}
	@Override
	public void init() {
		super.init();
	}

	/**1.仅仅初始化一个计划的值，并没有存入数据库
	 * @return
	 */
	public void setDefaultPlan(){
		setDoctorPlan("0000");
		init();
	}

	/**1.这个有效指的是：可以修改的，就是7天之外的
	 * @param planDoctor
	 * @return
	 */
	public Boolean getValidPlan(PlanDoctor planDoctor){
		String result = planDoctor.getPlanTime().substring(0,10);
		DateUtils dateUtils = new DateUtils();
		String strValidDate = dateUtils.getNextCountDay(dateUtils.getDate(),6);
		if (result.compareTo(strValidDate) > 0){
			return true;
		}else {
			return false;
		}
	}

	public Boolean getInSevenPlan(PlanDoctor planDoctor){
		String result = planDoctor.getPlanTime().substring(0,10);
		DateUtils dateUtils = new DateUtils();
		String strValidDate = dateUtils.getNextCountDay(dateUtils.getDate(),6);
		if (result.compareTo(strValidDate) <= 0){
			return true;
		}else {
			return false;
		}
	}
}
