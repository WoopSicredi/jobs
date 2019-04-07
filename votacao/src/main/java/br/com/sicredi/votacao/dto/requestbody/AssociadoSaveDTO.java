package br.com.sicredi.votacao.dto.requestbody;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.sicredi.votacao.model.Associado;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssociadoSaveDTO implements Serializable {

	private static final long serialVersionUID = 6935801269552050957L;
	
	@NotNull(message = "{validation.associado.requiredName}")
	@Size(min = 3, max = 100, message = "{validation.associado.invalidName}")
	private String nome;
	
	@CPF(message = "{validation.associado.invalidCpf}")
	private String cpf;

	public Associado toModel() {
		return Associado.builder()
				.nome(nome)
				.cpf(cpf)
				.build();
	}
	
}
