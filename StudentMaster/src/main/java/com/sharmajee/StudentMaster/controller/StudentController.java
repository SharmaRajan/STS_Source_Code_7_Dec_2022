package com.sharmajee.StudentMaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

	@GetMapping("")
	public String viewHomePage()	{
		return "index";
	}
}
