package com.social.user.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.social.entity.User;
import com.social.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User createFbUser(UserProfile userProfile) {
		User user = new User();
		user.setFacebookId(userProfile.getId());
		user.setName(userProfile.getFirstName());
		user.setUsername(userProfile.getEmail());
		user.setRegistrationDate(new Date());
		user.setLastLoginTime(new Date());
		user.setPassword(bCryptPasswordEncoder.encode(randomAlphabetic(8)));
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByUsername(email);
	}

}
