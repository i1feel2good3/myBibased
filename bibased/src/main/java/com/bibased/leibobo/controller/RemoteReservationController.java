package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.application.PlanDoctorApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.PlanDoctor;
import com.bibased.leibobo.domain.support.AjaxResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 1.返回远程预约界面
 * Created by booLei on 2018/5/5.
 */
@RestController
@RequestMapping(value = "bibased")
public class RemoteReservationController {

	@Autowired
	private PlanDoctorApplication planDoctorApplication;

	@RequestMapping(value = "/remote/manager",method = RequestMethod.GET)
	public ModelAndView remoteManager(){
		ModelAndView remoteModel = new ModelAndView("remoteReservation");
		return remoteModel;
	}

	/**
	 * @param plan
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/remote/and",method = RequestMethod.POST)
	public AjaxResponse docAndPlan(@RequestBody Plan plan,HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			PlanDoctor planDoctor = new PlanDoctor(idAndRole.getUserId(),plan.getDocPlan(),plan.getData());
			planDoctorApplication.savePlan(planDoctor);
			return AjaxResponse.succss(null);
		}else {
			return AjaxResponse.failure("请您先登录！");
		}
	}

	@Data
	static class Plan{
		private Date data;
		private String docPlan;
	}
}
