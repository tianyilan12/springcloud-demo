package com.springhealth.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhealth.user.domain.User;
//import com.springhealth.user.event.UserInfoChangedSource;
import com.springhealth.user.repository.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserInfoChangedSource userInfoChangedSource;

    public User getUserById(Long userId) {
        
        return userRepository.findById(userId).orElse(null); 
    }
    
    public User getUserByUserName(String userName) {
        
        return userRepository.findUserByUserName(userName);
    }

    public void addUser(User user){
    	userRepository.save(user);
        
//    	userInfoChangedSource.publishUserInfoAddedEvent(user);
    }

    public void updateUser(User user){
    	userRepository.save(user);
    	
//    	userInfoChangedSource.publishUserInfoUpdatedEvent(user);
    }

    public void deleteUser(User user){
    	userRepository.delete(user);
    	
//    	userInfoChangedSource.publishUserInfoDeletedEvent(user);
    }
}
