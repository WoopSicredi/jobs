package br.com.sicredi.votacaoapi.domains.pauta.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VotoRequest {

	@NotNull(message = "{pautaId.invalido}")
	@Positive(message = "{valor.nao.pode.ser.negativo}")
	private Long pautaId;

	@Valid
	@NotNull(message = "{associado.invalido}")
	private Associado associado;

	@NotNull(message = "{decisao.invalido}")
	private Boolean decisao;

}
