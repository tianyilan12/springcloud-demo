package com.springhealth.intervention;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springhealth.intervention.client.DeviceMapper;
import com.springhealth.intervention.client.DeviceServiceClient;
import com.springhealth.intervention.client.UserMapper;
import com.springhealth.intervention.client.UserServiceClient;
import com.springhealth.intervention.domain.Intervention;
import com.springhealth.intervention.repository.InterventionRepository;
import com.springhealth.intervention.service.InterventionService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class InterventionServiceTests {

	@MockBean
	private UserServiceClient userClient;

	@MockBean
	private DeviceServiceClient deviceClient;

	@MockBean
	private InterventionRepository interventionRepository;

	@Autowired
	private InterventionService interventionService;
	
	@Test
	public void testGenerateIntervention() throws Exception {
		String userName = "springhealth_user1";
		String deviceCode = "device1";		
	
		given(this.userClient.getUserByUserName(userName))
			.willReturn(new UserMapper(1L, "user1", userName));
		given(this.deviceClient.getDeviceByDeviceCode(deviceCode))
			.willReturn(new DeviceMapper(1L, "便携式血压计", "device1", "Sphygmomanometer", 100F));

		Intervention actual = interventionService.generateIntervention(userName, deviceCode);

		assertThat(actual.getHealthData()).isEqualTo(100L);
	}
}