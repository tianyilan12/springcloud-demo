package com.springhealth.intervention.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.springhealth.intervention.client.UserMapper;

import javax.annotation.PostConstruct;

@Repository
public class UserInfoRedisRepositoryImpl implements UserInfoRedisRepository {
	private static final String HASH_NAME = "user";

	private RedisTemplate<String, UserMapper> redisTemplate;
	private HashOperations<String, String, UserMapper> hashOperations;

	public UserInfoRedisRepositoryImpl() {
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
		hashOperations.put(HASH_NAME, user.getUserName(), user);
	}

	@Override
	public void updateUser(UserMapper user) {
		hashOperations.put(HASH_NAME, user.getUserName(), user);
	}

	@Override
	public void deleteUser(String userName) {
		hashOperations.delete(HASH_NAME, userName);
	}

	@Override
	public UserMapper findUserByUserName(String userName) {
		return (UserMapper) hashOperations.get(HASH_NAME, userName);
	}
}
