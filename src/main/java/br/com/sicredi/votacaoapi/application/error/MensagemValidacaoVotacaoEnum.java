package br.com.sicredi.votacaoapi.application.error;

import br.com.sicredi.votacaoapi.application.util.MessagesProperty;
import lombok.Getter;

@Getter
public enum MensagemValidacaoVotacaoEnum {

	RECURSO_NAO_ENCONTRADO("recurso.nao.encontrado"),
	ERRO_VALIDACAO_CAMPO("erro.validacao.campo"),
	EXCECAO_INTERNA("excecao.interna"),
	PAUTA_NAO_EXISTE("pauta.nao.existe"),
	SESSAO_JA_FOI_INICIADA_EXCEPTION("sessao.ja.foi.iniciada.exception"),
	BAD_REQUEST("bad.request");
	
	private String codigo;
	
	private MensagemValidacaoVotacaoEnum(String codigo) {
		this.codigo = codigo;
	}
	
	public String getMessage(String... params) {
		return MessagesProperty.getMessage(this.getCodigo(), params);
	}
	
}
