package br.com.sicredi.votacao.dto;

import java.io.Serializable;
import java.util.List;

import br.com.sicredi.votacao.util.CalculoUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoPautaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4359450713459342586L;
	
	private String nomePauta;
	private String pergunta;
	private List<ResultadoSessaoDTO> resultadosSessoes;
	
	public Long getVotosSim() {
		return resultadosSessoes.stream()
				.mapToLong(ResultadoSessaoDTO::getVotosSim)
				.sum();
	}
	
	public Long getVotosNao() {
		return resultadosSessoes.stream()
				.mapToLong(ResultadoSessaoDTO::getVotosNao)
				.sum();
	}
	
	public Long getTotalVotos() {
		return getVotosSim() + getVotosNao();
	}
	
	public Double getPercentualSim() {
		return CalculoUtils.percentual(getVotosSim(), getTotalVotos());
	}
	
	public Double getPercentualNao() {
		return CalculoUtils.percentual(getVotosNao(), getTotalVotos());
	}
	
}
