package com.sharmajee.repository;

import org.springframework.data.repository.CrudRepository;

import com.sharmajee.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
