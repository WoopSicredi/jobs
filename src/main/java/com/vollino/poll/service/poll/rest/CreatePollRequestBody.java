package com.vollino.poll.service.poll.rest;

import java.time.ZonedDateTime;

/**
 * @author Bruno Vollino
 */
public class CreatePollRequestBody {

    private String description;
    private ZonedDateTime endDate;

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }
}
