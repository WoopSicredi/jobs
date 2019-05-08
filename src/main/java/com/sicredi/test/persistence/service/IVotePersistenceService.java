package com.sicredi.test.persistence.service;

import java.util.List;

import com.sicredi.test.persistence.model.UserVote;
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.persistence.model.VoteOption;

/**
 * Interface de persistência de dados relacionados a votos.
 */
public interface IVotePersistenceService {

    List<VoteCount> findByTopicId(long topicId);

    UserVote createVote(long topicId, String username, VoteOption voteOption);

    boolean userAlreadyVote(String username, long topicId);

}
