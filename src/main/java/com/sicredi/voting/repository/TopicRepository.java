package com.sicredi.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicredi.voting.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {}
