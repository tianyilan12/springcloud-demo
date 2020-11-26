package com.springhealth.intervention.filter;

public class AuthorizationHeaderHolder {
    private static final ThreadLocal<AuthorizationHeader> authorizationHeaderContext = new ThreadLocal<AuthorizationHeader>();

    public static final AuthorizationHeader getAuthorizationHeader(){
        AuthorizationHeader header = authorizationHeaderContext.get();

        if (header == null) {
        	header = new AuthorizationHeader();
            authorizationHeaderContext.set(header);

        }
        return authorizationHeaderContext.get();
    }

    public static final void setAuthorizationHeader(AuthorizationHeader header) {
        authorizationHeaderContext.set(header);
    }
}
