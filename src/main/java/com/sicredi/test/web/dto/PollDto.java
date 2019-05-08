package com.sicredi.test.web.dto;

import java.util.Date;

/**
 * Objeto de transferência contendo dados de um votação.
 */
public class PollDto {

    private TopicDto topicDto;
    private Date createdOn;
    private int durationInMinutes;

    public PollDto(TopicDto topicDto, Date createdOn, int durationInMinutes) {
        this.topicDto = topicDto;
        this.createdOn = createdOn;
        this.durationInMinutes = durationInMinutes;
    }

    public TopicDto getTopicDto() {
        return topicDto;
    }

    public void setTopicDto(TopicDto topicDto) {
        this.topicDto = topicDto;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
