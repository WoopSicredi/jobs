package com.vollino.poll.service.vote;

import com.google.common.base.MoreObjects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Entity
public class Vote {

    @Valid
    @NotNull
    @EmbeddedId
    private VoteId id;

    @Pattern(regexp = "(Sim|Não)", message = "pollOption is mandatory and accepts only values 'Sim' or 'Não'")
    private String pollOption;

    public Vote() {
    }

    public Vote(VoteId id, String pollOption) {
        this.id = id;
        this.pollOption = pollOption;
    }

    public VoteId getId() {
        return id;
    }

    public void setId(VoteId id) {
        this.id = id;
    }

    public String getPollOption() {
        return pollOption;
    }

    public void setPollOption(String pollOption) {
        this.pollOption = pollOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(getId(), vote.getId()) &&
                Objects.equals(getPollOption(), vote.getPollOption());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPollOption());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("pollOption", pollOption)
                .toString();
    }
}
