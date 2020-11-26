package com.springhealth.intervention;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringCloudApplication
public class InterventionApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
    
    public static void main(String[] args) {
        SpringApplication.run(InterventionApplication.class, args);
    }
}

