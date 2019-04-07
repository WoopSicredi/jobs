package br.com.sicredi.votacao.message;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

@Component
public class MessageHandler {
	
	private final MessageSource messageSource;
	
	@Autowired
	public MessageHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String getMessage(MessageKey messageKey, Object ...args) {
		return messageSource.getMessage(messageKey.getKey(), args, Locale.getDefault());
	}
	
	public String getMessage(ObjectError objectError) {
		return messageSource.getMessage(objectError, Locale.getDefault());
	}

}