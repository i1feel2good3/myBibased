package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by boboLei on 2018/4/28.
 */
@Entity
@Table(name = "b_diagnosis_desc")
@Data
public class DiagnosisDesc extends AbstractEntity{

	@Column(name = "diagnosis_desc_type",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	@Enumerated(EnumType.ORDINAL)
	private DiagnosisDescType diagnosisDescType;

	@Column(name = "diagnosis_patient_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long diagnosisPatientId;			//患者id

	@Column(name = "diagnosis_doctor_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long diagnosisDoctorId;    //医生id

	@Column(name = "diagnosis_content",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private String diagnosisContent;

	public DiagnosisDesc(){}
}
