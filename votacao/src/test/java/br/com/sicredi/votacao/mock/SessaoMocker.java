package br.com.sicredi.votacao.mock;

import java.time.LocalDateTime;

import br.com.sicredi.votacao.model.Sessao;

public final class SessaoMocker {

	public static final Long ID = 1L;
	public static final Long DURATION = 60L;
	
	public SessaoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Sessao SESSAO = Sessao.builder()
			.id(ID)
			.inicio(LocalDateTime.now())
			.fim(LocalDateTime.now().plusMinutes(DURATION))
			.pauta(PautaMocker.PAUTA)
			.build();
	
	public static final Sessao SESSAO_CLOSED = Sessao.builder()
			.id(ID)
			.inicio(LocalDateTime.now())
			.fim(LocalDateTime.now().minusMinutes(DURATION))
			.pauta(PautaMocker.PAUTA)
			.build();
	
}
