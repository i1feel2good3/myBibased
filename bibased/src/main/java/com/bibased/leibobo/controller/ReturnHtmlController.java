package com.bibased.leibobo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/3/17.
 */
@RestController
@RequestMapping(value = "bobo")
public class ReturnHtmlController {
	//thymeleaf模板，return 有没有“/”都可以
	//thymeleaf模板，不需要配置
	//thymeleaf模板，不能带.html后缀
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView showlist(){
		ModelAndView mv =  new ModelAndView("index");
		return mv;
	}

	@RequestMapping(value = "/heheda",method = RequestMethod.GET)
	//@ResponseBody
	public String showString(){
		return "index";
	}

	/*@RequestMapping("/hello")
	public String helloHtml(HashMap<String, Object> map) {
		map.put("hello", "My bibased!");
		return "/index";
	}
	*/
	/*前端thymeleaf和bootstrap
	* 发现：HTML中可以直接调用接口。。
	*
	*
	*
	* @RestController is a stereotype annotation that combines @ResponseBody and @Controller.
意思是：
@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。

1)如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。

例如：本来应该到success.jsp页面的，则其显示success.



2)如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
3)如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
	* */

	//数据库的数据显示在页面
	//后台获取页面的数据
	//页面之间的跳转
}
