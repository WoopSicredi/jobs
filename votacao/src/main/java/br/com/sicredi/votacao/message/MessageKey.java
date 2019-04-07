package br.com.sicredi.votacao.message;

import lombok.Getter;

@Getter
public enum MessageKey {
	
	ASSOCIADO_NAO_ENCONTRADO("erro.associadoNaoEncontradao"),
	PAUTA_NAO_ENCONTRADA("erro.pautaNaoEncontrada"),
	PARAMETROS_INVALIDOS("erro.parametrosInvalidos"),
	SESSAO_PAUTA_INEXISTENTE("erro.erro.sessaoComPautaInexistente"),
	VOTO_SESSAO_INEXISTENTE("erro.votoComSessaoInexistente"),
	VOTO_ASSOCIADO_INEXISTENTE("erro.votoComAssociadoInexistente"),
	SESSAO_ENCERRADA("erro.sessaoEncerrada"), 
	ASSOCIADO_JA_VOTOU("erro.associadoJaVotou");
	
	private final String key;
	
	private MessageKey(String key) {
		this.key = key;
	}

}
