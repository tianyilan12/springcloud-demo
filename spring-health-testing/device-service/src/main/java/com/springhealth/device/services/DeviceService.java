package com.springhealth.device.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhealth.device.domain.Device;
import com.springhealth.device.repository.DeviceRepository;

@Service
public class DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;	
	
	public Device getDeviceByCode(String deviceCode) {
		
		return deviceRepository.findDeviceByDeviceCode(deviceCode);   
	}
}

