package br.com.sicredi.votacaoapi.domains.pauta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {

}
