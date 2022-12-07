package com.sharmajee.StudentMaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharmajee.StudentMaster.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

}
