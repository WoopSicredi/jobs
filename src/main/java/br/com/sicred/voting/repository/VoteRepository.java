package br.com.sicred.voting.repository;

import br.com.sicred.voting.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(" select e from Vote e" +
            " where e.idSecao = :idSecao" +
            "   and e.participantId = :participantId")
    Optional<Vote> findBySecaoAndParticipante(@Param("idSecao") Long idSecao,
                                              @Param("participantId") Long idParticipante);

    @Query(" select e from Vote e" +
            " where e.idSecao = :idSecao ")
    List<Vote> findBySecao(Long idSecao);
}
