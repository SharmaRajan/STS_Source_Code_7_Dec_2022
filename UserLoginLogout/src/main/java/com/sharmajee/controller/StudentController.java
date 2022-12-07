package com.sharmajee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sharmajee.entity.StudentEntity;

@Controller
@RequestMapping("/student")
public class StudentController {

	@RequestMapping(value="/homePage", method= {RequestMethod.GET,RequestMethod.POST})
	public String viewHomePage()	{
		return "index";
	}
	
	@RequestMapping(value="/register", method= {RequestMethod.GET,RequestMethod.POST})
	public String showSignUpForm(Model model)	{
		model.addAttribute("user", new StudentEntity());
		return "signup_form";
	}
	
	@RequestMapping(value="/error", method= {RequestMethod.GET,RequestMethod.POST})
	public String errorPage()	{
		return "error";
	}
	
}
