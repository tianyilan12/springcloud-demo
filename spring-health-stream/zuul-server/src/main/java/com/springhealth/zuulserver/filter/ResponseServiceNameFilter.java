package com.springhealth.zuulserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ResponseServiceNameFilter extends ZuulFilter{
    private static final Logger logger = LoggerFactory.getLogger(ResponseServiceNameFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {    	
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.get("serviceId") != null) {
        	String serviceName = ctx.get("serviceId").toString();
        	logger.debug(String.format("Current service is {}.",  serviceName));
        	
        	ctx.getResponse().addHeader("ServiceName", serviceName);       	
        }
        	
        return null;
    }
}