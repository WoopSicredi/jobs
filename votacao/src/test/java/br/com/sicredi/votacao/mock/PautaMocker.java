package br.com.sicredi.votacao.mock;

import br.com.sicredi.votacao.model.Pauta;

public final class PautaMocker {
	
	public static final Long ID = 1L;
	public static final String NOME = "Nome da Pauta";
	public static final String PERGUNTA = "Pergunta da Pauta";
	
	private PautaMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Pauta PAUTA = Pauta.builder()
			.id(ID)
			.nome(NOME)
			.pergunta(PERGUNTA)
			.build();

}
