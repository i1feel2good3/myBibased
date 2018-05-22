package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.application.PlanDoctorApplication;
import com.bibased.leibobo.application.UserDoctorApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.PlanDoctor;
import com.bibased.leibobo.domain.UserDoctor;
import com.bibased.leibobo.domain.support.AjaxResponse;
import com.bibased.leibobo.utils.DateUtils;
import lombok.Data;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Id;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1.返回远程预约界面
 * Created by booLei on 2018/5/5.
 */
@RestController
@RequestMapping(value = "bibased")
public class RemoteReservationController {

	@Autowired
	private PlanDoctorApplication planDoctorApplication;

	@Autowired
	private UserDoctorApplication userDoctorApplication;

	@RequestMapping(value = "/remote/manager",method = RequestMethod.GET)
	public ModelAndView remoteManager(){
		ModelAndView remoteModel = new ModelAndView("remoteReservation");
		return remoteModel;
	}

	/**1.这个用于第一个页面的刷新，在这里获取用户类型，医生则返回医生角色和id，默认的添加安排的日期
	 * 2.先查找上一次安排日期，如果是当天日期+6的日期之内，则返回当天日期+7的日期，如果之外，则返回日期的下一天
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/remote/getPlanTime",method = RequestMethod.GET)
	public AjaxResponse getPlanTime(HttpSession session){
		DateUtils dateUtils = new DateUtils();
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole)session.getAttribute(WebSecurityConfig.SESSION_KEY);
			if (idAndRole.getRoleCode() == 1){
				PlanDoctor planDoctor = planDoctorApplication.getDoctorTopPlan(idAndRole.getUserId());
				//System.out.println(planDoctor.getId()+" "+planDoctor.getPlanTime());
				if (planDoctor == null){
					System.out.println("这货还没有安排过计划,返回了date类型的第七天的空安排");
					return AjaxResponse.successed(dateUtils.getNextCountDay(dateUtils.getDate(),7),idAndRole);
				}else {
					//System.out.println(planDoctor.getPlanTime().substring(0,10));
					if (planDoctor.getPlanTime().compareTo(dateUtils.getNextCountDay(dateUtils.getDate(),6)) <= 0){
						return AjaxResponse.successed(dateUtils.getNextCountDay(dateUtils.getDate(),7),idAndRole);
					}else {
						//将取出的数据格式转为：yyyy-MM-dd
						String strDate = planDoctor.getPlanTime().substring(0,10);
						strDate = dateUtils.getNextCountDay(dateUtils.strToDate(strDate),1);
						return AjaxResponse.successed(strDate,idAndRole);
					}
				}
			}
			//病人页面展示所以七日内有安排的医生，然后选择医生，就看到医生的安排表，进行预约即可
			if (idAndRole.getRoleCode() == 2){
				//List<PlanDoctor> planDoctorList = planDoctorApplication.getDoctorAllPlan(idAndRole.getUserId());
				List<UserDoctor> allDoctors = userDoctorApplication.findAllUserDoctor();
				allDoctors = allDoctors.stream().filter(x -> x.getUserRole().getCode() == 1).collect(Collectors.toList());
				allDoctors = allDoctors.stream().filter(
						userDoctor -> judgeDocPlanValid(userDoctor)
				).collect(Collectors.toList());
				return AjaxResponse.successed(allDoctors,idAndRole);
			}
			if (idAndRole.getRoleCode() == 0){
				return AjaxResponse.successed("你别看了，超管爸爸没必要看这里",idAndRole);
			}
			else {
				return AjaxResponse.error("get plan time error!");
			}
		}else {
			return AjaxResponse.failure("请您先进行登录");
		}
	}

	/**
	 * @param plan
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/remote/and",method = RequestMethod.POST)
	public AjaxResponse docAndPlan(@RequestBody Plan plan,HttpSession session){
		if (plan == null){
			return AjaxResponse.error("计划为空，请重新输入");
		}
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			System.out.println("插入数据的时间格式"+plan.getDate());
			PlanDoctor planDoctor = new PlanDoctor(idAndRole.getUserId(),plan.getDocPlan(),plan.getDate());
			planDoctorApplication.savePlan(planDoctor);
			return AjaxResponse.succss(null);
		}else {
			return AjaxResponse.failure("请您先登录！");
		}
	}

	@RequestMapping(value = "/remote/change",method = RequestMethod.POST)
	public AjaxResponse docChangePlan(@RequestBody ChangePlan changePlan,HttpSession session){
		if (changePlan == null){
			return AjaxResponse.error("您要修改的计划是空的哎");
		}
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			//IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
			planDoctorApplication.updatePlan(changePlan.docPlan,changePlan.id);
			return AjaxResponse.succss(null);
		}else {
			return AjaxResponse.failure("您还没有登录！");
		}
	}

	/**
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/remote/personalPlanList",method = RequestMethod.GET)
	public AjaxResponse docPlanList(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole)session.getAttribute(WebSecurityConfig.SESSION_KEY);
			List<PlanDoctor> planDoctorList = planDoctorApplication.getDoctorAllPlan(idAndRole.getUserId());
			if (planDoctorList.isEmpty()){
				return AjaxResponse.succss("您还没有可查看/修改的计划，请先去添加计划！");
			}else {
				planDoctorList = planDoctorList.stream().filter(
						planDoctor ->  planDoctor.getValidPlan(planDoctor)
				).collect(Collectors.toList());
				return AjaxResponse.succss(planDoctorList);
			}
		}else {
			return AjaxResponse.failure("您还没有登录！");
		}
	}

/**
 * 1.点击修改计划，前端设置默认时间为不可修改的第一天时间，并且用这个时间来查找计划
 * 2.计划存在则返回，计划不存在则返回默认的空的计划显示
 * 3.判断当前时间是不是可以修改，在前端判断
 * @param
 * @param
 * @return
 @RequestMapping(value = "/remote/getOnePlan",method = RequestMethod.GET)
 public AjaxResponse docGetOnePlan(String planTime,HttpSession session){
 if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
 IdAndRole idAndRole = (IdAndRole) session.getAttribute(WebSecurityConfig.SESSION_KEY);
 PlanDoctor planDoctor = planDoctorApplication.getOnePlan(idAndRole.getUserId(),planTime);
 if (planDoctor == null){
 planDoctor = new PlanDoctor();
 planDoctor.setDefaultPlan();
 System.out.println(planDoctor.getDoctorPlan());
 return AjaxResponse.succss(planDoctor);
 }else {
 return AjaxResponse.succss(planDoctor);
 }
 }else {
 return AjaxResponse.failure("您还没有登录！");
 }
 }*/
	//病人接口
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 1.根据医生的id找到医生当前7天内的计划列表
	 * @param docId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/remote/getDocValidPlan",method = RequestMethod.GET)
	public AjaxResponse getDocValidPlan(Long docId,HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			List<PlanDoctor> planDoctorList = planDoctorApplication.getDoctorAllPlan(docId);
			planDoctorList = planDoctorList.stream().filter(
					planDoctor -> planDoctor.getInSevenPlan(planDoctor)
			).collect(Collectors.toList());
			return AjaxResponse.succss(planDoctorList);
		}else {
			return AjaxResponse.failure("请登录");
		}
	}

	/**
	 * @param userDoctor
	 * @return
	 */
	private Boolean judgeDocPlanValid(UserDoctor userDoctor){
		List<PlanDoctor> planDoctorList =planDoctorApplication.getDoctorAllPlan(userDoctor.getId());
		if (planDoctorList.isEmpty()){
			return false;
		}else {
			planDoctorList = planDoctorList.stream().filter(
					planDoctor -> planDoctor.getInSevenPlan(planDoctor)
			).collect(Collectors.toList());
			if (planDoctorList != null){
				return true;
			}else {
				return false;
			}
		}
	}

	@Data
	static class Plan{
		private String date;
		private String docPlan;
	}

	@Data
	static class ChangePlan{
		private Long id;
		private String docPlan;
	}
}
