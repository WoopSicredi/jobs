package com.vollino.poll.service.topic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Vollino
 */
@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
}
