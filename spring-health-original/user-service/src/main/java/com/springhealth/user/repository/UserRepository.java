package com.springhealth.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springhealth.user.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {

	User findUserByUserName(String userName);
}
