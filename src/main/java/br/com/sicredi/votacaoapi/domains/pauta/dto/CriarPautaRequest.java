package br.com.sicredi.votacaoapi.domains.pauta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CriarPautaRequest {

	@NotBlank(message = "Nome é campo obrigatório")
	@NotNull(message = "Por favor digite um nome válido")
	@Size(max=100, message = "O nome deve possuir no máximo 100 caracteres")
	@Setter
	private String nome;
	
	public static CriarPautaRequest converterEmDTO(Pauta pauta) {
		return new CriarPautaRequest(pauta.getNome());
	}
	
}
