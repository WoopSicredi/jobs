package com.vollino.poll.service.topic.persistence;

import com.vollino.poll.service.topic.business.domain.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Vollino
 */
@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
}
