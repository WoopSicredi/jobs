package br.com.sicredi.votacao.mock;

import br.com.sicredi.votacao.dto.requestbody.PautaSaveDTO;
import br.com.sicredi.votacao.model.Pauta;

public final class PautaMocker {
	
	public static final Long ID_NOT_FOUND = 0L;
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
	
	public static final PautaSaveDTO PAUTA_SAVE_DTO = PautaSaveDTO.builder()
			.nome(NOME)
			.pergunta(PERGUNTA)
			.build();

}
