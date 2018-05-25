package com.bibased.leibobo.application.impl;

import com.bibased.leibobo.application.OrdersApplication;
import com.bibased.leibobo.domain.Orders;
import com.bibased.leibobo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by boboLei on 2018/4/28.
 */
@Service
public class OrdersApplicationImpl implements OrdersApplication {
	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public void saveOrder(Orders order) {
		ordersRepository.save(order);
	}
}
