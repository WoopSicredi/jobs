package com.vollino.poll.service.vote.rest;

/**
 * @author Bruno Vollino
 */
public class CreateVoteRequestBody {

    private Long voterId;
    private String pollOption;

    public Long getVoterId() {
        return voterId;
    }

    public String getPollOption() {
        return pollOption;
    }
}
