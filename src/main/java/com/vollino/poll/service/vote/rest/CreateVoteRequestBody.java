package com.vollino.poll.service.vote.rest;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bruno Vollino
 */
public class CreateVoteRequestBody {

    @ApiModelProperty(notes = "Any Long identifying an unique the voter in the poll")
    private Long voterId;

    @ApiModelProperty(allowableValues = "Sim, Não")
    private String pollOption;

    public Long getVoterId() {
        return voterId;
    }

    public String getPollOption() {
        return pollOption;
    }
}
