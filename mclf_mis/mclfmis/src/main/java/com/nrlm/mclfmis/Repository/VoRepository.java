package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.VoProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoRepository extends JpaRepository<VoProfile, String> {

	@Query("Select vp FROM VoProfile vp JOIN Grampanchayat gp on gp.grampanchayatCode = vp.gpCode JOIN Block bk on bk.blockCode = gp.blockCode Join District dt on dt.districtCode = bk.districtCode where dt.stateCode IN (:locationcode)")
	Page<VoProfile> findAllByStatecode(List<Integer> locationcode, Pageable pageable);

	@Query("Select vp FROM VoProfile vp JOIN Grampanchayat gp on gp.grampanchayatCode = vp.gpCode JOIN Block bk on bk.blockCode = gp.blockCode where bk.districtCode IN (:discode)")
	Page<VoProfile> findAllBydiscode(List<Integer> discode, Pageable pageable);

	@Query("Select vp FROM VoProfile vp JOIN Grampanchayat gp on gp.grampanchayatCode = vp.gpCode where gp.blockCode IN (:blockcode)")
	Page<VoProfile> findAllByblockcode(List<Integer> blockcode, Pageable pageable);

	@Query("Select vp FROM VoProfile vp where gpCode IN (:gpcode)")
	Page<VoProfile> findAllBygpcode(List<Integer> gpcode, Pageable pageable);

	public Page<VoProfile> findAll(Specification<VoProfile> spec, Pageable pageable);
	
	Optional<VoProfile> findByVoCode(String voCode);

}
