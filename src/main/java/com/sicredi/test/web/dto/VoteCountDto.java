package com.sicredi.test.web.dto;

/**
 * Objeto de transferência de contagem de votos de uma determinada opição de
 * votos.
 */
public class VoteCountDto {

    private String voteOption;

    private long count;

    public VoteCountDto() {
    }

    public VoteCountDto(String voteOption, long count) {
        this.voteOption = voteOption;
        this.count = count;
    }

    public String getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(String voteOption) {
        this.voteOption = voteOption;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
