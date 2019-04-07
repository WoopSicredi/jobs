package br.com.sicredi.votacao.dto.requestbody;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.model.Sessao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessaoSaveDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2568612440496774656L;

	private static final Long DURACAO_PADRAO = 1L;
	
	@NotNull(message = "{validacao.sessao.idPautaObrigatorio}")
	private Long idPauta;
	
	@Positive(message = "{validacao.sessao.minutosInvalidos}")
	private Long minutos;

	public Sessao toModel() {
		return Sessao.builder()
				.pauta(Pauta.builder().id(idPauta).build())
				.inicio(LocalDateTime.now())
				.fim(calcularTerminoSessao())
				.build();
	}

	private LocalDateTime calcularTerminoSessao() {
		return LocalDateTime.now()
				.plusMinutes(Optional.ofNullable(minutos)
						.orElse(DURACAO_PADRAO));
	}
	
}
