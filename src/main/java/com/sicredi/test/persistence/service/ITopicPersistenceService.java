package com.sicredi.test.persistence.service;

import java.util.List;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;

/**
 * Interface para serviço de persistência de dados de pautas.
 */
public interface ITopicPersistenceService {

    Topic findById(final long id);

    List<Topic> findAll();

    Topic create(Topic entity);

    Poll createPoll(Poll poll, Topic topic);

    void delete(Topic entity);

    void deleteById(long entityId);

}
