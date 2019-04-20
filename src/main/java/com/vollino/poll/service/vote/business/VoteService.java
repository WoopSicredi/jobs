package com.vollino.poll.service.vote.business;

import com.vollino.poll.service.exception.business.DataIntegrityException;
import com.vollino.poll.service.poll.business.Clock;
import com.vollino.poll.service.poll.business.domain.Poll;
import com.vollino.poll.service.poll.business.exception.ClosedPollException;
import com.vollino.poll.service.poll.persistence.PollRepository;
import com.vollino.poll.service.vote.business.domain.Vote;
import com.vollino.poll.service.vote.persistence.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Bruno Vollino
 */
@Service
@Validated
public class VoteService {

    private final VoteRepository voteRepository;
    private final PollRepository pollRepository;
    private final Clock clock;

    @Autowired
    public VoteService(VoteRepository voteRepository, PollRepository pollRepository, Clock clock) {
        this.voteRepository = voteRepository;
        this.pollRepository = pollRepository;
        this.clock = clock;
    }

    public Vote create(@Valid Vote vote) {
        Optional<Poll> poll = pollRepository.findById(vote.getId().getPollId());
        if (poll.isPresent()) {
            if (clock.now().isAfter(poll.get().getEndDate())) {
                throw new ClosedPollException(poll.get());
            }
        } else {
            throw new DataIntegrityException(String.format("Poll with id=%d not found", vote.getId().getPollId()));
        }
        if (voteRepository.existsById(vote.getId())) {
            throw new DataIntegrityException(String.format(
                    "Voter with id=%d has already voted in Poll %d",
                    vote.getId().getVoterId(), vote.getId().getPollId()));
        }

        return voteRepository.save(vote);
    }
}
