package com.springhealth.intervention;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import com.springhealth.intervention.filter.AuthorizationHeaderInterceptor;



@SpringCloudApplication
@EnableResourceServer
public class InterventionApplication {

//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate(){
//		return new RestTemplate();
//	}
	
	@Primary
	@Bean
	public RestTemplate getCustomRestTemplate() {
		RestTemplate template = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		if (interceptors == null) {
			template.setInterceptors(Collections.singletonList(new AuthorizationHeaderInterceptor()));
		} else {
			interceptors.add(new AuthorizationHeaderInterceptor());
			template.setInterceptors(interceptors);
		}

		return template;
	}	
    
    public static void main(String[] args) {
        SpringApplication.run(InterventionApplication.class, args);
    }
}

