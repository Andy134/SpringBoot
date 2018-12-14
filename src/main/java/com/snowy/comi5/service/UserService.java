package com.snowy.comi5.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.snowy.comi5.dto.UserDto;

@Service
public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	List<UserDto> getUsers(int page, int limit);
	UserDto getUserbyUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	void deleteUser(String userId);
}
