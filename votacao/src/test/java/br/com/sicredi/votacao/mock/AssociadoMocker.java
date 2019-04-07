package br.com.sicredi.votacao.mock;

import br.com.sicredi.votacao.model.Associado;

public final class AssociadoMocker {
	
	public static final Long ID = 1L;
	public static final String NOME = "Nome do Associado";
	public static final String CPF = "11111111111";

	private AssociadoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Associado ASSOCIADO = Associado.builder()
			.id(ID)
			.nome(NOME)
			.cpf(CPF)
			.build();
	
}
