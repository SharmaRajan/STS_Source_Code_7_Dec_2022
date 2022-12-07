package com.example.Student.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.Student.entities.StudentEntity;
import com.example.Student.exception.RecordNotFoundException;
import com.example.Student.repository.StudentRepository;
import com.example.Student.response.StudentErrorResponse;
import com.example.Student.service.StudentService;
import com.example.Student.service.StudentServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
//	@PostMapping
//	public StudentEntity saveStudent(@RequestBody StudentEntity student) {
//		return studentServiceImpl.saveStudent(student);
//	}
//	
//	
//	@GetMapping
//	public List<StudentEntity> fetchStudentList(){
//		return studentServiceImpl.fetchStudentList();
//	}
//	
//	
//	@PutMapping("/{id}")
//	public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity, @PathVariable("id") Integer studentId)	{
//		return studentServiceImpl.updateStudent(studentEntity, studentId);
//	}
//	
//	
//	@DeleteMapping("/{id}")
//	public String deleteStudentById(@PathVariable("id") Integer studentId)	{
//		studentServiceImpl.deleteStudentById(studentId);
//		return "Deleted Successfully";
//	}
	
	/**** ***/
	
	@PostMapping
	public ResponseEntity<StudentEntity> saveStudent(StudentEntity student) {
		StudentEntity updatedEntity = studentService.saveStudent(student);
		return new ResponseEntity<StudentEntity>(updatedEntity,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<StudentEntity>> fetchStudentList() {
		List<StudentEntity> list = studentService.fetchStudentList();
		//List<StudentEntity> studentLists = studentRepo.getAllStudents();
		
//		studentLists.forEach(e->{
//			System.out.println(e);
//		});
		
		return new ResponseEntity<List<StudentEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentEntity> getStudentById(@PathVariable("id") Integer id) {
		StudentEntity student = studentService.getStudentById(id);

		if(id != student.getId()) {
			throw new RecordNotFoundException("Student with " + id + " not found in the records");
		}else {
			return new ResponseEntity<StudentEntity>(student, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
//	@GetMapping("/name/{studentName}")
//	public ResponseEntity<StudentEntity> getStudentByName(@PathVariable("studentName") String studentName) {
//		List<StudentEntity> studentList = studentRepo.getStudentByName(studentName);
//		
//		
//		studentList.forEach(e->{
//			if(!studentName.equals(e.getName())) {
//				throw new RecordNotFoundException("Student with " + studentName + " not found in the records");
//			}else {
//				//return new ResponseEntity<StudentEntity>(e, new HttpHeaders(), HttpStatus.OK);
//				System.out.println(e);
//			}
//		});
//		return new ResponseEntity<StudentEntity>((StudentEntity) studentList, new HttpHeaders(), HttpStatus.OK);
//	}
	
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteStudentById(@PathVariable("id") Integer studentId) {
		studentService.deleteStudentById(studentId);
		return HttpStatus.FORBIDDEN;
	}
	
	
	//Adding Exception handler
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<StudentErrorResponse> handleException(RecordNotFoundException exc)	{
		StudentErrorResponse errorResponse = new StudentErrorResponse();
		
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
}	



