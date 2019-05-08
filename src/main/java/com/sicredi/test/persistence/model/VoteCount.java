package com.sicredi.test.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

/**
 * Classe de persistência que modela a contagem de votos de um determinada opção
 * de uma pauta de votação.
 */
@Entity
public class VoteCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "topic_id")
    private long topicId;

    @Column(nullable = false)
    private String voteOption;

    @Column(nullable = false)
    private long count;

    public VoteCount() {
    }

    public VoteCount(long topicId, String voteOption) {
        this.topicId = topicId;
        this.voteOption = voteOption;
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

    public String getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(String voteOption) {
        this.voteOption = voteOption;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public synchronized void increment() {
        this.count++;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VoteCount other = (VoteCount) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
