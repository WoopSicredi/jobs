package br.com.sicred.voting.service;

import br.com.sicred.voting.dto.VotingSectionDto;
import br.com.sicred.voting.dto.VotingSectionResultDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.entity.Vote;
import br.com.sicred.voting.entity.VotingSection;
import br.com.sicred.voting.exception.ClosedSectionVotingException;
import br.com.sicred.voting.exception.ParticipantAlreadyVotedException;
import br.com.sicred.voting.exception.VotingSectionStillOpenException;
import br.com.sicred.voting.repository.TopicRepository;
import br.com.sicred.voting.repository.VoteRepository;
import br.com.sicred.voting.repository.VotingSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingSectionServiceImpl implements VotingSectionService {

    private final TopicRepository topicRepository;
    private final VotingSectionRepository votingSectionRepository;
    private final VoteRepository voteRepository;

    @Override
    public VotingSection createVotingSection(VotingSectionDto dto) {
        Optional<Topic> pauta = this.topicRepository.findById(dto.getTopicId());
        pauta.orElseThrow(() -> new IllegalArgumentException("Id de topic inválido"));
        VotingSection secao = VotingSection.builder()
                .topic(pauta.get())
                .openingDate(dto.getOpeningDate())
                .closingDate(
                        dto.getClosingDate() == null ?
                                dto.getOpeningDate().plus(1, MINUTES) : dto.getClosingDate()).build();
        return votingSectionRepository.save(secao);
    }

    @Override
    public void voteForSection(Long votingSectionId, Long participantId, Boolean vote) {
        Optional<VotingSection> section = this.votingSectionRepository.findById(votingSectionId);
        section.orElseThrow(() -> new IllegalArgumentException("Id de seção inválido"));
        if(section.get().getClosingDate() != null && section.get().getClosingDate().isBefore(LocalDateTime.now())) {
            throw new ClosedSectionVotingException();
        }
        Optional<Vote> preExistentVote = this.voteRepository.findByVotingSectionAndParticipantIds(votingSectionId, participantId);
        preExistentVote.ifPresent(s -> {throw new ParticipantAlreadyVotedException();});
        this.voteRepository.save(
                Vote.builder()
                        .date(LocalDateTime.now())
                        .participantId(participantId)
                        .votingSection(section.get())
                        .value(vote)
                        .build());
    }

    @Override
    public VotingSectionResultDto getVotingSectionResult(Long sectionId) {
        Optional<VotingSection> section = this.votingSectionRepository.findById(sectionId);
        section.orElseThrow(() -> new IllegalArgumentException("Id de seção inválido"));
        if(section.get().getClosingDate().isAfter(LocalDateTime.now())) {
            throw new VotingSectionStillOpenException();
        }
        List<Vote> votes = this.voteRepository.findByVotingSectionId(sectionId);
        if(votes.isEmpty()) {
            return VotingSectionResultDto
                    .builder()
                    .votingSection(section.get())
                    .noPercentage(0d)
                    .yesPercentage(0d).build();
        }
        long countYes = votes.stream().filter(vote -> vote.getValue()).count();
        double yesPercentage = new Double(countYes) / votes.size() * 100;
        return VotingSectionResultDto.builder()
                .votingSection(section.get())
                .yesPercentage(yesPercentage)
                .noPercentage(100-yesPercentage)
                .build();
    }


}
