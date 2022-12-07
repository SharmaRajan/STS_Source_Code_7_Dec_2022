package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.Grampanchayat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GpRepository extends JpaRepository<Grampanchayat, String> {

	@Query("Select gp FROM Grampanchayat gp JOIN Block bk on bk.blockCode = gp.blockCode Join District dt on dt.districtCode = bk.districtCode where dt.stateCode IN (:locationcode)")
	Page<Grampanchayat> findAllByStatecode(List<Integer> locationcode, Pageable pageable);

	@Query("Select gp FROM Grampanchayat gp JOIN Block bk on bk.blockCode = gp.blockCode where bk.districtCode IN (:discode)")
	Page<Grampanchayat> findAllBydiscode(List<Integer> discode, Pageable pageable);

	@Query("Select gp FROM Grampanchayat gp where gp.blockCode IN (:blockcode)")
	Page<Grampanchayat> findAllByblockcode(List<Integer> blockcode, Pageable pageable);

	@Query("Select gp FROM Grampanchayat gp where grampanchayatCode IN (:gpcode)")
	Page<Grampanchayat> findAllBygpcode(List<Integer> gpcode, Pageable pageable);

	public Page<Grampanchayat> findAll(Specification<Grampanchayat> spec, Pageable pageable);
	
	Optional<Grampanchayat> findByGrampanchayatCode(String gpCode);
}
