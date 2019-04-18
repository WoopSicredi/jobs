package com.vollino.poll.service.vote;

import com.vollino.poll.service.exception.DataIntegrityException;
import com.vollino.poll.service.poll.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author Bruno Vollino
 */
@Service
@Validated
public class VoteService {

    private final VoteRepository voteRepository;
    private final PollRepository pollRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, PollRepository pollRepository) {
        this.voteRepository = voteRepository;
        this.pollRepository = pollRepository;
    }

    public Vote create(@Valid Vote vote) {
        if (!pollRepository.existsById(vote.getId().getPollId())) {
            throw new DataIntegrityException(String.format("Poll with id=%d not found", vote.getId().getPollId()));
        }
        if (voteRepository.existsById(vote.getId())) {
            throw new DataIntegrityException(String.format(
                    "Voter with id=%d has already voted in Poll %d",
                    vote.getId().getPollId(), vote.getId().getVoterId()));
        }

        return voteRepository.save(vote);
    }
}
