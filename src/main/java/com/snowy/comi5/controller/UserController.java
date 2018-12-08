package com.snowy.comi5.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snowy.comi5.dto.UserDto;
import com.snowy.comi5.model.request.UserReqModel;
import com.snowy.comi5.model.response.UserRest;
import com.snowy.comi5.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "Get_user";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserReqModel userReq) { // Registration screen
		
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userReq, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}

	@PutMapping
	public String updateUser() {
		return "Update user";
	}

	@DeleteMapping
	public String deleteUser() {
		return "Delete user";
	}

}
