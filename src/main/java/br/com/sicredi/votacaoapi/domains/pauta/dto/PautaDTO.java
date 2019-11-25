package br.com.sicredi.votacaoapi.domains.pauta.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PautaDTO {

	private Long pautaId;

	@NotBlank(message = "{nome.nao.vazio}")
	@NotNull(message = "{nome.invalido}")
	@Size(max = 100, message = "{nome.tamanho.invalido}")
	@Setter
	private String nome;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inicioVotacao;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fimVotacao;

	public static PautaDTO turnsIntoDTO(Pauta pauta) {
		return new PautaDTO(pauta.getId(), pauta.getNome(), pauta.getInicioVotacao(), pauta.getFimVotacao());
	}

}
