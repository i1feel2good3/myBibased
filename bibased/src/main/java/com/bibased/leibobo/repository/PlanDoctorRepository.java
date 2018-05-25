package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.PlanDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
@Repository
public interface PlanDoctorRepository extends JpaRepository<PlanDoctor, Long> {

	//按照id分组，每个分组只有一条数据，同时按照plantime倒序，doctorid匹配必然是最新的一条数据
	@Query("select p from PlanDoctor as p where doctorId = ?1 and createTime = " +
			"(select max (createTime) from PlanDoctor where doctorId = ?1)")
	public PlanDoctor findPlanDoctorTopPlan(Long doctorId);

	//医生修改自己的安排，显示一天的
	@Query("select p from PlanDoctor as p where doctorId = ?1 and planTime = ?2")
	public PlanDoctor findDoctorOnePlan(Long doctorId,String planTime);

	@Transactional
	@Modifying
	@Query("update PlanDoctor as p set p.doctorPlan = ?1 where p.id = ?2 ")
	public void updatePlan(String doctorPlan,Long id);

	//查询医生的计划（在查出来之后进行过滤无效的）
	public List<PlanDoctor> findAllByDoctorId(Long doctorId);

	public PlanDoctor findById(Long id);
}
