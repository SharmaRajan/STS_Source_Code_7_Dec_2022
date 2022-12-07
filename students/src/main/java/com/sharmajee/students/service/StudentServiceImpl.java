package com.sharmajee.students.service;

import org.springframework.stereotype.Service;

import com.sharmajee.students.entity.StudentEntity;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentEntity saveStudent(StudentEntity students) {
        return studentRepository.save(students);
    }

    @Override
    public List<StudentEntity> fetchStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity updateDepartment(StudentEntity studentEntity, int studentId) {
        StudentEntity student = studentRepository.findById(studentId).get();

        student.setName(studentEntity.getName());
        student.setEmail(studentEntity.getEmail());
        student.setContactNo(studentEntity.getContactNo());

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
    }
}
