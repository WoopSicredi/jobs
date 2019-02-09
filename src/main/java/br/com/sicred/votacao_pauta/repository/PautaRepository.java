package br.com.sicred.votacao_pauta.repository;

import br.com.sicred.votacao_pauta.entity.Pauta;
import br.com.sicred.votacao_pauta.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
