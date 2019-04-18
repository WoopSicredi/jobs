package com.vollino.poll.service.vote.rest;

import com.vollino.poll.service.vote.Vote;
import com.vollino.poll.service.vote.VoteId;
import com.vollino.poll.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruno Vollino
 */
@RestController
public class VoteRestController {

    private VoteService voteService;

    @Autowired
    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(path = "/polls/{pollId}/votes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Vote> create(
            @PathVariable Long pollId,
            @RequestBody CreateVoteRequestBody requestBody) {
        Vote created = voteService.create(new Vote(
                new VoteId(requestBody.getVoterId(), pollId),
                requestBody.getPollOption()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(created);
    }
}
