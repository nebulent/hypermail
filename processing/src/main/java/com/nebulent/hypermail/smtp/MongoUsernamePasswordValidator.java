package com.nebulent.hypermail.smtp;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.UsernamePasswordValidator;

import com.nebulent.hypermail.model.User;
import com.nebulent.hypermail.repositories.UserRepository;

public class MongoUsernamePasswordValidator implements
		UsernamePasswordValidator {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserRepository userRepository;
	
	public void login(String username, String password) throws LoginFailedException {
		logger.info("Auth: {} {}", username, password);
		if (username == null) {
			throw new LoginFailedException();
		}
		User user = userRepository.findByUsername(username);
		if (!StringUtils.equals(password, user.getPassword())) {
			throw new LoginFailedException();
		}
	}

}
