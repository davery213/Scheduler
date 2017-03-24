package com.team.icr.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.icr.user.entity.User;
import com.team.icr.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/createUser")
	public User createUser(@RequestParam(value = "firstName") final String firstName,
			@RequestParam(value = "lastName") final String lastName) {

		return this.userService.createNewUser(firstName, lastName);
	}

	@RequestMapping("/findUserById")
	public User findUserById(@RequestParam(value = "id") final long id) {
		return this.userService.findUserById(id);
	}
}
