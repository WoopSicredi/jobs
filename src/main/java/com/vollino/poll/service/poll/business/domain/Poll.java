package com.vollino.poll.service.poll.business.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Bruno Vollino
 */
@Entity
@Table(name = "poll")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "topicId is mandatory")
    private Long topicId;

    @NotBlank(message = "description is mandatory")
    private String description;

    private ZonedDateTime endDate;

    @Transient
    private List<VoteCount> results;

    public Poll() {
    }

    public Poll(Long id, Long topicId, String description, ZonedDateTime endDate) {
        this.id = id;
        this.topicId = topicId;
        this.description = description;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public List<VoteCount> getResults() {
        return results;
    }

    public void setResults(List<VoteCount> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return Objects.equals(getId(), poll.getId()) &&
                Objects.equals(getTopicId(), poll.getTopicId()) &&
                Objects.equals(getDescription(), poll.getDescription()) &&
                Objects.equals(getEndDate(), poll.getEndDate()) &&
                Objects.equals(getResults(), poll.getResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTopicId(), getDescription(), getEndDate(), getResults());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("topicId", topicId)
                .add("description", description)
                .add("endDate", endDate)
                .add("results", results)
                .toString();
    }
}
