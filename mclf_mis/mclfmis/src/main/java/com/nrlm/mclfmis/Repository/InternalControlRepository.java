package com.nrlm.mclfmis.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nrlm.mclfmis.Entity.InternalControlEntity;

@Repository
public interface InternalControlRepository extends JpaRepository<InternalControlEntity, Long>{

}
