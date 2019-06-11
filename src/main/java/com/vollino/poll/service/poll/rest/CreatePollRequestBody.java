package com.vollino.poll.service.poll.rest;

import io.swagger.annotations.ApiModelProperty;

import java.time.ZonedDateTime;

/**
 * @author Bruno Vollino
 */
public class CreatePollRequestBody {

    private String description;

    @ApiModelProperty(example = "2019-04-26T08:30:00-03:00[Brazil/East]")
    private ZonedDateTime endDate;

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }
}
