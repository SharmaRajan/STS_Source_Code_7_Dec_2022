package com.nrlm.mclfmis.usermanagement.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.usermanagement.entity.LocationEntity;

@Repository
public class CustomRepositoryImpl implements CustomRepository{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUserAuthorities(String level) {
		String sql = "Select end_point from mclf_api_permission where level like '%"+ level + "%'";
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getModulePermissions(String level) {
		String sql = "Select module_name,permission from mclf_module_permission where level = '"+level + "'";
		Query query = em.createNativeQuery(sql);
		//query.setParameter(1, arg1)
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long checkUserAuthoritiesByLevelAndUri(String level, String uri) {
		String sql = "Select count(id) from mclf_api_permission where end_point = '"+uri+"' and level like '%"+ level + "%'";
		Query query = em.createNativeQuery(sql);
		BigInteger res = (BigInteger) query.getSingleResult();
		return res.longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationEntity> findUserLocations(Long userId) {
		String sql = "Select le from LocationEntity le where le.userId = "+userId ;
		Query query = em.createQuery(sql);
		//query.setParameter(1, arg1)
		return query.getResultList();
	}

	@Transactional
	@Override
	public Long saveUpdateUserLocation(LocationEntity le) {
		LocationEntity le1 = em.merge(le);
		return le1.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUserLocationDetails(String level,String locationCode) {
		String sql = "" ;
		if(level.equals(ProjectConstants.STATE)) {
			sql = "Select state_name from mst_state where state_code = '"+locationCode+"'";
		}
		else if(level.equals(ProjectConstants.DISTRICT)) {
			sql = "Select s.state_name,d.district_name "
				+ " from mst_district d"
				+ " join mst_state s on (d.state_code = s.state_code)"
				+ " where d.district_code = '"+locationCode+"'";
		}
		else if(level.equals(ProjectConstants.BLOCK)) {
			sql = "Select s.state_name,d.district_name,b.block_name"
					+ " from mst_block b"
					+ " join mst_district d on (b.district_code = d.district_code)"
					+ " join mst_state s on (d.state_code = s.state_code)"
					+ "where b.block_code = '"+locationCode+"'";
		}
		else if(level.equals(ProjectConstants.CLF)) {
			sql = "Select s.state_name,d.district_name,b.block_name,c.clf_name "
					+ " from clf_profile c"
					+ " join mst_block b on (c.block_code = b.block_code)"
					+ " join mst_district d on (d.district_code = b.district_code)"
					+ " join mst_state s on (s.state_code = b.state_code)"
					+ " where c.clf_code = '"+locationCode+"'";
		}
		
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void updateUserLastAccess(Long userId) {
		String sql = "Update mclf_mst_user set last_access = now() where  id = "+userId ;
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
		
	}

	@Transactional
	@Override
	public void updateUserLastAccessAndToken(Long userId, String token) {
		String sql = "Update mclf_mst_user set token = :token , last_access = now() where  id = "+userId ;
		Query query = em.createNativeQuery(sql).setParameter("token", token);
		query.executeUpdate();
		
	}

	/*@Transactional
	@Override
	public int updateUserLoginStatus(Long userId) {
		String sql = "Update mclf_mst_user set login_status = 0 where  id = "+userId ;
		Query query = em.createNativeQuery(sql);
		return query.executeUpdate();
	}*/

	/*@Override
	public void saveUserLocation(LocationEntity le) {
		em.merge(le);
		
	}*/

}
