package com.vollino.poll.service.vote.persistence;

import com.vollino.poll.service.vote.business.domain.Vote;
import com.vollino.poll.service.vote.business.domain.VoteId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Vollino
 */
@Repository
public interface VoteRepository extends CrudRepository<Vote, VoteId> {
}
