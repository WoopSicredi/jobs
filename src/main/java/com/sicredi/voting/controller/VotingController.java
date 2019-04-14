package com.sicredi.voting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.voting.model.Topic;
import com.sicredi.voting.request.CreateTopicRequest;
import com.sicredi.voting.request.OpenSessionRequest;
import com.sicredi.voting.request.VoteRequest;
import com.sicredi.voting.response.ResultResponse;
import com.sicredi.voting.response.TopicResponse;
import com.sicredi.voting.service.VotingService;

@RestController
public class VotingController {

	@Autowired
	private VotingService service;
	
	
	@PostMapping("/create")
	public TopicResponse create(@Valid @RequestBody CreateTopicRequest topic) {
		return new TopicResponse(service.create(topic.getName()).getId());
	}
	
	@PutMapping("/openSession")
	public void openSession(@Valid @RequestBody OpenSessionRequest request) {
		service.openSession(request.getTopicId(), request.getDurationInMinutes());
	}
	
	@PutMapping("/vote")
	public void vote(@Valid @RequestBody VoteRequest vote) {
		service.vote(vote.getTopicId(), vote.getUserId(), vote.getValue());
	}
	
	@GetMapping("/result/{sessionId}")
	public ResultResponse result(@Valid @PathVariable Long sessionId) {
		Topic topic = service.obtainTopic(sessionId);
		return new ResultResponse(topic.getName(), topic.hasPassed());
	}
}
