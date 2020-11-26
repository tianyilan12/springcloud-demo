package com.springhealth.intervention;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;


@SpringCloudApplication
@EnableResourceServer
public class InterventionApplication {

//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate(){
//		return new RestTemplate();
//	}
	
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext context,
                                                 OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, context);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(InterventionApplication.class, args);
    }
}

