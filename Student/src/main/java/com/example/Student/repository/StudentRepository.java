package com.example.Student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Student.entities.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

//	JPQL query: Java persistent query language
	
	@Query("SELECT std FROM StudentEntity std")
	public List<StudentEntity> getAllStudents();
	
	// :
	@Query("SELECT usr FROM StudentEntity usr WHERE usr.name=:studentName")
	public List<StudentEntity> getStudentByName(@Param("studentName") String name);
	
//	native query: we write sql query
	@Query(value="select * from StudentEntity", nativeQuery=true)
	public List<StudentEntity> getStudents();
	
}
