package com.springhealth.intervention.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Intervention {

	@Id
	@GeneratedValue
	private Long id; 	
	private Long userId;	
	private Long deviceId;
	private Float healthData;
	private String intervention;
	private Date createTime;
	
	public Intervention() {		
	}
	
	public Intervention(Long userId, Long deviceId, Float healthData, String intervention, Date createTime) {
		super();
		this.userId = userId;
		this.deviceId = deviceId;
		this.healthData = healthData;
		this.intervention = intervention;
		this.createTime = createTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Float getHealthData() {
		return healthData;
	}
	public void setHealthData(Float healthData) {
		this.healthData = healthData;
	}
	public String getIntervention() {
		return intervention;
	}
	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
