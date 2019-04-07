package br.com.sicredi.votacao.dto;

import java.io.Serializable;
import java.util.Objects;

import br.com.sicredi.votacao.model.Associado;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssociadoDTO implements Serializable {

	private static final long serialVersionUID = 2999824472481109851L;
	
	private Long id;
	private String nome;
	private String cpf;
	
	public static AssociadoDTO parseDTO(Associado associado) {
		if (Objects.isNull(associado)) {
			return null;
		}
		return AssociadoDTO.builder()
				.id(associado.getId())
				.nome(associado.getNome())
				.cpf(associado.getCpf())
				.build();
	}

}
