package com.snowy.comi5.sercurity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowy.comi5.SpringApplicationContext;
import com.snowy.comi5.dto.UserDto;
import com.snowy.comi5.model.request.UserLoginReqModel;
import com.snowy.comi5.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginReqModel creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginReqModel.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((User) authResult.getPrincipal()).getUsername();

		String token = Jwts.builder()
						.setSubject(username)
						.setExpiration(new Date(System.currentTimeMillis() + SecurityConnstants.EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS512,	SecurityConnstants.TOKEN_SECRET)
						.compact();
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		UserDto userDto = userService.getUser(username);
		
		response.addHeader(SecurityConnstants.HEADER_STRING, SecurityConnstants.TOKEN_PREFIX + token);
		response.addHeader("UserID", userDto.getUserId());
	}
}
