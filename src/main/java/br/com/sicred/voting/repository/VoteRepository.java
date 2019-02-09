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
            " where e.votingSection.id = :votingSectionId" +
            "   and e.participantId = :participantId")
    Optional<Vote> findByVotingSectionAndParticipantIds(@Param("votingSectionId") Long votingSectionId,
                                                        @Param("participantId") Long participantId);

    @Query(" select e from Vote e" +
            " where e.votingSection.id = :votingSectionId ")
    List<Vote> findByVotingSectionId(@Param("votingSectionId") Long votingSectionId);
}
