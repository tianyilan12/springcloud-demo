package com.springhealth.user.event;

import com.springhealth.user.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoChangedSource {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoChangedSource.class);
  
    @Autowired
    public UserInfoChangedSource(Source source){
        this.source = source;
    }

    private void publishUserInfoChangedEvent(UserInfoOperation operation, User user){
    	logger.debug("Sending message for UserId: {}", user.getId());
    	
        UserInfoChangedEvent change =  new UserInfoChangedEvent(
    		   UserInfoChangedEvent.class.getTypeName(),
    		   operation.toString(),
    		   user);

        source.output().send(MessageBuilder.withPayload(change).build());
    }
    
    public void publishUserInfoAddedEvent(User user) {
    	publishUserInfoChangedEvent(UserInfoOperation.ADD, user);
    }
    
    public void publishUserInfoUpdatedEvent(User user) {
    	publishUserInfoChangedEvent(UserInfoOperation.UPDATE, user);
    }
    
    public void publishUserInfoDeletedEvent(User user) {
    	publishUserInfoChangedEvent(UserInfoOperation.DELETE, user);
    }
}
