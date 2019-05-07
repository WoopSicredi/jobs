package com.sicredi.test.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.test.persistence.model.Poll;

public interface IPollDao extends JpaRepository<Poll, Long> {
    
}
