package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.Grampanchayat;
import com.nrlm.mclfmis.customRepository.ClfCutOffMonthlyRepositoryCustom;
import com.nrlm.mclfmis.customRepository.ClfRepositoryCustom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClfRepository extends JpaRepository<ClfMaster, Integer>, ClfRepositoryCustom {
//    @Query(value = "select new com.nicusernmanagement.nicusernmanagement.Response.ModuleCustomResponse(m.module_cd,m.module_name,sm.form_cd,sm.form_name,m.scheme_name,m.scheme_cd,sm.url, sm.link ) "
//            +"from Mst_user u JOIN Mst_assign_role ar ON u.login_id = ar.login_id LEFT JOIN Mst_role_detail rd ON ar.role_cd = rd.role_cd LEFT JOIN Mst_module m "
//            +"ON rd.module_cd = m.module_cd LEFT JOIN Mst_form_module sm ON rd.form_cd = sm.form_cd WHERE u.login_id = ?1")
//    public List<ModuleCustomResponse> findModulesbyUser(String login_id);

    @Query("Select cm FROM ClfMaster cm JOIN Block bk on bk.blockCode = cm.blockCode Join District dt on dt.districtCode = bk.districtCode where dt.stateCode IN (:locationcode)")
    Page<ClfMaster> findAllByStatecode(List<Integer> locationcode, Pageable pageable);

    @Query("Select cm FROM ClfMaster cm JOIN Block bk on bk.blockCode = cm.blockCode where bk.districtCode IN (:discode)")
    Page<ClfMaster> findAllBydiscode(List<Integer> discode, Pageable pageable);

    @Query("Select cm FROM ClfMaster cm where cm.blockCode IN (:blockcode)")
    Page<ClfMaster> findAllByblockcode(List<Integer> blockcode, Pageable pageable);

    @Query("Select cm FROM ClfMaster cm JOIN Grampanchayat gp on gp.blockCode = cm.blockCode where gp.grampanchayatCode IN (:gpcode)")
    Page<ClfMaster> findAllBygpcode(List<Integer> gpcode, Pageable pageable);

    @Query("Select cm FROM ClfMaster cm where clfCode IN (:clfcode)")
    Page<ClfMaster> findAllByclfcode(List<Integer> clfcode, Pageable pageable);
    
   /* @Query("Select cm FROM ClfMaster cm where clfCode = :clfcode")
    Optional<ClfMaster> findByClfcode(@Param("clfcode") String clfcode);*/
    
	public Page<ClfMaster> findAll(Specification<ClfMaster> spec, Pageable pageable);

}
