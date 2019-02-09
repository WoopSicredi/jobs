package br.com.sicred.voting.service;

import br.com.sicred.voting.dto.VotingSectionResultDto;
import br.com.sicred.voting.dto.VotingSectionDto;
import br.com.sicred.voting.entity.VotingSection;

public interface VotingSectionService {

    VotingSection createVotingSection(VotingSectionDto dto);

    void voteForSection(Long votingSectionId, Long participantId, Boolean vote);

    VotingSectionResultDto getVotingSectionResult(Long sectionId);
}
