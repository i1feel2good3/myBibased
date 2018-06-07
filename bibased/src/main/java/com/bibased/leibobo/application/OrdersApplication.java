package com.bibased.leibobo.application;

import com.bibased.leibobo.domain.Orders;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
public interface OrdersApplication {
	public void saveOrder(Orders order);

	public List<Orders> getListPatientOrders(Long patientId);

	public List<Orders> getListDoctorOrders(Long doctorId);

	public List<Orders> getAllOrders();

	public void updateLink(String link,Long orderId);
}
