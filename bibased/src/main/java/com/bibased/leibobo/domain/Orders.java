package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.assertj.core.util.Preconditions;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by boboLei on 2018/4/28.
 */
@Data
@Entity
@Table(name = "b_orders")
public class Orders extends AbstractEntity{

	@Column(name = "patient_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long patientId;			//患者id

	@Column(name = "doctor_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long doctorId; 			//医生id

	@Column(name = "doctor_plan_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long planDoctorId;

	@Column(name = "order_time",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private String orderTime;			//预约时间，哪一天几点，默认持续一小时

	@Column(name = "order_status",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	@Enumerated(EnumType.ORDINAL)
	private OrderStatus orderStatus; 	//预约状态，有效或者无效，默认有效

	@Column(name = "done",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Boolean done;		//预约是否完成

	public Orders(){}

	public Orders(Long patientId,Long doctorId,Long planDoctorId,String orderTime){
		Preconditions.checkArgument(patientId != null,"patientId is null");
		Preconditions.checkArgument(doctorId != null,"doctorId is null");
		Preconditions.checkArgument(planDoctorId != null,"orderTime is null");
		Preconditions.checkArgument(orderTime != null,"orderTime is null");
		init();
		setPatientId(patientId);
		setDoctorId(doctorId);
		setPlanDoctorId(planDoctorId);
		setOrderTime(orderTime);
	}

	@Override
	public void init() {
		super.init();
		setOrderStatus(OrderStatus.ENABLE);
		setDone(false);
	}

	public void enable(){
		setOrderStatus(OrderStatus.ENABLE);
	}

	public void disEnable(){
		setOrderStatus(OrderStatus.DISABLE);
	}

	public void orderIsDone(){
		setDone(true);
	}
}
