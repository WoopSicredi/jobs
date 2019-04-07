package br.com.sicredi.votacao.service;

import java.util.List;

import br.com.sicredi.votacao.dto.ResultadoSessaoDTO;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.model.Sessao;

public interface SessaoService {
	
	List<ResultadoSessaoDTO> accountingSessoesByPauta(Pauta pauta);

	Sessao save(Sessao sessao);

}
