package com.vollino.poll.service.poll.persistence;

import com.vollino.poll.service.poll.business.domain.Poll;
import com.vollino.poll.service.poll.business.domain.VoteCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bruno Vollino
 */
@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {

    @Query(value = "SELECT new com.vollino.poll.service.poll.business.domain.VoteCount(v.pollOption, COUNT(v))" +
            " FROM Vote v" +
            " WHERE v.id.pollId = :pollId" +
            " GROUP BY v.pollOption" +
            " ORDER BY v.pollOption")
    List<VoteCount> findVoteCountByPollGroupByOption(@Param("pollId") Long pollId);

    List<Poll> findByTopicId(Long topicId);
}
