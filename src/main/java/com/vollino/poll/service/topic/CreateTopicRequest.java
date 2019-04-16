package com.vollino.poll.service.topic;

import javax.validation.constraints.NotBlank;

/**
 * @author Bruno Vollino
 */
public class CreateTopicRequest {

    @NotBlank(message = "Description is mandatory")
    private String description;

    public String getDescription() {
        return description;
    }
}
