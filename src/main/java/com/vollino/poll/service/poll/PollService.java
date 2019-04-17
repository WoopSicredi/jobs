package com.vollino.poll.service.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author Bruno Vollino
 */
@Service
@Validated
public class PollService {

    private final PollRepository pollRepository;
    private final Clock clock;

    @Autowired
    public PollService(PollRepository pollRepository, Clock clock) {
        this.pollRepository = pollRepository;
        this.clock = clock;
    }

    public Poll create(@Valid Poll poll) {
        if (poll.getEndDate() == null) {
            poll.setEndDate(clock.now());
        }

        return pollRepository.save(poll);
    }
}
