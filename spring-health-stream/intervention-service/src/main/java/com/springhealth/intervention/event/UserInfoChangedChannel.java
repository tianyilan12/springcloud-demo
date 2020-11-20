package com.springhealth.intervention.event;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserInfoChangedChannel {
	
	String USER_INFO = "userInfoChangedChannel";
	
    @Input(UserInfoChangedChannel.USER_INFO)
    SubscribableChannel accountChangeChannel();
}
