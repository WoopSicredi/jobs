package com.sicredi.voting.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sicredi.voting.assessment.VoteTotalizer;
import com.sicredi.voting.exception.SessionClosedException;
import com.sicredi.voting.exception.SessionNotStartedException;
import com.sicredi.voting.exception.UserAlreadyVotedThisTopicException;

@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private Set<Vote> votes = new HashSet<>();
	
	@Column(nullable = false, length = 64)
	private String name;
	
	@Column
	private LocalDateTime startTime;
	
	@Column
	private LocalDateTime endTime;


	Topic() {}
	
	public Topic(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void openSession(Long durationInMinutes) {
		validateSession();
		
		startTime = LocalDateTime.now();
		endTime = startTime.plusMinutes(durationInMinutes == null ? 1 : durationInMinutes);
	}
	
	public void addVote(Long userId, Boolean value) {
		validateSession();

		boolean alreadyExistsUser = votes
										.stream()
										.anyMatch(v -> v.getUserId() == userId);
		if (alreadyExistsUser) {
			throw new UserAlreadyVotedThisTopicException();
		}
		
		Vote vote = new Vote(this, userId, value);
		votes.add(vote);
	}
	
	public boolean hasPassed() {
		validateSession();
		
		return VoteTotalizer.obtainResult(votes);
	}
	
	private boolean hasSessionOpened() {
		return startTime != null;
	}

	private boolean hasSessionClosed() {
		return endTime.isBefore(LocalDateTime.now());
	}

	private void validateSession() {
		if (!hasSessionOpened()) throw new SessionNotStartedException();
		if (hasSessionClosed()) throw new SessionClosedException();
	}
}