package com.sicredi.voting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.voting.exception.SessionNotFoundException;
import com.sicredi.voting.model.Topic;
import com.sicredi.voting.repository.TopicRepository;

@Service
public class VotingService {

	@Autowired
	public TopicRepository repository;

	
	public Topic create(String topicName) {
		return repository.save(new Topic(topicName));
	}
	
	public void openSession(Long topicId, Long durationInMinutes) {
		Topic topic = obtainTopic(topicId);
		repository.save(topic);
	}
	
	public void vote(Long topicId, Long userId, Boolean value) {
		Topic topic = obtainTopic(topicId);
		topic.addVote(userId, value);
		repository.save(topic);
	}
	
	public Topic obtainTopic(Long topicId) {
		Optional<Topic> topicOptional = repository.findById(topicId);
		validateTopicPresence(topicOptional);
		return topicOptional.get();
	}
	
	private void validateTopicPresence(Optional<Topic> topicOptional) {
		if (topicOptional.isEmpty()) {
			throw new SessionNotFoundException();
		}
	}
}

