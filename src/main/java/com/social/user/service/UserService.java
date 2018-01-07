package com.social.user.service;

import org.springframework.social.connect.UserProfile;

import com.social.entity.User;

public interface UserService {

	User createFbUser(UserProfile userProfile);

	User getUserByEmail(String email);
}
