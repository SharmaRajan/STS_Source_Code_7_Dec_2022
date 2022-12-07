package com.sharmajee.StudentCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharmajee.StudentCrud.entity.Student;
import com.sharmajee.StudentCrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	
	@Autowired
	private StudentRepository repo;
	
	public StudentServiceImpl(StudentRepository studentRepository)	{
		super();
		this.repo=studentRepository;
	}
	
	@Override
	public List<Student> getAllStudents()	{
		return repo.findAll();
	}
	
	public void save(Student std)	{
		repo.save(std);
	}
	
	public Student get(long id)	{
		return repo.findById(id).get();
	}
	public void delete(long id)	{
		repo.deleteById(id);
	}
	//.deleteById(id);
}
