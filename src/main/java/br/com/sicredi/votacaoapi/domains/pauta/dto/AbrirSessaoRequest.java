package br.com.sicredi.votacaoapi.domains.pauta.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AbrirSessaoRequest {
	
	@NotNull(message = "{pautaId.invalido}")
	@Positive(message = "{valor.nao.pode.ser.negativo}")
	private Long pautaId;

	@Positive(message = "{valor.nao.pode.ser.negativo}")
	private Long duracaoEmMinutos;
	
}
