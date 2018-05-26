package com.bibased.leibobo.controller;

import com.bibased.leibobo.domain.News;
import com.bibased.leibobo.domain.Orders;
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
	@RequestMapping(value = "/news/lookLetter",method = RequestMethod.GET)
	public ModelAndView newsWrite(){
		ModelAndView modelAndView = new ModelAndView("myNews");
		return modelAndView;
	}

	/**1.根据用户id获取用户的通知列表
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/news/noticeList",method = RequestMethod.GET)
	public List<News> getNoticeList(Long userId,HttpSession session){

		return null;
	}

	@RequestMapping(value = "/news/letterList",method = RequestMethod.GET)
	public List<News> getLetterList(Long userId,HttpSession session){
		return null;
	}
}
