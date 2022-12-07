package com.sharmajee.StudentCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharmajee.StudentCrud.entity.Student;
import com.sharmajee.StudentCrud.service.StudentService;
import com.sharmajee.StudentCrud.service.StudentServiceImpl;

@Controller
public class StudentController {

	@Autowired
	private StudentServiceImpl service;
	
	private StudentService serv;
	
	public StudentController(StudentService studentService)	{
		super();
		this.serv=studentService;
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model)	{
		List<Student> listStudents = service.getAllStudents();
		model.addAttribute("studentList", listStudents);
		return "index";
	}
	
	@GetMapping("/new")
	public String add(Model model)	{
		model.addAttribute("student", new Student());
		return "new";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student")Student std)	{
		service.save(std);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/(id)")
	public ModelAndView showEditStudentPage(@PathVariable(name="id")int id)	{
		ModelAndView mav = new ModelAndView("new");
		Student std = service.get(id);
		mav.addObject("student", std);
		return mav;
	}
	
	@RequestMapping("/delete/(id)")
	public String deleteStudent(@PathVariable(name="id")int id)	{
		service.delete(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/list")
	public String listStudents(Model model)		{
		model.addAttribute("students", serv.getAllStudents());
		return "students";
	}
	
	
	
	
	
	
	
	
	
	
	
}
