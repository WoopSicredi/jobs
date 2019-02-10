package br.com.sicred.voting.service;

import br.com.sicred.voting.dto.VotingSessionDto;
import br.com.sicred.voting.dto.VotingSessionResultDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.entity.Vote;
import br.com.sicred.voting.entity.VotingSession;
import br.com.sicred.voting.exception.ClosedSessionVotingException;
import br.com.sicred.voting.exception.ParticipantAlreadyVotedException;
import br.com.sicred.voting.exception.VotingSessionStillOpenException;
import br.com.sicred.voting.repository.TopicRepository;
import br.com.sicred.voting.repository.VoteRepository;
import br.com.sicred.voting.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingSessionServiceImpl implements VotingSessionService {

    private final TopicRepository topicRepository;
    private final VotingSessionRepository votingSessionRepository;
    private final VoteRepository voteRepository;

    @Override
    public VotingSession createVotingSession(VotingSessionDto dto) {
        Optional<Topic> topic = this.topicRepository.findById(dto.getTopicId());
        topic.orElseThrow(() -> new IllegalArgumentException("Id de topic inválido"));
        if(dto.getClosingDate() != null && dto.getClosingDate().isBefore(dto.getOpeningDate())) {
            throw new IllegalArgumentException("Data de encerramento da votação não " +
                    "pode ser menor que a data de abertura");
        }
        VotingSession session = VotingSession.builder()
                .topic(topic.get())
                .openingDate(dto.getOpeningDate())
                .closingDate(
                        dto.getClosingDate() == null ?
                                dto.getOpeningDate().plus(1, MINUTES) : dto.getClosingDate()).build();
        return votingSessionRepository.save(session);
    }

    @Override
    public void voteForSession(Long votingSessionId, Long participantId, Boolean vote) {
        Optional<VotingSession> session = this.votingSessionRepository.findById(votingSessionId);
        session.orElseThrow(() -> new IllegalArgumentException("Id de sssção inválido"));
        if(session.get().getClosingDate() != null && session.get().getClosingDate().isBefore(LocalDateTime.now())) {
            throw new ClosedSessionVotingException();
        }
        Optional<Vote> preExistentVote = this.voteRepository.findByVotingSessionAndParticipantIds(
                votingSessionId, participantId);
        preExistentVote.ifPresent(s -> {throw new ParticipantAlreadyVotedException();});
        this.voteRepository.save(
                Vote.builder()
                        .date(LocalDateTime.now())
                        .participantId(participantId)
                        .votingSession(session.get())
                        .value(vote)
                        .build());
    }

    @Override
    public VotingSessionResultDto getVotingSessionResult(Long sessionId) {
        Optional<VotingSession> session = this.votingSessionRepository.findById(sessionId);
        session.orElseThrow(() -> new IllegalArgumentException("Id de sessão inválido"));
        if(session.get().getClosingDate().isAfter(LocalDateTime.now())) {
            throw new VotingSessionStillOpenException();
        }
        List<Vote> votes = this.voteRepository.findByVotingSessionId(sessionId);
        if(votes.isEmpty()) {
            return VotingSessionResultDto
                    .builder()
                    .votingSession(session.get())
                    .noPercentage(0d)
                    .yesPercentage(0d).build();
        }
        long countYes = votes.stream().filter(vote -> vote.getValue()).count();
        double yesPercentage = new Double(countYes) / votes.size() * 100;
        return VotingSessionResultDto.builder()
                .votingSession(session.get())
                .yesPercentage(yesPercentage)
                .noPercentage(100-yesPercentage)
                .build();
    }


}
