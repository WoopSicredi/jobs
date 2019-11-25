package br.com.sicredi.votacaoapi.domains.pauta.dto;

import javax.validation.constraints.NotNull;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VotoResponse {

	@NotNull
	private PautaDTO pautaDTO;

	@NotNull
	private Associado associado;

	@NotNull
	private Boolean decision;
	
	public VotoResponse(Pauta pauta, Associado associado, Boolean decisao) {
		this.pautaDTO = PautaDTO.turnsIntoDTO(pauta);
		this.associado = associado;
		this.decision = decisao;
	}

}
