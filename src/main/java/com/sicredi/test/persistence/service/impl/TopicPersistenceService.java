package com.sicredi.test.persistence.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sicredi.test.persistence.dao.IPollDao;
import com.sicredi.test.persistence.dao.ITopicDao;
import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.service.ITopicPersistenceService;

/**
 * Implementa a persistência de dados de pautas.
 */
@Service
@Transactional
public class TopicPersistenceService implements ITopicPersistenceService {

    @Autowired
    private IPollDao pollDao;

    @Autowired
    private ITopicDao topicDao;

    public TopicPersistenceService() {
        super();
    }

    public IPollDao getPollDao() {
        return pollDao;
    }

    public ITopicDao getTopicDao() {
        return topicDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Topic findById(final long id) {
        return getTopicDao().findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Topic create(final Topic entity) {
        return getTopicDao().save(entity);
    }

    @Override
    public Poll createPoll(final Poll poll, Topic topic) {
        poll.setTopic(topic);
        getTopicDao().save(topic);
        return getPollDao().save(poll);
    }

    @Override
    public void delete(final Topic entity) {
        getTopicDao().delete(entity);
    }

    @Override
    public void deleteById(final long entityId) {
        getTopicDao().deleteById(entityId);
    }

    @Transactional(readOnly = true)
    public List<Topic> findAll() {
        return Lists.newArrayList(getTopicDao().findAll());
    }
}
