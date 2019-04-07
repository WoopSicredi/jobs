package br.com.sicredi.votacao.mock;

import br.com.sicredi.votacao.model.ValorVoto;
import br.com.sicredi.votacao.model.Voto;

public final class VotoMocker {
	
	public static final Long ID = 1L;

	private VotoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Voto VOTO = Voto.builder()
			.associado(AssociadoMocker.ASSOCIADO)
			.sessao(SessaoMocker.SESSAO)
			.valorVoto(ValorVoto.SIM)
			.build();
	
	public static final Voto VOTO_CREATED = Voto.builder()
			.id(ID)
			.associado(AssociadoMocker.ASSOCIADO)
			.sessao(SessaoMocker.SESSAO)
			.valorVoto(ValorVoto.SIM)
			.build();
	
}
