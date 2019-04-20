package com.vollino.poll.service.poll.rest;

import com.vollino.poll.service.poll.business.PollService;
import com.vollino.poll.service.poll.business.domain.Poll;
import com.vollino.poll.service.vote.business.VoteService;
import com.vollino.poll.service.vote.business.domain.Vote;
import com.vollino.poll.service.vote.business.domain.VoteId;
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
    private final VoteService voteService;

    @Autowired
    public PollRestController(PollService pollService, VoteService voteService) {
        this.pollService = pollService;
        this.voteService = voteService;
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

    @ApiOperation("Cast a Vote to a given Poll")
    @PostMapping(path = "/polls/{pollId}/votes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Vote> create(
            @PathVariable Long pollId,
            @RequestBody CreateVoteRequestBody requestBody) {
        Vote created = voteService.create(new Vote(
                new VoteId(requestBody.getVoterId(), pollId),
                requestBody.getPollOption()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(created);
    }
}
