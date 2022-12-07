package com.example.Student.service;

import java.util.List;

import com.example.Student.entities.StudentEntity;
import com.example.Student.exception.RecordNotFoundException;

public interface StudentService {
	
	// Save operation
	StudentEntity saveStudent(StudentEntity students);
 
    // Read operation
    List<StudentEntity> fetchStudentList();
 
    public StudentEntity getStudentById(int id) throws RecordNotFoundException;
    
    public List<StudentEntity> getStudentByName(String name) throws RecordNotFoundException;
    
    // Update operation
    StudentEntity updateStudent(StudentEntity student,int studentId) throws RecordNotFoundException;
 
    // Delete operation
    void deleteStudentById(int studentId) throws RecordNotFoundException;

}
