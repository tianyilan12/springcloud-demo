package com.springhealth.user.filter;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationHeader {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    private String authorizationHeader = new String();

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public void setAuthorizationHeader(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }
}
