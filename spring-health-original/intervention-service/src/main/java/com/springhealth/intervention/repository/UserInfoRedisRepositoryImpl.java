package com.springhealth.intervention.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.springhealth.intervention.client.UserMapper;

import javax.annotation.PostConstruct;

@Repository
public class UserInfoRedisRepositoryImpl implements UserInfoRedisRepository {
    private static final String HASH_NAME ="user";

    private RedisTemplate<String, UserMapper> redisTemplate;
    private HashOperations<String, Long, UserMapper> hashOperations;

    public UserInfoRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private UserInfoRedisRepositoryImpl(RedisTemplate<String, UserMapper> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveUser(UserMapper user) {
        hashOperations.put(HASH_NAME, user.getId(), user);
    }

    @Override
    public void updateUser(UserMapper user) {
        hashOperations.put(HASH_NAME, user.getId(), user);
    }

    @Override
    public void deleteUser(Long userId) {
        hashOperations.delete(HASH_NAME, userId);
    }

    @Override
    public UserMapper findUserById(Long userId) {
       return (UserMapper) hashOperations.get(HASH_NAME, userId);
    }

	@Override
	public UserMapper findUserByUserName(String userName) {
		return (UserMapper) hashOperations.get(HASH_NAME, userName);
	}
}
