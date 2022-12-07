package com.nic.nrlm_aajeevika.usermanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "login_history")
public class LoginHistory {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	   
	   @Column(name = "user_id")
	    private Integer userId;
	    
	    @Column(name = "login_id")
	  private String loginId;
	    
	   @Column(name = "user_agent")
	    private String userAgent;
	    
	    @Column(name = "type")
	    private String type;
	    
	    
	    @Column(name = "lat")
	    private String lat;
	    
	    
	    @Column(name = "lng")
	    private String lng;
	    
	    
	    @Column(name = "logout_location_lat")
	    private String logoutLocationLat;
	    
	    @Column(name = "logout_location_lng")
	    private String logoutLocationLng;
	    
	    @Column(name="ip_address")
	    private String ipAddress;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "logged_in_at")
	    private Date loggedInAt;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "logged_out_at")
	    private Date loggedOutAt;
	 
	    @CreationTimestamp
	    @Column(name = "created_at", nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdAt;
	    @UpdateTimestamp
	    @Column(name = "updated_at", nullable = false, updatable = true)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date updatedAt;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}

		public String getUserAgent() {
			return userAgent;
		}
		public void setUserAgent(String userAgent) {
			this.userAgent = userAgent;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLogoutLocationLat() {
			return logoutLocationLat;
		}
		public void setLogoutLocationLat(String logoutLocationLat) {
			this.logoutLocationLat = logoutLocationLat;
		}
		public String getLogoutLocationLng() {
			return logoutLocationLng;
		}
		public void setLogoutLocationLng(String logoutLocationLng) {
			this.logoutLocationLng = logoutLocationLng;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		public Date getLoggedInAt() {
			return loggedInAt;
		}
		public void setLoggedInAt(Date loggedInAt) {
			this.loggedInAt = loggedInAt;
		}
		public Date getLoggedOutAt() {
			return loggedOutAt;
		}
		public void setLoggedOutAt(Date loggedOutAt) {
			this.loggedOutAt = loggedOutAt;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getLoginId() {
			return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
	
	    
	  
}
