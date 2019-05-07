package com.sicredi.test.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.base.Preconditions;
import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.UserVote;
import com.sicredi.test.persistence.model.Vote;
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.persistence.service.ITopicService;
import com.sicredi.test.persistence.service.IVoteService;
import com.sicredi.test.web.converter.PollResultsConverter;
import com.sicredi.test.web.dto.PollDto;
import com.sicredi.test.web.dto.PollResultDto;
import com.sicredi.test.web.dto.TopicDto;
import com.sicredi.test.web.exception.MyResourceNotFoundException;
import com.sicredi.test.web.exception.PollAlreadyCreatedException;
import com.sicredi.test.web.util.RestPreconditions;
import com.sicredi.test.web.validator.TopicValidator;

@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    @Autowired
    private ITopicService topicService;
    @Autowired
    private IVoteService voteService;
    @Autowired
    private TopicValidator topicValidator;
    @Autowired
    private PollResultsConverter pollResultsConverter;
    
    public TopicController() {
        super();
    }

    @GetMapping(value = "/{id}")
    public Topic findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        try {
            return RestPreconditions.checkFound(topicService.findById(id));
        }
        catch (MyResourceNotFoundException exc) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Foo Not Found", exc);
        }
    }

    @GetMapping
    public List<TopicDto> findAll() {
        List<TopicDto> topics = topicService.findAll().stream().map(topic -> {
        	Poll poll = topic.getPoll();
        	return new TopicDto(topic.getId(), (poll != null ?
        			new PollDto(poll.getCreatedOn(), poll.getDurationInMinutes()) : null));
        }).collect(Collectors.toList());
		return topics;
    }

    @PostMapping(value = "/{id}/poll")
    @ResponseStatus(HttpStatus.CREATED)
    public Poll openPoll(@PathVariable("id") int topicId,
    		@RequestBody final Poll newPoll, final HttpServletResponse response) {
        Preconditions.checkNotNull(newPoll);
        Topic topic = topicService.findById(topicId);
        Poll poll = topic.getPoll();
        
        if (poll != null) {
        	throw new PollAlreadyCreatedException();
        }
        return topicService.createPoll(newPoll, topic);
    }

    @PutMapping(value = "/{id}/poll")
    @ResponseStatus(HttpStatus.OK)
    public UserVote vote(@PathVariable("id") int topicId,
    		@RequestBody final Vote vote, final HttpServletResponse response) {
        Preconditions.checkNotNull(vote);
        topicValidator.validateForVote(topicId, vote.getUsername());

        return voteService.createVote(vote, topicId);
    }

    @GetMapping(value = "/{id}/poll")
    @ResponseStatus(HttpStatus.OK)
    public PollResultDto result(@PathVariable("id") int topicId, 
    		final HttpServletResponse response) {
        List<VoteCount> votes = Optional.ofNullable(voteService.findByTopicId(topicId)).orElseGet(ArrayList::new);

        topicValidator.validatePollForGetResults(topicId);

        return pollResultsConverter.convert(votes, topicId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topic create(@RequestBody final Topic resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        return topicService.create(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        topicService.deleteById(id);
    }
}
