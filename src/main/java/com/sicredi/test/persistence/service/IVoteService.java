package com.sicredi.test.persistence.service;

import java.util.List;

import com.sicredi.test.persistence.model.UserVote;
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.persistence.model.VoteOption;

public interface IVoteService {

	List<VoteCount> findByTopicId(long topicId);

	UserVote createVote(long topicId, String username, VoteOption voteOption);

	boolean userAlreadyVote(String username, long topicId);

	
}
