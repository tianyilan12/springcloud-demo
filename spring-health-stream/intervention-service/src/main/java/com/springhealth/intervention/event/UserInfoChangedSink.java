package com.springhealth.intervention.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.springhealth.intervention.repository.UserInfoRedisRepository;

@EnableBinding(UserInfoChangedChannel.class)
public class UserInfoChangedSink {

    @Autowired
    private UserInfoRedisRepository userInfoRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoChangedSink.class);

    @StreamListener(UserInfoChangedChannel.USER_INFO)
    public void handleChangedUserInfo(UserInfoChangedEventMapper userInfoChangedEventMapper) {
    	
        logger.debug("Received a message of type " + userInfoChangedEventMapper.getType()); 
    	logger.debug("Received a {} event from the user-service for user name {}", 
    			userInfoChangedEventMapper.getOperation(), 
    			userInfoChangedEventMapper.getUser().getUserName());
        
        if(userInfoChangedEventMapper.getOperation().equals("ADD")) {
            userInfoRedisRepository.saveUser(userInfoChangedEventMapper.getUser());
        } else if(userInfoChangedEventMapper.getOperation().equals("UPDATE")) {
        	userInfoRedisRepository.updateUser(userInfoChangedEventMapper.getUser());        	
        } else if(userInfoChangedEventMapper.getOperation().equals("DELETE")) {
        	userInfoRedisRepository.deleteUser(userInfoChangedEventMapper.getUser().getUserName());
        } else {            
            logger.error("Received an UNKNOWN event from the user-service of type {}", userInfoChangedEventMapper.getType());
        }
    }
}
