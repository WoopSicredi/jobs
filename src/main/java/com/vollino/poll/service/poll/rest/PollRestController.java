package com.vollino.poll.service.poll.rest;

import com.vollino.poll.service.poll.Poll;
import com.vollino.poll.service.poll.PollService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Bruno Vollino
 */
@Api("polls")
@RestController
public class PollRestController {

    private final PollService pollService;

    @Autowired
    public PollRestController(PollService pollService) {
        this.pollService = pollService;
    }

    @ApiOperation("Create a Poll under a Topic")
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
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(persisted);
    }

    @ApiOperation("Get a Poll by ID")
    @GetMapping(path = "/polls/{pollId}", produces = "application/json")
    public ResponseEntity<Poll> get(@PathVariable("pollId") Long pollId) {
        Optional<Poll> poll = pollService.getPoll(pollId);

        if (poll.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(poll.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
