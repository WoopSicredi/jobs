package br.com.sicredi.votacaoapi.domains.pauta.dto;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CriarPautaResponse {

	private Long topicId;
	
	private String name;
	
	public static CriarPautaResponse converterEmDTO(Pauta pauta) {
		return new CriarPautaResponse(pauta.getId(), pauta.getNome());
	}
	
}
