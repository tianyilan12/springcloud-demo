package com.springhealth.intervention.repository;

import com.springhealth.intervention.client.UserMapper;

public interface UserInfoRedisRepository {
	
    void saveUser(UserMapper user);
    
    void updateUser(UserMapper user);
    
    void deleteUser(Long userId);
    
    UserMapper findUserById(Long userId);
    
    UserMapper findUserByUserName(String userName);
}
