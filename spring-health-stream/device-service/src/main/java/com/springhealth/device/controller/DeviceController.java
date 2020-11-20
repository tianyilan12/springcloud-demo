package com.springhealth.device.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springhealth.device.domain.Device;
import com.springhealth.device.services.DeviceService;

@RestController
@RequestMapping(value="devices")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    
	@Autowired
	DeviceService deviceService;
	
    @Autowired
    private HttpServletRequest request;
    	
	@RequestMapping(value = "/{deviceCode}", method = RequestMethod.GET)
    public Device getDevice(@PathVariable String deviceCode) {		

		logger.info("Get device by code: {} from port: {}", deviceCode, request.getServerPort());
		
		Device device = deviceService.getDeviceByCode(deviceCode);
    	return device;
    }
}
