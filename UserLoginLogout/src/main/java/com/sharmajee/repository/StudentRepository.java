package com.sharmajee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharmajee.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
