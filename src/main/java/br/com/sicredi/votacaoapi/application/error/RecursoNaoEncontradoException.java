package br.com.sicredi.votacaoapi.application.error;

/**
 * 
 * @author Ruddy Paz 
 */
public class RecursoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(MensagemValidacaoVotacaoEnum mensagemValidacaoVotacaoEnum) {
		super(mensagemValidacaoVotacaoEnum.getMessage());
	}
	
}
