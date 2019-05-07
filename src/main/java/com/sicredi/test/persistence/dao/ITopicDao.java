package com.sicredi.test.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.test.persistence.model.Topic;

public interface ITopicDao extends JpaRepository<Topic, Long> {
    
}
