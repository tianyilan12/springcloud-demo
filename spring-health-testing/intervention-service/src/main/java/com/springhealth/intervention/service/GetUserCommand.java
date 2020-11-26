package com.springhealth.intervention.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.springhealth.intervention.client.UserMapper;
import com.springhealth.intervention.client.UserServiceClient;

public class GetUserCommand extends HystrixCommand<UserMapper> {
	
	//远程调用user-service的客户端工具类
	private UserServiceClient userServiceClient;
 
	protected GetUserCommand(String name) {
		super(Setter.withGroupKey(		 
			//设置命令组
			HystrixCommandGroupKey.Factory.asKey("springHealthGroup"))
				//设置命令键
				.andCommandKey(HystrixCommandKey.Factory.asKey("interventionKey"))
				//设置线程池键
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
				//设置命令属性
				.andCommandPropertiesDefaults(
					HystrixCommandProperties.Setter()
						.withExecutionTimeoutInMilliseconds(5000))
				//设置线程池属性
				.andThreadPoolPropertiesDefaults(
					HystrixThreadPoolProperties.Setter()
						.withMaxQueueSize(10)
						.withCoreSize(2))
		);
	}

	@Override
	protected UserMapper run() throws Exception {
		return userServiceClient.getUserByUserName("springhealth_user1");
	}

	@Override
	protected UserMapper getFallback() {
		return new UserMapper(1L,"user1","springhealth_user1");
	}
}
