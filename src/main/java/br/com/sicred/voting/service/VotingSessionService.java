package br.com.sicred.voting.service;

import br.com.sicred.voting.dto.VotingSessionResultDto;
import br.com.sicred.voting.dto.VotingSessionDto;
import br.com.sicred.voting.entity.VotingSession;

public interface VotingSessionService {

    VotingSession createVotingSession(VotingSessionDto dto);

    void voteForSession(Long votingSessionId, Long participantId, Boolean vote);

    VotingSessionResultDto getVotingSessionResult(Long sessionId);
}
