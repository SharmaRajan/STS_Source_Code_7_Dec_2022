package com.nic.nrlm_aajeevika.usermanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer levelId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Boolean emailVerified;
    private Boolean mobileVerified;
    private String txnId;
    private String resetPasswordToken;
    private Integer resetPasswordExpires;
    private String isDeleted;
    private String token;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    //    private Date deletedAt;
    private Date blockedAt;
    private Integer createdBy;
    private Integer updatedBy;
    @Column(name = "is_deleted_by")
    private Integer isDeletedBy;

   public User() {
   }

   public User(long id, Integer levelId, String name, String username, String password, String email, String mobile, Boolean emailVerified, Boolean mobileVerified, String txnId, String resetPasswordToken, Integer resetPasswordExpires, String isDeleted, Date createdAt, Date updatedAt, Date blockedAt, Integer createdBy, Integer updatedBy, Integer isDeletedBy) {
      this.id = id;
      this.levelId = levelId;
      this.name = name;
      this.username = username;
      this.password = password;
      this.email = email;
      this.mobile = mobile;
      this.emailVerified = emailVerified;
      this.mobileVerified = mobileVerified;
      this.txnId = txnId;
      this.resetPasswordToken = resetPasswordToken;
      this.resetPasswordExpires = resetPasswordExpires;
      this.isDeleted = isDeleted;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.blockedAt = blockedAt;
      this.createdBy = createdBy;
      this.updatedBy = updatedBy;
      this.isDeletedBy = isDeletedBy;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Integer getLevelId() {
      return levelId;
   }

   public void setLevelId(Integer levelId) {
      this.levelId = levelId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public Boolean getEmailVerified() {
      return emailVerified;
   }

   public void setEmailVerified(Boolean emailVerified) {
      this.emailVerified = emailVerified;
   }

   public Boolean getMobileVerified() {
      return mobileVerified;
   }

   public void setMobileVerified(Boolean mobileVerified) {
      this.mobileVerified = mobileVerified;
   }

   public String getTxnId() {
      return txnId;
   }

   public void setTxnId(String txnId) {
      this.txnId = txnId;
   }

   public String getResetPasswordToken() {
      return resetPasswordToken;
   }

   public void setResetPasswordToken(String resetPasswordToken) {
      this.resetPasswordToken = resetPasswordToken;
   }

   public Integer getResetPasswordExpires() {
      return resetPasswordExpires;
   }

   public void setResetPasswordExpires(Integer resetPasswordExpires) {
      this.resetPasswordExpires = resetPasswordExpires;
   }

   public String getIsDeleted() {
      return isDeleted;
   }

   public void setIsDeleted(String isDeleted) {
      this.isDeleted = isDeleted;
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

   public Date getBlockedAt() {
      return blockedAt;
   }

   public void setBlockedAt(Date blockedAt) {
      this.blockedAt = blockedAt;
   }

   public Integer getCreatedBy() {
      return createdBy;
   }

   public void setCreatedBy(Integer createdBy) {
      this.createdBy = createdBy;
   }

   public Integer getUpdatedBy() {
      return updatedBy;
   }

   public void setUpdatedBy(Integer updatedBy) {
      this.updatedBy = updatedBy;
   }

   public Integer getIsDeletedBy() {
      return isDeletedBy;
   }

   public void setIsDeletedBy(Integer isDeletedBy) {
      this.isDeletedBy = isDeletedBy;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }
}
