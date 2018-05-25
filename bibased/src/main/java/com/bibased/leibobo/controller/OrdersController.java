package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.application.OrdersApplication;
import com.bibased.leibobo.application.PlanDoctorApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.Orders;
import com.bibased.leibobo.domain.PlanDoctor;
import com.bibased.leibobo.domain.support.AjaxResponse;
import lombok.Data;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 1.普通用户预约接口
 * 2.普通用户添加预约
 * 3.前端传来OneOrder，插入一个orders，根据planDoctorId查到PlanDoctor的doctorId
 * 4.修改PlanDoctor
 * Created by boboLei on 2018/5/24.
 */
@RestController
@RequestMapping(value = "bibased")
public class OrdersController {

	@Autowired
	private PlanDoctorApplication planDoctorApplication;
	@Autowired
	private OrdersApplication ordersApplication;

	@Transactional
	@RequestMapping(value = "/remote/andOrder",method = RequestMethod.POST)
	public AjaxResponse andOrder(@RequestBody OneOrder oneOrder, HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			PlanDoctor planDoctor = planDoctorApplication.getDoctorPlanById(oneOrder.getPlanDoctorId());
			Orders order = new Orders(idAndRole.getUserId(),planDoctor.getDoctorId(),planDoctor.getId(),oneOrder.orderTime);
			ordersApplication.saveOrder(order);
			String newPlan = planDoctor.changePlan(oneOrder.getSetZeroNum()).getDoctorPlan();
			planDoctorApplication.updatePlan(newPlan,oneOrder.getPlanDoctorId());
			return AjaxResponse.succss(null);
		}else {
			return AjaxResponse.failure("您还没有登录,不能预约！");
		}
	}

	@Data
	static class OneOrder{
		private Long planDoctorId;
		private String orderTime;
		private Integer setZeroNum;
	}
}
