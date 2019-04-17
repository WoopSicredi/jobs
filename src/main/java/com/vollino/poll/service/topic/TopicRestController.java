package com.vollino.poll.service.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruno Vollino
 */
@RestController
@RequestMapping("/topic")
public class TopicRestController {

    private TopicService topicService;

    @Autowired
    public TopicRestController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Topic> create(@RequestBody CreateTopicRequest createRequest) {
        Topic created = topicService.create(new Topic(null, createRequest.getDescription()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(created);
    }
}
