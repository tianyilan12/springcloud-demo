package com.springhealth.intervention.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springhealth.intervention.repository.UserInfoRedisRepository;

@Component
public class UserServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceClient.class);
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    UserInfoRedisRepository userInfoRedisRepository;
    
    private UserMapper getUserFromCache(String userName) {
        try {
            return userInfoRedisRepository.findUserByUserName(userName);
        }
        catch (Exception ex){
            return null;
        }
    }

    private void putUserIntoCache(UserMapper user) {
        try {
        	userInfoRedisRepository.saveUser(user);
        }catch (Exception ex){
        }
    }
   
    public UserMapper getUserByUserName(String userName){
    	
    	logger.debug("Get user: {}", userName);
    	
    	ResponseEntity<UserMapper> restExchange =
                restTemplate.exchange(
                        "http://zuulservice:5555/springhealth/user/users/username/{userName}",
                        HttpMethod.GET,
                        null, UserMapper.class, userName);
                
        UserMapper user = restExchange.getBody();
        
        return user;
        
    	
//        UserMapper user = getUserFromCache(userName);
//        if (user != null){
//            return user;
//        }
//
//        ResponseEntity<UserMapper> restExchange =
//                restTemplate.exchange(
//                        "http://userservice/users/{userName}",
//                        HttpMethod.GET,
//                        null, UserMapper.class, userName);
//                
//        user = restExchange.getBody();
//        
//        if (user != null) {
//        	putUserIntoCache(user);
//        }
//        
//        return user;
    }
}
