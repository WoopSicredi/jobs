package com.sicredi.test.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sicredi.test.persistence.model.VoteCount;

public interface IVoteCountDao extends JpaRepository<VoteCount, Long> {

	@Query("select v from VoteCount v where v.topicId=:topicId and v.voteOption=:voteOption")
	VoteCount findByTopicIdAnVoteOption(@Param("topicId") long topicId,
			@Param("voteOption") String voteOption);

	List<VoteCount> findByTopicId(long topicId);
    
}
