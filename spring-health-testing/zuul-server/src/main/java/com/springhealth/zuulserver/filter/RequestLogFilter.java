package com.springhealth.zuulserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RequestLogFilter extends ZuulFilter{
    private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public String filterType() {
        return "pre";
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
        logger.debug(String.format("Processing incoming request for {}.",  
        		ctx.getRequest().getRequestURI()));
        
        return null;
    }
}