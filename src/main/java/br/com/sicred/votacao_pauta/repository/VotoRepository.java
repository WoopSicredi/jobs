package br.com.sicred.votacao_pauta.repository;

import br.com.sicred.votacao_pauta.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query(" select e from Voto e" +
            " where e.idSecao = :idSecao" +
            "   and e.idParticipante = :idParticipante")
    Optional<Voto> findBySecaoAndParticipante(@Param("idSecao") Long idSecao,
                                              @Param("idParticipante") Long idParticipante);

    @Query(" select e from Voto e" +
            " where e.idSecao = :idSecao ")
    List<Voto> findBySecao(Long idSecao);
}
