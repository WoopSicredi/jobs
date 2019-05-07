package com.sicredi.test.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sicredi.test.persistence.model.UserVote;

public interface IUserVoteDao extends JpaRepository<UserVote, Long> {

	@Query("select v from UserVote v where v.topicId=:topicId and v.username=:username")
	UserVote findByTopicIdAndUsername(@Param("topicId") long topicId,
			@Param("username") String username);
    
}
