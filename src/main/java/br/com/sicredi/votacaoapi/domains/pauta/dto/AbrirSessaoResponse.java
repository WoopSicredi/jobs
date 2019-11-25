package br.com.sicredi.votacaoapi.domains.pauta.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AbrirSessaoResponse {

	private Long pautaId;

	private String nome;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inicioVotacao;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fimVotacao;

	public static AbrirSessaoResponse converterEmDTO(Pauta pauta) {
		return new AbrirSessaoResponse(pauta.getId(), pauta.getNome(), pauta.getInicioVotacao(), pauta.getFimVotacao());
	}

}
