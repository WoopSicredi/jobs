package br.com.sicredi.votacao.message;

import lombok.Getter;

@Getter
public enum MessageKey {
	
	PAUTA_NAO_ENCONTRADA("erro.pautaNaoEncontrada"),
	PARAMETROS_INVALIDOS("erro.parametrosInvalidos"),
	PAUTA_INVALIDA("erro.pautaInvalida"),
	SESSAO_INVALIDA("erro.sessaoInvalida"),
	SESSAO_ENCERRADA("erro.sessaoEncerrada"), 
	ASSOCIADO_INVALIDO("erro.AssociadoInvalido"),
	ASSOCIADO_JA_VOTOU("erro.associadoJaVotou");
	
	private final String key;
	
	private MessageKey(String key) {
		this.key = key;
	}

}
