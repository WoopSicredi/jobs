package com.vollino.poll.service.topic.rest;

import com.vollino.poll.service.poll.business.PollService;
import com.vollino.poll.service.poll.business.domain.Poll;
import com.vollino.poll.service.poll.rest.CreatePollRequestBody;
import com.vollino.poll.service.topic.business.TopicService;
import com.vollino.poll.service.topic.business.domain.Topic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bruno Vollino
 */
@Api("topics")
@RestController
public class TopicRestController {

    private TopicService topicService;
    private PollService pollService;

    @Autowired
    public TopicRestController(TopicService topicService, PollService pollService) {
        this.topicService = topicService;
        this.pollService = pollService;
    }

    @ApiOperation("Create a Topic")
    @PostMapping(path = "/topics", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Topic> create(@RequestBody() CreateTopicRequest createRequest) {
        Topic created = topicService.create(new Topic(null, createRequest.getDescription()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(created);
    }

    @ApiOperation("Get all Topics")
    @GetMapping(path = "/topics", produces = "application/json")
    public ResponseEntity<List<Topic>> get() {
        List<Topic> topics = topicService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(topics);
    }

    @ApiOperation("Create a Poll under a Topic")
    @PostMapping(path = "/topics/{topicId}/polls", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Poll> create(
            @PathVariable Long topicId,
            @RequestBody CreatePollRequestBody createRequest) {
        Poll persisted = pollService.create(
                new Poll(null,
                        topicId,
                        createRequest.getDescription(),
                        createRequest.getEndDate()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(persisted);
    }

    @ApiOperation("Get all Polls under a given Topic")
    @GetMapping(path = "/topics/{topicId}/polls", produces = "application/json")
    public ResponseEntity<List<Poll>> getPolls(@PathVariable("topicId") Long topicId) {
        List<Poll> polls = pollService.getPollsByTopic(topicId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(polls);
    }
}
