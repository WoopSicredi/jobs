package br.com.sicredi.votacao.dto;

import br.com.sicredi.votacao.util.CalculoUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoSessaoDTO {
	
	private Long idSessao;
	private Long votosSim;
	private Long votosNao;
	
	public Long getTotalVotos() {
		return votosSim + votosNao;
	}
	
	public Double getPercentualSim() {
		return CalculoUtils.percentual(votosSim, getTotalVotos());
	}
	
	public Double getPercentualNao() {
		return CalculoUtils.percentual(votosNao, getTotalVotos());
	}
	
}
