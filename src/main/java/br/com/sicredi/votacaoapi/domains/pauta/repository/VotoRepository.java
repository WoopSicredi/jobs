package br.com.sicredi.votacaoapi.domains.pauta.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.model.Voto;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

	Optional<Voto> findByPautaAndUsuarioId(Pauta pauta, Long usuarioId);
	
}
