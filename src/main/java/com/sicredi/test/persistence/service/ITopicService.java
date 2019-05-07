package com.sicredi.test.persistence.service;

import java.util.List;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.UserVote;

public interface ITopicService {

	Topic findById(final long id);

    List<Topic> findAll();

    Topic create(Topic entity);

    void delete(Topic entity);

    void deleteById(long entityId);

	Poll createPoll(Poll poll, Topic topic);

	Poll createVote(UserVote resource, int topicId); 

}
