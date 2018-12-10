package com.snowy.comi5.sercurity;

import com.snowy.comi5.SpringApplicationContext;

public class SecurityConnstants {
	public static final long EXPIRATION_TIME = 864000000; // Ten days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
