package br.com.sicred.voting.repository;

import br.com.sicred.voting.entity.VotingSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSectionRepository extends JpaRepository<VotingSection, Long> {
}
