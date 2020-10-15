package com.springhealth.intervention.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springhealth.intervention.domain.Intervention;
import com.springhealth.intervention.service.InterventionService;

@RestController
@RequestMapping(value="interventions")
public class InterventionController {

    private static final Logger logger = LoggerFactory.getLogger(InterventionController.class);
    
    @Autowired
    private InterventionService interventionService; 
	
	@RequestMapping(value = "/{userName}/{deviceCode}", method = RequestMethod.POST)
	public Intervention generateIntervention( @PathVariable("userName") String userName,
            @PathVariable("deviceCode") String deviceCode) {
		
		logger.info("Generate intervention for userName: {} and deviceCode: {}.", userName, deviceCode);
		
		Intervention intervention = interventionService.generateIntervention(userName, deviceCode);		
		
		return intervention;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Intervention getIntervention(@PathVariable Long id) {
		Intervention intervention = interventionService.getInterventionById(id);
		
    	return intervention;
    }
	
	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public List<Intervention> getInterventionList( @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		List<Intervention> interventions = interventionService.getInterventions(pageIndex, pageSize);
		
		return interventions;
	}
	
}
