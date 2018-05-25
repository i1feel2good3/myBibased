package com.bibased.leibobo.controller;

import com.bibased.leibobo.Model.NewsSaveModel;
import com.bibased.leibobo.application.NewsApplication;
import com.bibased.leibobo.config.WebSecurityConfig;
import com.bibased.leibobo.domain.News;
import com.bibased.leibobo.domain.support.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by boboLei on 2018/5/25.
 */
@RestController
@RequestMapping(value = "bibased")
public class NewsWriteController {

	@Autowired
	private NewsApplication newsApplication;

	@RequestMapping(value = "/news/write",method = RequestMethod.GET)
	public ModelAndView newsWrite(){
		ModelAndView modelAndView = new ModelAndView("newsWrite");
		return modelAndView;
	}

	@RequestMapping(value = "/news/save",method = RequestMethod.POST)
	public AjaxResponse newsSave(@RequestBody NewsSaveModel newsSaveModel, HttpSession session){
		if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null){
			News news = new News(newsSaveModel.getFromUserId(),newsSaveModel.getToUserId(),
					newsSaveModel.getNewsTheme(),newsSaveModel.getNewsContent());
			news.setNewsPrivateLetter();
			newsApplication.saveNews(news);
			return AjaxResponse.succss(null);
		}else {
			return AjaxResponse.failure("您还没有登录，不能写信！");
		}
	}
}
