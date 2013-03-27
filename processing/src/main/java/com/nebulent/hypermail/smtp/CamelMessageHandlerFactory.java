package com.nebulent.hypermail.smtp;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;

public class CamelMessageHandlerFactory implements MessageHandlerFactory {

	@Autowired
	CamelContext camelContext;
	
	public MessageHandler create(MessageContext ctx) {
		return new CamelMessageHandler((String)ctx.getAuthenticationHandler().getIdentity(), 
				camelContext.createProducerTemplate());
	}

}
