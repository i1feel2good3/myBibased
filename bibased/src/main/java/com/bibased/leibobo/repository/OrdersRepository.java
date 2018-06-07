package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.Orders;
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
public interface OrdersRepository extends JpaRepository<Orders,Long> {

	@Query("select o from Orders as o where o.patientId = ?1")
	public List<Orders> listPatientOrders(Long patientId);

	@Query("select o from Orders as o where o.doctorId = ?1")
	public List<Orders> listDoctorOrders(Long doctorId);

	@Transactional
	@Modifying
	@Query("update Orders as o set o.link = ?1 where o.id = ?2 ")
	public void updateLink(String link,Long id);
}
