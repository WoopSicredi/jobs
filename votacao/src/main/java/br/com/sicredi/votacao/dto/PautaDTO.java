package br.com.sicredi.votacao.dto;

import java.io.Serializable;
import java.util.Objects;

import br.com.sicredi.votacao.model.Pauta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaDTO implements Serializable {

	private static final long serialVersionUID = 3955426265813582792L;
	
	private Long id;
	private String nome;
	private String pergunta;

	public static PautaDTO parseDTO(Pauta pauta) {
		if (Objects.isNull(pauta)) {
			return null;
		}
		return PautaDTO.builder()
				.id(pauta.getId())
				.nome(pauta.getNome())
				.pergunta(pauta.getPergunta())
				.build();
	}

}
