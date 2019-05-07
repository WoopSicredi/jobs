package com.sicredi.test.persistence.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.google.common.base.Objects;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Topic")
@Entity
public class Topic implements Serializable {

	private static final long serialVersionUID = 6854668792809573665L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "topic",
    		cascade = CascadeType.ALL, orphanRemoval = true)
    private Poll poll;

    public Topic() {
    }

    public Topic(final String name) {
    	this();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    
    public Poll getPoll() {
		return poll;
	}
    
    public void setPoll(Poll poll) {
		this.poll = poll;
	}

    @Override
    public int hashCode() {
    	return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Topic other = (Topic) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Foo [name=").append(name).append("]");
        return builder.toString();
    }

}
