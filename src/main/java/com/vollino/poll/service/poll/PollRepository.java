package com.vollino.poll.service.poll;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Vollino
 */
@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {
}
