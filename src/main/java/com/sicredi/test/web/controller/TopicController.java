package com.sicredi.test.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.UserVote;
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.persistence.service.ITopicService;
import com.sicredi.test.persistence.service.IVoteService;
import com.sicredi.test.web.converter.PollDtoToPollConverter;
import com.sicredi.test.web.converter.PollResultsConverter;
import com.sicredi.test.web.converter.TopicCreationDtoToTopicConverter;
import com.sicredi.test.web.converter.TopicToTopicDtoConverter;
import com.sicredi.test.web.dto.PollDto;
import com.sicredi.test.web.dto.PollResultDto;
import com.sicredi.test.web.dto.TopicCreationDto;
import com.sicredi.test.web.dto.TopicDto;
import com.sicredi.test.web.dto.VoteDto;
import com.sicredi.test.web.exception.PollAlreadyCreatedException;
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
    @Autowired
    private PollDtoToPollConverter pollDtoToPollConverter;
    @Autowired
    private TopicCreationDtoToTopicConverter topicCreationDtoToTopicConverter;
    @Autowired
    private TopicToTopicDtoConverter topicToTopicDtoConverter;
    
    public TopicController() {
        super();
    }

    @GetMapping(value = "/{id}")
    public Topic findById(@PathVariable("id") long topicId) {
    	return topicService.findById(topicId);
    }

    @GetMapping
    public List<TopicDto> findAll() {
        return topicService.findAll().stream().map(
        		topic -> topicToTopicDtoConverter.convert(topic))
        		.collect(Collectors.toList());
    }

    @PostMapping(value = "/{id}/poll")
    @ResponseStatus(HttpStatus.CREATED)
    public Poll openPoll(@PathVariable("id") long topicId, @RequestBody final PollDto newPoll) {
        Topic topic = topicService.findById(topicId);
        Poll poll = topic.getPoll();
        
        if (poll != null) {
        	throw new PollAlreadyCreatedException();
        }
        return topicService.createPoll(pollDtoToPollConverter.convert(newPoll), topic);
    }

    @PostMapping(value = "/{id}/vote")
    @ResponseStatus(HttpStatus.OK)
    public UserVote vote(@PathVariable("id") int topicId, @Valid @RequestBody final VoteDto vote) {
        topicValidator.validateForVote(topicId, vote.getUsername(), vote.getVoteOption());

        return voteService.createVote(topicId, vote.getUsername(), vote.getVoteOption());
    }

    @GetMapping(value = "/{id}/poll")
    @ResponseStatus(HttpStatus.OK)
    public PollResultDto result(@PathVariable("id") int topicId) {
        List<VoteCount> votes = Optional.ofNullable(voteService.findByTopicId(topicId)).orElseGet(ArrayList::new);

        topicValidator.validatePollForGetResults(topicId);

        return pollResultsConverter.convert(votes, topicId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topic create(@Valid @RequestBody final TopicCreationDto topic) {
        return topicService.create(topicCreationDtoToTopicConverter.convert(topic));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        topicService.deleteById(id);
    }
}
