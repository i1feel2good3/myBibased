package com.bibased.leibobo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**1.医生显示被预约的记录，表格信息吧
 * 2.病人显示预约过的记录
 * Created by boboLei on 2018/5/26.
 */
@RestController
@RequestMapping(value = "bibased")
public class UserHistoryController {

	@RequestMapping(value = "/history/manager",method = RequestMethod.GET)
	public ModelAndView historyManager(){
		ModelAndView modelAndView = new ModelAndView("history");
		return modelAndView;
	}

}
