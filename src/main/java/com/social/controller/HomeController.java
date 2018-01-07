package com.social.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
