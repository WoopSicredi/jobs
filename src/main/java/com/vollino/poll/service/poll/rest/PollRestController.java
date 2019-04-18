package com.vollino.poll.service.poll.rest;

import com.vollino.poll.service.poll.Poll;
import com.vollino.poll.service.poll.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bruno Vollino
 */
@RestController
public class PollRestController {

    private final PollService pollService;

    @Autowired
    public PollRestController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping(path = "/topics/{topicId}/polls", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Poll> create(
            @PathVariable Long topicId,
            @RequestBody CreatePollRequestBody createRequest) {
        Poll persisted = pollService.create(
                new Poll(null,
                        topicId,
                        createRequest.getDescription(),
                        createRequest.getEndDate()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(persisted);
    }
}
