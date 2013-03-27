package com.nebulent.hypermail.smtp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.io.IOUtils;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.TooMuchDataException;

public class CamelMessageHandler implements MessageHandler {

	final String username;
	
	final ProducerTemplate sender;
	
	final com.nebulent.hypermail.model.Message message;
	
	
	public CamelMessageHandler(String username, ProducerTemplate template) {
		this.username = username;
		sender = template;
		message = new com.nebulent.hypermail.model.Message();
	}

	public void data(InputStream data) throws RejectException,
			TooMuchDataException, IOException {
		message.setBody(IOUtils.toString(data, "UTF-8"));
	}

	public void done() {
		sender.sendBody("direct:process-email", ExchangePattern.InOnly, message);
	}

	public void from(String from) throws RejectException {
		message.setFrom(from);
	}

	public void recipient(String to) throws RejectException {
		message.setTo(to);
	}

}
