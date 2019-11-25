package br.com.sicredi.votacaoapi.domains.pauta.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Associado {
	
	@NotNull(message = "{associado.id.invalido}")
	@Positive(message = "{valor.nao.pode.ser.negativo}")
	private Long id;
	
	@Pattern(regexp = "[0-9]{3}[0-9]{3}[0-9]{3}[0-9]{2}", message="{cpf.invalid}")
	private String cpf;
	
	public Associado(Long id) {
		this.id = id;
	}

}
