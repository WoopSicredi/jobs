package com.vollino.poll.service.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Bruno Vollino
 */
@RestController
@RequestMapping("/poll")
public class PollRestController {

    private final PollRepository pollRepository;

    @Autowired
    public PollRestController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Poll> create(@Valid @RequestBody CreatePollRequest createRequest) {
        Poll persisted = pollRepository.save(
                new Poll(null, createRequest.getTopicId(), createRequest.getDescription(),
                        createRequest.getEndDate()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(persisted);
    }
}
