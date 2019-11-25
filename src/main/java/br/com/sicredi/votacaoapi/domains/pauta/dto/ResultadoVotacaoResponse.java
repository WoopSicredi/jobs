package br.com.sicredi.votacaoapi.domains.pauta.dto;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.model.validations.TipoDeResultadoDaVotacao;
import lombok.Getter;

@Getter
public class ResultadoVotacaoResponse {

	private PautaDTO pauta;
	
	private TipoDeResultadoDaVotacao votingResult;
	
	public ResultadoVotacaoResponse(Pauta pauta, TipoDeResultadoDaVotacao votingResult) {
		this.pauta = PautaDTO.converterEmDTO(pauta);
		this.votingResult = votingResult;
	}
	
}
