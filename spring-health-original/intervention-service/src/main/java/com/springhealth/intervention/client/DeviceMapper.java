package com.springhealth.intervention.client;

import java.io.Serializable;

public class DeviceMapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;	
	private String deviceCode;
	private String deviceName;
	private String description;
	private Float healthData;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getHealthData() {
		return healthData;
	}
	public void setHealthData(Float healthData) {
		this.healthData = healthData;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}