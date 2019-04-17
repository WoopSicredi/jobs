package com.vollino.poll.service.poll;

import java.time.ZonedDateTime;

/**
 * @author Bruno Vollino
 */
public class CreatePollRequest {

    private Long topicId;
    private String description;
    private ZonedDateTime endDate;

    public Long getTopicId() {
        return topicId;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }
}
