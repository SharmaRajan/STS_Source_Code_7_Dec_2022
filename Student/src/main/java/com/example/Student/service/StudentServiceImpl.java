package com.example.Student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Student.entities.StudentEntity;
import com.example.Student.exception.RecordNotFoundException;
import com.example.Student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public StudentServiceImpl() {}
	
	@Override
	public StudentEntity saveStudent(StudentEntity students) {
		return studentRepository.save(students);
	}

	@Override
	public List<StudentEntity> fetchStudentList() {
		List<StudentEntity> studentList = studentRepository.findAll();
		
		if(studentList.size() > 0)
			return studentList;
		else
			return new ArrayList<StudentEntity>();
		
	}

	@Override
	public StudentEntity getStudentById(int id) throws RecordNotFoundException{
		Optional<StudentEntity> student = studentRepository.findById(id);
		//StudentEntity student = studentRepository.findById(id).orElse(null);
		//if(student != null)
		if(student.isPresent())
			return student.get();
		else {
			throw new RecordNotFoundException("No student record exists for given id " + id);
		}
	}
	@Override
	public List<StudentEntity> getStudentByName(String name) throws RecordNotFoundException {
		List<StudentEntity> student = studentRepository.getStudentByName(name);
		
		if(student.isEmpty() || name.equals(student.get(0).getName())) {
			throw new RecordNotFoundException("No student record exists for given name " + name);
		}else {
			return student;
		}
	}
	
	@Override
	public StudentEntity updateStudent(StudentEntity studentEntity, int studentId) throws RecordNotFoundException{
		//StudentEntity student = studentRepository.findById(studentId).get();
		
		Optional<StudentEntity> student = studentRepository.findById(studentId);
		
		if(student.isPresent()) {
			StudentEntity newStudent = student.get();
			newStudent.setName(studentEntity.getName());
			newStudent.setEmail(studentEntity.getEmail());
			newStudent.setContactNo(studentEntity.getContactNo());
			
			newStudent = studentRepository.save(newStudent);
			return newStudent;
		}else {
			studentEntity = studentRepository.save(studentEntity);
			return studentEntity;	
		}
		
	}

	@Override
	public void deleteStudentById(int studentId) throws RecordNotFoundException {
		Optional<StudentEntity> student = studentRepository.findById(studentId);
		
		if(student.isPresent()) {
			studentRepository.deleteById(studentId);
		}	else {
			throw new RecordNotFoundException("No student record exists for given id");
		}
	}

	

}
