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
	
	@NotNull(message = "{validacao.associado.nomeObrigatorio}")
	@Size(min = 3, max = 100, message = "{validacao.associado.nomeInvalido}")
	private String nome;
	
	@CPF(message = "{validacao.associado.cpfInvalido}")
	private String cpf;

	public Associado toModel() {
		return Associado.builder()
				.nome(nome)
				.cpf(cpf)
				.build();
	}
	
}
