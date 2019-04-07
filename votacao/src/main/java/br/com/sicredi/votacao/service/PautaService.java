package br.com.sicredi.votacao.service;

import java.util.List;
import java.util.Optional;

import br.com.sicredi.votacao.dto.ResultadoPautaDTO;
import br.com.sicredi.votacao.model.Pauta;

public interface PautaService {

	List<Pauta> findAll();

	Optional<Pauta> findById(Long id);
	
	Pauta save(Pauta model);
	
	ResultadoPautaDTO accounting(Pauta pauta);

}
