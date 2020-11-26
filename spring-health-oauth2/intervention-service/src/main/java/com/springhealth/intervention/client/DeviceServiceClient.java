package com.springhealth.intervention.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeviceServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceClient.class);
	
    @Autowired
    OAuth2RestTemplate restTemplate;    
//    RestTemplate restTemplate;

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