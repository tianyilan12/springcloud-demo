package com.springhealth.zuulserver.filter;


import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class JWTFilter extends ZuulFilter{

    public static final String AUTHORIZATION_HEADER     = "Authorization";


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
    	//获取JWTToken
    	RequestContext ctx = RequestContext.getCurrentContext();
    	String authorizationHeader = ctx.getRequest().getHeader(AUTHORIZATION_HEADER);
        String jwtToken = authorizationHeader.replace("Bearer ","");
        
        //解析JWTToken
        String[] split_string = jwtToken.split("\\.");
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(base64EncodedBody));
		JSONObject jsonObj = new JSONObject(body);
		
		//获取定制化属性值
        String systemName = jsonObj.getString("system");
        
        System.out.print(systemName);
        
        return null;		
    }
}