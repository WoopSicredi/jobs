package br.com.sicredi.votacaoapi.domains.pauta.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {

	private Long pautaId;

	@NotBlank(message = "Nome é campo obrigatório")
	@NotNull(message = "Por favor digite um nome válido")
	@Size(max = 100, message = "O nome deve possuir no máximo 100 caracteres")
	@Setter
	private String nome;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inicioVotacao;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fimVotacao;

	public static PautaDTO converterEmDTO(Pauta pauta) {
		return new PautaDTO(pauta.getId(), pauta.getNome(), pauta.getInicioVotacao(), pauta.getFimVotacao());
	}

}
