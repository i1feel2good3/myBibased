package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.HistoryModel;
import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.application.OrdersApplication;
import com.bibased.leibobo.application.UserDoctorApplication;
import com.bibased.leibobo.application.UserPatientApplication;
import com.bibased.leibobo.application.UserSuperApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.Orders;
import com.bibased.leibobo.domain.UserDoctor;
import com.bibased.leibobo.domain.UserPatient;
import com.bibased.leibobo.domain.support.AjaxResponse;
import javafx.beans.value.WeakChangeListener;
import org.apache.catalina.User;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Id;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**1.医生显示被预约的记录，表格信息吧
 * 2.病人显示预约过的记录
 * Created by boboLei on 2018/5/26.
 */
@RestController
@RequestMapping(value = "bibased")
public class UserHistoryController {

	@Autowired
	private OrdersApplication ordersApplication;

	@Autowired
	private UserPatientApplication userPatientApplication;

	@Autowired
	private UserDoctorApplication userDoctorApplication;

	@RequestMapping(value = "/history/manager",method = RequestMethod.GET)
	public ModelAndView historyManager(){
		ModelAndView modelAndView = new ModelAndView("history");
		return modelAndView;
	}

	@RequestMapping(value = "/history/list")
	public AjaxResponse getHistory(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			if (idAndRole.getRoleCode() == 0){
				return getAllHistory(session);
			}
			if (idAndRole.getRoleCode() == 1){
				return getDoctorHistory(session);
			}
			else {
				return getPatientHistory(session);
			}
		}else {
			return AjaxResponse.failure("您还没有登录，不能获取历史记录！");
		}
	}

	@RequestMapping(value = "/history/doctor",method = RequestMethod.GET)
	public AjaxResponse getDoctorHistory(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			List<HistoryModel> historyModels = new ArrayList<>();
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			UserDoctor userDoctor = userDoctorApplication.getUserDoctorById(idAndRole.getUserId());
			List<Orders> doctorOrders = ordersApplication.getListDoctorOrders(idAndRole.getUserId());
			for (int i = 0;i < doctorOrders.size();i++){
				UserPatient userPatient = userPatientApplication.getUserPatientById(doctorOrders.get(i).getPatientId());
				HistoryModel historyModel = new HistoryModel();
				historyModel.setDoctorName(userDoctor.getRealName());
				historyModel.setPatientName(userPatient.getRealName());
				historyModel.setOrderTime(doctorOrders.get(i).getOrderTime());
				historyModel.setIsDone(doctorOrders.get(i).getDone());
				historyModels.add(historyModel);
			}
			return AjaxResponse.successed(historyModels,idAndRole);
		}else {
			return AjaxResponse.failure("您还没有登录，不能获取历史记录！");
		}
	}

	@RequestMapping(value = "/history/patient",method = RequestMethod.GET)
	public AjaxResponse getPatientHistory(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			List<HistoryModel> historyModels = new ArrayList<>();
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			UserPatient userPatient = userPatientApplication.getUserPatientById(idAndRole.getUserId());
			List<Orders> patientOrders = ordersApplication.getListPatientOrders(idAndRole.getUserId());
			for (int i = 0;i < patientOrders.size();i++){
				HistoryModel historyModel = new HistoryModel();
				UserDoctor userDoctor = userDoctorApplication.getUserDoctorById(patientOrders.get(i).getDoctorId());
				historyModel.setPatientName(userPatient.getRealName());
				historyModel.setDoctorName(userDoctor.getRealName());
				historyModel.setOrderTime(patientOrders.get(i).getOrderTime());
				historyModel.setIsDone(patientOrders.get(i).getDone());
				historyModels.add(historyModel);
			}
			return AjaxResponse.successed(historyModels,idAndRole);
		}else {
			return AjaxResponse.failure("您还没有登录，不能获取历史记录！");
		}
	}

	@RequestMapping(value = "/history/all",method = RequestMethod.GET)
	public AjaxResponse getAllHistory(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			List<HistoryModel> historyModels = new ArrayList<>();
			List<Orders> allOrders = ordersApplication.getAllOrders();
			for (int i = 0;i < allOrders.size();i++){
				UserDoctor  userDoctor = userDoctorApplication.getUserDoctorById(allOrders.get(i).getDoctorId());
				UserPatient userPatient = userPatientApplication.getUserPatientById(allOrders.get(i).getPatientId());
				HistoryModel historyModel = new HistoryModel();
				historyModel.setDoctorName(userDoctor.getRealName());
				historyModel.setPatientName(userPatient.getRealName());
				historyModel.setOrderTime(allOrders.get(i).getOrderTime());
				historyModel.setIsDone(allOrders.get(i).getDone());
				historyModels.add(historyModel);
			}
			return AjaxResponse.successed(historyModels,idAndRole);
		}else {
			return AjaxResponse.failure("您还没有登录，不能管理历史记录！");
		}
	}
}
