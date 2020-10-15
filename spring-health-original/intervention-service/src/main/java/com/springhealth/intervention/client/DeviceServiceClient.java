package com.springhealth.intervention.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeviceServiceClient {
	
    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceClient.class);

    public DeviceMapper getDeviceByDeviceCode(String deviceCode){

    	logger.debug("Get device: {}", deviceCode);

        ResponseEntity<DeviceMapper> restExchange =
                restTemplate.exchange(
                        "http://zuulservice:5555/springhealth/device/devices/{deviceCode}", 
                        HttpMethod.GET,
                        null, DeviceMapper.class, deviceCode);
         
        DeviceMapper device = restExchange.getBody();

        return device;
    }
}