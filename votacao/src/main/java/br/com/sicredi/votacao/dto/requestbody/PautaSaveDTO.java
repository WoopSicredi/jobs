package br.com.sicredi.votacao.dto.requestbody;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sicredi.votacao.model.Pauta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaSaveDTO implements Serializable {

	private static final long serialVersionUID = -1125758208743083240L;
	
	@NotNull(message = "{validacao.pauta.nomeObrigatorio}")
	@Size(min = 3, max = 100, message = "{validacao.pauta.nomeInvalido}")
	private String nome;

	@NotNull(message = "{validacao.pauta.perguntaObrigatoria}")
	@Size(min = 3, max = 1000, message = "{validacao.pauta.perguntaInvalida}")
	private String pergunta;
	
	public Pauta toModel() {
		return Pauta.builder()
				.nome(nome)
				.pergunta(pergunta)
				.build();
	}
	
}
