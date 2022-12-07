package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.Block;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, String> {

    @Query("Select bk FROM Block bk Join District dt on dt.districtCode = bk.districtCode where dt.stateCode IN (:locationcode)")
    public Page<Block> findAllByStatecode(List<Integer> locationcode, Pageable pageable);

    @Query("Select bk FROM Block bk where districtCode IN (:discode)")
    public Page<Block> findAllBydiscode(List<Integer> discode, Pageable pageable);

    @Query("Select bk FROM Block bk where blockCode IN (:blockcode)")
    public Page<Block> findAllByblockcode(List<Integer> blockcode, Pageable pageable);
    
    public Page<Block> findAll(Specification<Block> spec, Pageable pageable);

    
}
