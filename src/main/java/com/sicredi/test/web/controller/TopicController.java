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
import com.sicredi.test.persistence.service.ITopicPersistenceService;
import com.sicredi.test.persistence.service.IVoteService;
import com.sicredi.test.web.converter.PollDtoToPollConverter;
import com.sicredi.test.web.converter.PollResultsConverter;
import com.sicredi.test.web.converter.TopicCreationDtoToTopicConverter;
import com.sicredi.test.web.converter.TopicToTopicDtoConverter;
import com.sicredi.test.web.dto.PollCreationDto;
import com.sicredi.test.web.dto.PollResultDto;
import com.sicredi.test.web.dto.TopicCreationDto;
import com.sicredi.test.web.dto.TopicDto;
import com.sicredi.test.web.dto.VoteDto;
import com.sicredi.test.web.validator.TopicValidator;

@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    @Autowired
    private ITopicPersistenceService topicService;
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

    @GetMapping(value = "/{topicId}")
    public TopicDto findById(@PathVariable("topicId") long topicId) {
    	return topicToTopicDtoConverter.convert(topicService.findById(topicId));
    }

    @GetMapping
    public List<TopicDto> findAll() {
        return topicService.findAll().stream().map(
        		topic -> topicToTopicDtoConverter.convert(topic))
        		.collect(Collectors.toList());
    }

    @PostMapping(value = "/{topicId}/poll")
    @ResponseStatus(HttpStatus.CREATED)
    public Poll openPoll(@PathVariable("topicId") long topicId, @RequestBody PollCreationDto newPoll) {
        Topic topic = topicService.findById(topicId);

        topicValidator.validatePollIsNotCreated(topic);
        return topicService.createPoll(pollDtoToPollConverter.convert(newPoll), topic);
    }

    @PostMapping(value = "/{topicId}/vote")
    @ResponseStatus(HttpStatus.OK)
    public UserVote vote(@PathVariable("topicId") long topicId, @Valid @RequestBody VoteDto vote) {
        topicValidator.validateForVote(topicId, vote.getUsername(), vote.getVoteOption());

        return voteService.createVote(topicId, vote.getUsername(), vote.getVoteOption());
    }

    @GetMapping(value = "/{topicId}/poll")
    @ResponseStatus(HttpStatus.OK)
    public PollResultDto result(@PathVariable("topicId") long topicId) {
        List<VoteCount> votes = Optional.ofNullable(voteService.findByTopicId(topicId)).orElseGet(ArrayList::new);

        topicValidator.validatePollForGetResults(topicId);
        Topic topic = topicService.findById(topicId);

        return pollResultsConverter.convert(votes, topic);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicDto create(@Valid @RequestBody TopicCreationDto topicDto) {
        Topic topic = topicService.create(topicCreationDtoToTopicConverter.convert(topicDto));
        return topicToTopicDtoConverter.convert(topic);
    }

    @DeleteMapping(value = "/{topicId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("topicId") long id) {
        topicService.deleteById(id);
    }
}
