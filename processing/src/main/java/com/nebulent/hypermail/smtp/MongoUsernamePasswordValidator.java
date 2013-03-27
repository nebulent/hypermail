package com.nebulent.hypermail.smtp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.UsernamePasswordValidator;

public class MongoUsernamePasswordValidator implements
		UsernamePasswordValidator {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public void login(String username, String password) throws LoginFailedException {
		logger.info("Auth: {} {}", username, password);
		if (username == null) {
			throw new LoginFailedException();
		}
	}

}
