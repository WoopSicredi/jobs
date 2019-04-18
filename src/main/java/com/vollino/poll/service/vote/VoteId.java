package com.vollino.poll.service.vote;

import com.google.common.base.MoreObjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Embeddable
public class VoteId implements Serializable {

    private static final long serialVersionUID = 4796082321282645728L;

    @NotNull(message = "voterId is mandatory")
    private Long voterId;

    @NotNull(message = "pollId is mandatory")
    private Long pollId;

    public VoteId() {
    }

    public VoteId(Long voterId, Long pollId) {
        this.voterId = voterId;
        this.pollId = pollId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteId voteId = (VoteId) o;
        return Objects.equals(getVoterId(), voteId.getVoterId()) &&
                Objects.equals(getPollId(), voteId.getPollId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoterId(), getPollId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("voterId", voterId)
                .add("pollId", pollId)
                .toString();
    }
}
