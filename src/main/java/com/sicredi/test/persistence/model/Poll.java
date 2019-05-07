package com.sicredi.test.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.Objects;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("poll")
@Entity
public class Poll implements Serializable {

	private static final long serialVersionUID = 6854668792809573665L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@OneToOne
    @MapsId
	private Topic topic;
	
	@Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

    @Column(nullable = false)
    private int durationInMinutes;

    public Poll() {
    }

    public Poll(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

    public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

    public Date getCreatedOn() {
		return createdOn;
	}

    public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

    public Topic getTopic() {
		return topic;
	}
    
    public void setTopic(Topic topic) {
		this.topic = topic;
	}

    @Override
    public int hashCode() {
    	return Objects.hashCode(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Poll other = (Poll) obj;
        
        return other.id == this.id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Poll [id=").append(id).append("]");
        return builder.toString();
    }

}
