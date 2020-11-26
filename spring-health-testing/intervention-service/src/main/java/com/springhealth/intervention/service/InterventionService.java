package com.springhealth.intervention.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springhealth.intervention.client.UserServiceClient;
import com.springhealth.intervention.client.DeviceMapper;
import com.springhealth.intervention.client.DeviceServiceClient;
import com.springhealth.intervention.client.UserMapper;
import com.springhealth.intervention.domain.Intervention;
import com.springhealth.intervention.repository.InterventionRepository;

@Service
public class InterventionService {

	@Autowired
	private InterventionRepository interventionRepository;

	@Autowired
	private DeviceServiceClient deviceClient;

	@Autowired
	private UserServiceClient userClient;

	private static final Logger logger = LoggerFactory.getLogger(InterventionService.class);

//	@HystrixCommand
//	@HystrixCommand(threadPoolKey = "springHealthGroup",
//    threadPoolProperties =
//         {
//        		@HystrixProperty(name="coreSize",value="2"),
//        		@HystrixProperty(name="maxQueueSize",value="10")
//         },
//         fallbackMethod = "getInterventionsFallback"
//	)
	private UserMapper getUser(String userName) {

		return userClient.getUserByUserName(userName);
	}
	
	@SuppressWarnings("unused")
	private UserMapper getUserFallback(String userName) {

		UserMapper fallbackUser = new UserMapper(0L,"no_user","not_existed_user");
		
		return fallbackUser;
	}

//	@HystrixCommand(commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")
//	})
	private DeviceMapper getDevice(String deviceCode) {

//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		return deviceClient.getDeviceByDeviceCode(deviceCode);
	}

	public Intervention generateIntervention(String userName, String deviceCode) {
		
		logger.debug("Generate intervention record with user: {} from device: {}", userName, deviceCode);
		
		Intervention intervention = new Intervention();		

		//获取远程Device信息
		DeviceMapper device = getDevice(deviceCode);
		if (device == null) {
			return intervention;
		}		
		logger.debug("Get remote device: {} is successful", deviceCode);		

		//获取远程User信息
		UserMapper user = getUser(userName);
		if (user == null) {
			return intervention;
		}		
		logger.debug("Get remote user: {} is successful", userName);
		

		//创建并保存Intervention信息
		intervention.setUserId(user.getId());
		intervention.setDeviceId(device.getId());
		intervention.setHealthData(device.getHealthData());
		intervention.setIntervention("InterventionForDemo");
		intervention.setCreateTime(new Date());

		interventionRepository.save(intervention);

		return intervention;
	}

//	@HystrixCommand(commandProperties = {			
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
//			//一个滑动窗口内最小的请求数
//			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//			//错误比率阀值
//			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
//			//触发熔断的时间值
//			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
//			//一个滑动窗口的时间长度
//			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
//			//一个滑动窗口被划分的数量
//			@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
	
	@HystrixCommand(fallbackMethod = "getInterventionsFallback")
	public List<Intervention> getInterventions(int pageIndex, int pageSize) {
//		if (1 == 1) {
//			try {
//				throw new Exception("An exception has occured!");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		return interventionRepository.findAll(
				PageRequest.of(pageIndex - 1, pageSize, Sort.DEFAULT_DIRECTION))
				.getContent();
	}

	@SuppressWarnings("unused")
	private List<Intervention> getInterventionsFallback(int pageIndex, int pageSize) {
		List<Intervention> fallbackList = new ArrayList<>();

		Intervention intervention = new Intervention();
		intervention.setUserId(0L);
		intervention.setDeviceId(0L);
		intervention.setHealthData(0F);
		intervention.setIntervention("Intervention list is not available");
		intervention.setCreateTime(new Date());

		fallbackList.add(intervention);
		return fallbackList;
	}

	public Intervention getInterventionById(Long id) {
		return interventionRepository.getOne(id);
	}
}
