package com.nrlm.mclfmis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.StatutoryLicenseEntity;

@Repository
public interface StatutoryLicenseRepository extends JpaRepository<StatutoryLicenseEntity, Long> {

	Optional<StatutoryLicenseEntity> findByComplianceIdAndLicenseSelected(Long complianceId, Integer licenseSelected);

	void deleteAllByComplianceIdAndLicenseSelectedNotIn(Long id, List<Integer> licSelectedList);

	void deleteAllByComplianceId(Long id);

	List<StatutoryLicenseEntity> findAllByComplianceId(Long id);
}
