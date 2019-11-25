package br.com.sicredi.votacaoapi.domains.pauta.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VotoRequest {

	@NotNull(message = "Por favor informe o identificador da pauta")
	@Positive(message = "Valor não pode ser negativo")
	private Long pautaId;

	@Valid
	@NotNull(message = "Por favor informe o associado")
	private Associado associado;

	@NotNull(message = "Por favor informe o voto")
	private Boolean decisao;

}
