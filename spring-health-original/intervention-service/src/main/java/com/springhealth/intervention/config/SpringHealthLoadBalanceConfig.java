package com.springhealth.intervention.config;
//package com.tianyalan.orders.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
//
//@Configuration
//public class SpringHealthLoadBalanceConfig {
//
//    @Autowired
//    IClientConfig config;
//
//    @Bean
//    @ConditionalOnMissingBean
//    public IRule ribbonRule(IClientConfig config) {
//    	
//        return new RandomRule();
//    }
//}