package com.chinesedreamer.eir.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 30, 2016
**/
@Controller
public class SessionController {
	@RequestMapping(value="sessionoverdue")
	public String overdue(){
		return "sessionoverdue";
	}
}
