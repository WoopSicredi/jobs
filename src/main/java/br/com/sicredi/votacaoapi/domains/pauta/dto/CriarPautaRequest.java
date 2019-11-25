package br.com.sicredi.votacaoapi.domains.pauta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CriarPautaRequest {

	@NotBlank(message = "{nome.nao.vazio}")
	@NotNull(message = "{nome.invalido}")
	@Size(max=100, message = "{nome.tamanho.invalido}")
	@Setter
	private String nome;
	
}
