package com.springhealth.intervention.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

	@Value("$(orderservice.customProperty)")
	private String customProperty;

	@Value("${redis.server}")
	private String redisServer = "";

	@Value("${redis.port}")
	private String redisPort = "";

	public String getCustomProperty() {
		return customProperty;
	}

	public String getRedisServer() {
		return redisServer;
	}

	public Integer getRedisPort() {
		return new Integer(redisPort).intValue();
	}

}
