package com.springhealth.intervention.repository;

import com.springhealth.intervention.client.UserMapper;

public interface UserInfoRedisRepository {
	
    void saveUser(UserMapper user);
    
    void updateUser(UserMapper user);
    
    void deleteUser(String userName);
    
    UserMapper findUserByUserName(String userName);
}
