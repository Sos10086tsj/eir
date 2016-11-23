package com.chinesedreamer.eir.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 21, 2016
**/
@Controller
@RequestMapping(value="job")
public class JobController {

	@RequestMapping(value="po")
	public String po(){
		return "job/po";
	}
	
	@ResponseBody
	@RequestMapping(value="test")
	public String test(){
		return "success";
	}
}
