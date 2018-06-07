package com.bibased.leibobo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by boboLei on 2018/6/7.
 */
@RestController
@RequestMapping(value = "bibased")
public class RemoteDiagnosisController {

	@RequestMapping(value = "/remote/diagnosis",method = RequestMethod.GET)
	public ModelAndView remoteVideo(){
		ModelAndView modelAndView = new ModelAndView("remoteDiagnosis");
		return modelAndView;
	}


}
