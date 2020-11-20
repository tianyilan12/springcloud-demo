package com.springhealth.intervention.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.springhealth.intervention.domain.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {

	List<Intervention> findInterventionsByUserId(@Param("userId") Long userId);
	
	List<Intervention> findInterventionsByDeviceId(@Param("deviceId") Long deviceId);

}
