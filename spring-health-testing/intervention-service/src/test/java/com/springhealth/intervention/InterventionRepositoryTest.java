package com.springhealth.intervention;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.springhealth.intervention.domain.Intervention;
import com.springhealth.intervention.repository.InterventionRepository;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InterventionRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private InterventionRepository interventionRepository;

	@Test
	public void testFindInterventionByUserId() throws Exception {				
		this.entityManager.persist(new Intervention(1L, 1L, 100F, "Intervention1", new Date()));
		this.entityManager.persist(new Intervention(1L, 2L, 200F, "Intervention2", new Date()));
		
		Long userId = 1L;
		List<Intervention> interventions = this.interventionRepository.findInterventionsByUserId(userId);
		assertThat(interventions).size().isEqualTo(2);
		Intervention actual = interventions.get(0);
		assertThat(actual.getUserId()).isEqualTo(userId);
	}
	
	@Test
	public void testFindInterventionByDeviceId() throws Exception {				
		this.entityManager.persist(new Intervention(1L, 1L, 100F, "Intervention1", new Date()));
		this.entityManager.persist(new Intervention(1L, 1L, 200F, "Intervention2", new Date()));
		
		Long deviceId = 1L;
		List<Intervention> interventions = this.interventionRepository.findInterventionsByDeviceId(deviceId);
		assertThat(interventions).size().isEqualTo(2);
		Intervention actual = interventions.get(0);
		assertThat(actual.getUserId()).isEqualTo(deviceId);
	}

	@Test
	public void testFindInterventionByNonExistedUserId() throws Exception {				
		this.entityManager.persist(new Intervention(1L, 1L, 100F, "Intervention1", new Date()));
		this.entityManager.persist(new Intervention(1L, 2L, 200F, "Intervention2", new Date()));
		
		Long userId = 3L;
		List<Intervention> interventions = this.interventionRepository.findInterventionsByUserId(userId);
		assertThat(interventions).size().isEqualTo(0);
	}
}
