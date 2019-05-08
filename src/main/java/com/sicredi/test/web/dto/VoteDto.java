package com.sicredi.test.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sicredi.test.persistence.model.VoteOption;

/**
 * Objeto de transferência de um voto numa determinada pauta.
 */
public class VoteDto {

    @NotBlank
    private String username;

    @NotNull
    private VoteOption voteOption;

    public VoteDto() {
    }

    public VoteDto(long topicId, String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(VoteOption voteOption) {
        this.voteOption = voteOption;
    }
}
