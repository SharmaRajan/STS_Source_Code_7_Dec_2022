package com.sharmajee.students.service;

import java.util.List;

import com.sharmajee.students.entity.StudentEntity;

public interface StudentService {

	 // Save operation
    StudentEntity saveStudent(StudentEntity students);

    // Read operation
    List<StudentEntity> fetchStudentList();

    // Update operation
    StudentEntity updateDepartment(StudentEntity student, int studentId);

    // Delete operation
    void deleteStudentById(int studentId);
	
}
