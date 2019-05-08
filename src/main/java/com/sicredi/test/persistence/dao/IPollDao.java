package com.sicredi.test.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.test.persistence.model.Poll;

/**
 * Repositório de votações de pautas.
 */
public interface IPollDao extends JpaRepository<Poll, Long> {

}
