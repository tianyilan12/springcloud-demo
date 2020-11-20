package com.springhealth.zuulserver.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import brave.Span;
import brave.Tracer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TraceFilter extends ZuulFilter{
    
    @Autowired
    Tracer tracer;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        
        ctx.getResponse().addHeader("trace-id", tracer.currentSpan().context().traceIdString());
         
        ctx.getResponse().addHeader("span-id", String.valueOf(tracer.currentSpan().context().spanIdString()));
        
        return null;
    }
    
    
    void sample() {

    	String tagValue = null;

        Span newSpan = tracer.newTrace().name("newSpan").start();
        try {
        	
			// 给一个Span打Tag
        	newSpan.tag("tagName", tagValue);
        	// 在Span上记录事件
//        	newSpan.logEvent("eventOccured");
        } finally {
        	
        	//关闭Span
        	newSpan.finish();
        }
    }
}