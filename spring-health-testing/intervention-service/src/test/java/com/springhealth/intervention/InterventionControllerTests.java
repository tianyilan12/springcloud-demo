package com.springhealth.intervention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springhealth.intervention.controller.InterventionController;
import com.springhealth.intervention.domain.Intervention;
import com.springhealth.intervention.service.InterventionService;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InterventionController.class)
public class InterventionControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private InterventionService interventionService;

	@Test
	public void testGenerateIntervention() throws Exception {
		String userName = "springhealth_user1";
		String deviceCode = "device1";		
		Intervention intervention = new Intervention(1L, 1L, 100F, "Intervention1", new Date());		

		given(this.interventionService.generateIntervention(userName, deviceCode))
				.willReturn(intervention);

		this.mvc.perform(post("/interventions/" + userName+ "/" + deviceCode).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}