package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.LocationMapper;
import com.nrlm.mclfmis.usermanagement.entity.LocationEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationMapperRepository extends JpaRepository<LocationEntity, Integer> {
 //public List<LocationMapper> findAllByUserid(Integer userid);

public List<LocationEntity> findAllByUserIdAndLevel(Long userId, String level);
}
