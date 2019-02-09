package br.com.sicred.votacao_pauta.repository;

import br.com.sicred.votacao_pauta.entity.SecaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoVotacaoRepository extends JpaRepository<SecaoVotacao, Long> {
}
