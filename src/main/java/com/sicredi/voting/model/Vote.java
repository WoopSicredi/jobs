package com.sicredi.voting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@ManyToOne
	private Topic topic;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Boolean value;
	
	
	Vote() {}
	
	public Vote(Topic topic, Long userId, Boolean value) {
		this.topic = topic;
		this.userId = userId;
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}
	
	public Long getUserId() {
		return userId;
	}
}