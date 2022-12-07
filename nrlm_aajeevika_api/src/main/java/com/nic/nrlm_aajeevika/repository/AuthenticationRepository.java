package com.nic.nrlm_aajeevika.repository;

import com.nic.nrlm_aajeevika.usermanagement.entity.User;
import com.nic.nrlm_aajeevika.usermanagement.entity.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {

    //    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUsername(String username);

	@Query(value="Select r from UserLevel r where r.id = :id ")
    List<UserLevel> getUserRoles(Long id);

}
