package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.IdAndRole;
import com.bibased.leibobo.application.NewsApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.News;
import com.bibased.leibobo.domain.support.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by boboLei on 2018/5/26.
 */
@RestController
@RequestMapping(value = "bibased")
public class NewsLookController {

	@Autowired
	private NewsApplication newsApplication;

	@RequestMapping(value = "/news/lookLetter",method = RequestMethod.GET)
	public ModelAndView newsWrite(){
		ModelAndView modelAndView = new ModelAndView("myNews");
		return modelAndView;
	}

	@RequestMapping(value = "/news/detailLetter",method = RequestMethod.GET)
	public ModelAndView newsDetail(){
		ModelAndView modelAndView = new ModelAndView("detailLetter");
		return modelAndView;
	}

	/**1.根据用户id获取用户的通知列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/news/noticeList",method = RequestMethod.GET)
	public AjaxResponse getNoticeList(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole)session.getAttribute(WebSecurityConfig.SESSION_KEY);
			List<News> newsNoticeList = newsApplication.getNoticeList(idAndRole.getUserId());
			return AjaxResponse.succss(newsNoticeList);
		}else {
			return AjaxResponse.failure("无法获取通知列表，因为您还没有登录！");
		}
	}

	/**1.获取发送过的私信
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/news/letterSendList",method = RequestMethod.GET)
	public AjaxResponse getLetterSendList(HttpSession session){
        if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole)session.getAttribute(WebSecurityConfig.SESSION_KEY);
			List<News> newsSendLetterList = newsApplication.getLetterSendList(idAndRole.getUserId());
			return AjaxResponse.succss(newsSendLetterList);
		}else {
        	return AjaxResponse.failure("无法获取发送私信列表，因为您还没有登录！");
		}
	}

	/**1.获取收到过的私信
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/news/letterReceivedList",method = RequestMethod.GET)
	public AjaxResponse getLetterReceivedList(HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			IdAndRole idAndRole = (IdAndRole)session.getAttribute(WebSecurityConfig.SESSION_KEY);
        	List<News> newsReceivedList = newsApplication.getLetterReceivedList(idAndRole.getUserId());
        	return AjaxResponse.succss(newsReceivedList);
		}else {
			return AjaxResponse.failure("无法获取已收私信列表，因为您还没有登录！");
		}
	}

	/**1.根据消息的id，返回消息的详细信息
	 * @param newsId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/news/letterDetails",method = RequestMethod.GET)
	public AjaxResponse getLetterDetails(Long newsId,HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			News newsDetails = newsApplication.getLetterDeatil(newsId);
			return AjaxResponse.succss(newsDetails);
		}else {
			return AjaxResponse.failure("无法查看详情，因为您还没有登录！");
		}
	}

	@RequestMapping(value = "/news/LetterIsRead",method = RequestMethod.GET)
	public AjaxResponse setLetterReaded(Long newsId,HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			newsApplication.updateLetterReadStatus(newsId);
			return AjaxResponse.succss(null);
		}else {
			return AjaxResponse.failure("修改已读出现错误！");
		}
	}
}
