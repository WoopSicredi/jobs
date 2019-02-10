package br.com.sicred.voting.service;

import br.com.sicred.voting.dto.VotingSectionResultDto;
import br.com.sicred.voting.dto.VotingSectionDto;
import br.com.sicred.voting.entity.VotingSession;

public interface VotingSectionService {

    VotingSession createVotingSection(VotingSectionDto dto);

    void voteForSection(Long votingSectionId, Long participantId, Boolean vote);

    VotingSectionResultDto getVotingSectionResult(Long sectionId);
}
