package com.nrlm.mclfmis.Entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "location_mapper")
public class LocationMapper {

    @Id
    private Integer id;
    private Integer userid;
    @Column(name = "location_id")
    private String locationId;
    @Column(name = "level_id")
    private Integer levelId;
    private String status;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updatedAt")
    private String updated_at;

    public LocationMapper() {
    }

    public LocationMapper(Integer id, Integer userid, String locationId, Integer levelId, String status, String createdAt, String updated_at) {
        this.id = id;
        this.userid = userid;
        this.locationId = locationId;
        this.levelId = levelId;
        this.status = status;
        this.createdAt = createdAt;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
