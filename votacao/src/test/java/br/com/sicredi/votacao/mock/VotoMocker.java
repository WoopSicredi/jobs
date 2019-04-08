package br.com.sicredi.votacao.mock;

import br.com.sicredi.votacao.dto.requestbody.VotoSaveDTO;
import br.com.sicredi.votacao.model.ValorVoto;
import br.com.sicredi.votacao.model.Voto;

public final class VotoMocker {
	
	public static final Long ID = 1L;

	private VotoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Voto VOTO = Voto.builder()
			.associado(AssociadoMocker.ASSOCIADO)
			.sessao(SessaoMocker.SESSAO_CREATED)
			.valorVoto(ValorVoto.SIM)
			.build();
	
	public static final Voto VOTO_CREATED = Voto.builder()
			.id(ID)
			.associado(AssociadoMocker.ASSOCIADO)
			.sessao(SessaoMocker.SESSAO_CREATED)
			.valorVoto(ValorVoto.SIM)
			.build();
	
	public static final VotoSaveDTO VOTO_SAVE_DTO = VotoSaveDTO.builder()
			.idAssociado(AssociadoMocker.ID)
			.idSessao(SessaoMocker.ID)
			.valorVoto(ValorVoto.SIM)
			.build();
			
	
}
