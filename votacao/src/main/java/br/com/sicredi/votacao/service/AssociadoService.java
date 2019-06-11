package br.com.sicredi.votacao.service;

import java.util.List;
import java.util.Optional;

import br.com.sicredi.votacao.model.Associado;

public interface AssociadoService {

	List<Associado> findAll();

	Optional<Associado> findById(Long id);

	Associado save(Associado associado);

}
