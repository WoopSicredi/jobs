package com.sicredi.test.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe de persistência que modela o registro de votação de um determinado
 * usuário numa pauta específica.
 */
@Entity
public class UserVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "topic_id")
    private long topicId;

    @Column(nullable = false)
    private String username;

    public UserVote() {
    }

    public UserVote(long topicId, String username) {
        this.topicId = topicId;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }
}
