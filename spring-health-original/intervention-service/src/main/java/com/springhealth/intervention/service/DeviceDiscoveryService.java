package com.springhealth.intervention.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springhealth.intervention.client.DeviceMapper;

@Service
public class DeviceDiscoveryService {
	
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    
	public DeviceMapper getDevice(String deviceCode) {
		List<ServiceInstance> instances = discoveryClient.getInstances("deviceservice");

		if (instances.size() == 0)
			return null;

		String serviceUri = String.format("%s/devices/%s", instances.get(0).getUri().toString(), deviceCode);

		ResponseEntity<DeviceMapper> result = restTemplate.exchange(serviceUri, HttpMethod.GET, null, DeviceMapper.class,
				deviceCode);

		return result.getBody();
	}
}
