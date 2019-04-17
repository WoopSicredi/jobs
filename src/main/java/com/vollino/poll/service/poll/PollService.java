package com.vollino.poll.service.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * @author Bruno Vollino
 */
@Service
public class PollService {

    private final PollRepository pollRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll create(@Valid Poll poll) {
        return pollRepository.save(poll);
    }
}
