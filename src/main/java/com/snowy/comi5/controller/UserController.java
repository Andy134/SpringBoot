package com.snowy.comi5.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snowy.comi5.dto.UserDto;
import com.snowy.comi5.exceptions.UserServiceException;
import com.snowy.comi5.model.request.UserReqModel;
import com.snowy.comi5.model.response.UserRest;
import com.snowy.comi5.service.UserService;
import com.snowy.comi5.utils.ErrorMessages;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}", 
				produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserRest getUser(@PathVariable String id) {

		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserbyUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		return returnValue;
	}

	@PostMapping(
				consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
				)
	public UserRest createUser(@RequestBody UserReqModel userReq) throws Exception { // Registration screen

		UserRest returnValue = new UserRest();
		
		if(userReq.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessages());

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
