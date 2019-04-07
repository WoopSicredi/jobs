package br.com.sicredi.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.sicredi.votacao.message.MessageKey;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends BaseRuntimeException {

	private static final long serialVersionUID = -8909887932783542291L;
	
	public BusinessException(MessageKey messageKey, Object ...args) {
		super(messageKey, args);
	}

}
