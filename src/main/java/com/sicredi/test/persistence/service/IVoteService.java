package com.sicredi.test.persistence.service;

import java.util.List;

import com.sicredi.test.persistence.model.UserVote;
import com.sicredi.test.persistence.model.Vote;
import com.sicredi.test.persistence.model.VoteCount;

public interface IVoteService {

	List<VoteCount> findByTopicId(long topicId);

	UserVote createVote(Vote vote, long topicId);

	boolean userAlreadyVote(String username, long topicId);
	
}
