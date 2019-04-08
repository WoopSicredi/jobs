package br.com.sicredi.votacao.mock;

import br.com.sicredi.votacao.dto.requestbody.AssociadoSaveDTO;
import br.com.sicredi.votacao.model.Associado;

public final class AssociadoMocker {
	
	public static final Long ID = 1L;
	public static final Long ID_NOT_FOUND = 0L;
	public static final String NOME = "Nome do Associado";
	public static final String CPF = "01065929048";
	public static final String INVALID_CPF = "11111111111";

	private AssociadoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Associado ASSOCIADO = Associado.builder()
			.id(ID)
			.nome(NOME)
			.cpf(CPF)
			.build();
	
	public static final AssociadoSaveDTO ASSOCIADO_SAVE_DTO = AssociadoSaveDTO.builder()
			.nome(NOME)
			.cpf(CPF)
			.build();
	
	public static final AssociadoSaveDTO ASSOCIADO_SAVE_DTO_WITH_INVALID_PARAMETERS = AssociadoSaveDTO.builder()
			.nome(null)
			.cpf(INVALID_CPF)
			.build();
}
