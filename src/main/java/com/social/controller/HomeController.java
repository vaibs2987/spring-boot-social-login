package com.social.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.User;

@RestController
@RequestMapping({ "/api/user/" })
public class HomeController {

	@RequestMapping(value = { "resources/welcome" }, method = RequestMethod.GET)
	public String welcome() {

		return "welcome";
	}

	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	public String getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	@RequestMapping(value = { "/resources/test" }, method = RequestMethod.GET)
	public String testUser() {
		User user = new User();
		user.setUsername("vivek@gmail.com");
		user.setPassword("password");
		user.setName("Vivek");
		user.setRegistrationDate(new Date());
		user.setLastLoginTime(new Date());
		// user = userService.createUser(user);
		return user != null ? "success" : "Incorrect credinals";
	}
}
