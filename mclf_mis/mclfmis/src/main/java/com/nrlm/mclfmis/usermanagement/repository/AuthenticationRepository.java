package com.nrlm.mclfmis.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.usermanagement.entity.LocationEntity;
import com.nrlm.mclfmis.usermanagement.entity.UserEntity;


@Repository
public interface AuthenticationRepository extends JpaRepository<UserEntity,String>,JpaSpecificationExecutor<UserEntity>,CustomRepository {

	Optional<UserEntity> findByUserId(Long userId);

	

	

	

	

}
