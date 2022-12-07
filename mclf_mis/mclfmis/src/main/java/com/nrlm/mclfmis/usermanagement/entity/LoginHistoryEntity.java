package com.nrlm.mclfmis.usermanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="mclf_login_history",schema="history")
public class LoginHistoryEntity {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="login_id")
	private String loginId;
	
	@Column(name="client_address")
	private String clientAddress;
	
	@Column(name="client_device")
	private String clientDevice;
	
	@Column(name="status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="logged_time")
	private Date loggedTime;
	
	@Column(name="message")
	private String message;

	public LoginHistoryEntity() {
		super();
	}

	public LoginHistoryEntity(Long id, String loginId, String clientAddress, String clientDevice, Integer status,
			Date loggedTime,String msg) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.clientAddress = clientAddress;
		this.clientDevice = clientDevice;
		this.status = status;
		this.loggedTime = loggedTime;
		this.message = msg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientDevice() {
		return clientDevice;
	}

	public void setClientDevice(String clientDevice) {
		this.clientDevice = clientDevice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLoggedTime() {
		return loggedTime;
	}

	public void setLoggedTime(Date loggedTime) {
		this.loggedTime = loggedTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
