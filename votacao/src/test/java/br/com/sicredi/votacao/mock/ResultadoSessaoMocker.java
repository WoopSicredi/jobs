package br.com.sicredi.votacao.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.sicredi.votacao.dto.ResultadoSessaoDTO;

public final class ResultadoSessaoMocker {
	
	private ResultadoSessaoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Long VOTOS_SIM = 2L;
	public static final Long VOTOS_NAO = 1L;
	
	public static final List<ResultadoSessaoDTO> getResult() {
		List<ResultadoSessaoDTO> result = new ArrayList<>();
		
		result.add(ResultadoSessaoDTO.builder()
				.idSessao(SessaoMocker.ID)
				.votosSim(VOTOS_SIM)
				.votosNao(VOTOS_NAO)
				.build());
		return result;
	}
	
}
