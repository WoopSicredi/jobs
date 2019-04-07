package br.com.sicredi.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
	
	boolean existsBySessaoPautaAndAssociado(Pauta pauta, Associado associado);

}
