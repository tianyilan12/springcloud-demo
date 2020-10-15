package com.springhealth.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springhealth.user.domain.User;
import com.springhealth.user.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userId") Long userId) {

		logger.info("Get user by id: {} ", userId);

		User user = userService.getUserById(userId);
		return user;
	}

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "username/{userName}", method = RequestMethod.GET)
	public User getUserByUserName(@PathVariable("userName") String userName) {

		logger.info("Get user by userName from {} port of userservice instance", request.getServerPort());

		// Mock数据
		// User user = new User();
		// user.setId(001L);
		// user.setUserCode("mockUser");
		// user.setUserName(userName);

		User user = userService.getUserByUserName(userName);
		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("userId") Long userId) {
		User user = new User();
		user.setId(userId);

		userService.deleteUser(user);
	}
}
