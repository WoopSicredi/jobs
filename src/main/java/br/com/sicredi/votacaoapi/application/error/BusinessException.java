package br.com.sicredi.votacaoapi.application.error;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(MensagemValidacaoVotacaoEnum mensagemValidacaoVotacaoEnum) {
		super(mensagemValidacaoVotacaoEnum.getMessage());
	}
	
}
