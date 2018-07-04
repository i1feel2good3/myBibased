package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.application.*;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.*;
import com.bibased.leibobo.domain.support.AjaxResponse;
import com.bibased.leibobo.utils.DateUtils;
import lombok.Data;
import org.aspectj.weaver.loadtime.Aj;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	@Autowired
	private NewsApplication newsApplication;
	@Autowired
	private UserPatientApplication userPatientApplication;
	@Autowired
	private UserDoctorApplication userDoctorApplication;

	/**
	 * 1.添加一条预约
	 * 2.对医生的计划进行修改，减少一条可预约时间
	 * 3.增加一条对病人的系统通知
	 * 4.增加一条对医生的系统通知
	 * 5.这四条作为一个事务
	 * @param oneOrder
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/remote/andOrder",method = RequestMethod.POST)
	public AjaxResponse andOrder(@RequestBody OneOrder oneOrder, HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			System.out.println(oneOrder.getPlanDoctorId());
			PlanDoctor planDoctor = planDoctorApplication.getDoctorPlanById(oneOrder.getPlanDoctorId());
			System.out.println(planDoctor.getDoctorId());
			System.out.println(oneOrder.getOrderTime());
			Orders order = new Orders(idAndRole.getUserId(),planDoctor.getDoctorId(),planDoctor.getId(),oneOrder.getOrderTime());
			ordersApplication.saveOrder(order);

			String newPlan = planDoctor.changePlan(oneOrder.getSetZeroNum()).getDoctorPlan();
			planDoctorApplication.updatePlan(newPlan,oneOrder.getPlanDoctorId());

			UserPatient userPatient = userPatientApplication.getUserPatientById(idAndRole.getUserId());
			UserDoctor userDoctor = userDoctorApplication.getUserDoctorById(planDoctor.getDoctorId());
			News noticeToPatient;
			News noticeToDoctor;
			String themeDoc = "有患者预约了您";
			String contentDoc = "患者"+userPatient.getRealName()+"预约了您"+oneOrder.getOrderTime()+"进行远程诊疗";
			noticeToDoctor = new News(planDoctor.getDoctorId(),themeDoc,contentDoc);
			newsApplication.saveNews(noticeToDoctor);

			String themePat = "您预约了";
			String contentPat = "您预约了医生"+userDoctor.getRealName()+"在"+oneOrder.getOrderTime()+"进行远程诊疗";
			noticeToPatient = new News(idAndRole.getUserId(),themePat,contentPat);
			newsApplication.saveNews(noticeToPatient);
			return AjaxResponse.succss(null);
		}else {
			return AjaxResponse.failure("您还没有登录,不能预约！");
		}
	}

	@RequestMapping(value = "remote/getYourOrder",method = RequestMethod.GET)
	public AjaxResponse getYourOrder(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			if (idAndRole.getRoleCode() == 0){
				return AjaxResponse.succss("超管");
			}
			if (idAndRole.getRoleCode() == 1){
				return getDocTodayOrders(idAndRole);
			} else {
				return getPatientTodayOrder(idAndRole);
			}
		}else {
			return AjaxResponse.error("您还没有登录！");
		}
	}

	private AjaxResponse getDocTodayOrders(IdAndRole idAndRole){
		DateUtils dateUtils = new DateUtils();
		List<RemoteOrder> remoteOrderList = new ArrayList<>();
		List<Orders> orders = ordersApplication.getListDoctorOrders(idAndRole.getUserId());
		orders = orders.stream().filter(
				order -> order.getOrderTime().substring(0,10) == dateUtils.getStringDate()
		).collect(Collectors.toList());
		if (orders.isEmpty()){
			return AjaxResponse.failure("您今日没有预约");
		}else {
			for (int i = 0;i < orders.size();i++){
				RemoteOrder remoteOrder = new RemoteOrder();
				remoteOrder.setDoctorId(orders.get(i).getDoctorId());
				remoteOrder.setPatientId(orders.get(i).getPatientId());
				remoteOrder.setOrderTime(orders.get(i).getOrderTime());
				UserDoctor doctor = userDoctorApplication.getUserDoctorById(orders.get(i).getDoctorId());
				UserPatient patient = userPatientApplication.getUserPatientById(orders.get(i).getPatientId());
				remoteOrder.setDoctorName(doctor.getRealName());
				remoteOrder.setPatientName(patient.getRealName());
				remoteOrderList.add(remoteOrder);
			}
		}
		return AjaxResponse.successed(remoteOrderList,idAndRole);
	}

	private AjaxResponse getPatientTodayOrder(IdAndRole idAndRole){
		DateUtils dateUtils = new DateUtils();
		RemoteOrder remoteOrder = new RemoteOrder();
		List<Orders> ordersList = ordersApplication.getListPatientOrders(idAndRole.getUserId());
		ordersList = ordersList.stream().filter(
				orders -> orders.getOrderTime().substring(0,10) == dateUtils.getStringDate()
		).collect(Collectors.toList());

		if (ordersList.size() == 1){
			remoteOrder.setDoctorId(ordersList.get(0).getDoctorId());
			remoteOrder.setPatientId(ordersList.get(0).getPatientId());
			remoteOrder.setOrderTime(ordersList.get(0).getOrderTime());
			UserDoctor doctor = userDoctorApplication.getUserDoctorById(ordersList.get(0).getDoctorId());
			UserPatient patient = userPatientApplication.getUserPatientById(ordersList.get(0).getPatientId());
			remoteOrder.setDoctorName(doctor.getRealName());
			remoteOrder.setPatientName(patient.getRealName());
			return AjaxResponse.successed(remoteOrder,idAndRole);
		}
		if (ordersList.isEmpty()){
			return AjaxResponse.failure("您今日没有预约");
		}
		else {
			return AjaxResponse.error("您这一天好几个预约，出错了");
		}
	}

	@Data
	static class RemoteOrder{
		private Long doctorId;
		private Long patientId;
		private String doctorName;
		private String patientName;
		private String orderTime;
		public RemoteOrder(){}
	}

	@Data
	static class OneOrder{
		private Long planDoctorId;
		private String orderTime;
		private Integer setZeroNum;
	}
}
