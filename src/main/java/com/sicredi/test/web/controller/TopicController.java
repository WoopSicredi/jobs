package com.sicredi.test.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.persistence.service.ITopicPersistenceService;
import com.sicredi.test.persistence.service.IVotePersistenceService;
import com.sicredi.test.web.converter.PollCreationDtoToPollConverter;
import com.sicredi.test.web.converter.PollResultsConverter;
import com.sicredi.test.web.converter.PollToPollDtoConverter;
import com.sicredi.test.web.converter.TopicCreationDtoToTopicConverter;
import com.sicredi.test.web.converter.TopicToTopicDtoConverter;
import com.sicredi.test.web.dto.PollCreationDto;
import com.sicredi.test.web.dto.PollDto;
import com.sicredi.test.web.dto.PollResultDto;
import com.sicredi.test.web.dto.TopicCreationDto;
import com.sicredi.test.web.dto.TopicDto;
import com.sicredi.test.web.dto.VoteDto;
import com.sicredi.test.web.validator.TopicValidator;

@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private static Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private ITopicPersistenceService topicService;
    @Autowired
    private IVotePersistenceService voteService;
    @Autowired
    private TopicValidator topicValidator;
    @Autowired
    private PollResultsConverter pollResultsConverter;
    @Autowired
    private PollCreationDtoToPollConverter pollDtoToPollConverter;
    @Autowired
    private TopicCreationDtoToTopicConverter topicCreationDtoToTopicConverter;
    @Autowired
    private TopicToTopicDtoConverter topicToTopicDtoConverter;
    @Autowired
    private PollToPollDtoConverter pollToPollDtoConverter;

    public TopicController() {
        super();
    }

    @GetMapping(value = "/{topicId}")
    public TopicDto findById(@PathVariable("topicId") long topicId) {
        LOGGER.info("Retrieving topic {}", topicId);
        return topicToTopicDtoConverter.convert(topicService.findById(topicId));
    }

    @GetMapping
    public List<TopicDto> findAll() {
        LOGGER.info("Retrieving all topics");
        return topicService.findAll().stream().map(topic -> topicToTopicDtoConverter.convert(topic))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/{topicId}/poll")
    @ResponseStatus(HttpStatus.CREATED)
    public PollDto openPoll(@PathVariable("topicId") long topicId, @RequestBody PollCreationDto newPoll) {
        LOGGER.info("About to open poll for topic {}", topicId);
        Topic topic = topicService.findById(topicId);

        topicValidator.validatePollIsNotCreated(topic);
        LOGGER.debug("About to create poll", topicId);
        Poll poll = topicService.createPoll(pollDtoToPollConverter.convert(newPoll), topic);
        LOGGER.info("Poll has been created. {}", poll);
        return pollToPollDtoConverter.convert(poll);
    }

    @PostMapping(value = "/{topicId}/vote")
    @ResponseStatus(HttpStatus.OK)
    public void vote(@PathVariable("topicId") long topicId, @Valid @RequestBody VoteDto vote) {
        topicValidator.validateForVote(topicId, vote.getUsername(), vote.getVoteOption());

        voteService.createVote(topicId, vote.getUsername(), vote.getVoteOption());
        LOGGER.info("User {} has vote on topic {}", vote.getUsername(), topicId);
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
