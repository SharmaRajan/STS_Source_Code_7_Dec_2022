package com.sharmajee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	
	@RequestMapping("/")
	public String home()	{
		return "home";
	}
	
	@RequestMapping("/contact")
	public String contact()	{
		return "contact";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String handleTest()	{
		return "This is project using STS";
	}
}
