package com.sicredi.test.web.validator;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.VoteOption;
import com.sicredi.test.persistence.service.ITopicService;
import com.sicredi.test.persistence.service.IVoteService;
import com.sicredi.test.web.exception.ClosedTopicException;
import com.sicredi.test.web.exception.ExpiredTopicException;
import com.sicredi.test.web.exception.InvalidTopicException;
import com.sicredi.test.web.exception.InvalidVoteOptionException;
import com.sicredi.test.web.exception.OpenTopicException;
import com.sicredi.test.web.exception.UserAlreadyVoteException;

/**
 * Validator related to Topic actions.
 */
@Component
public class TopicValidator {

	@Autowired
    private ITopicService topicService;
    @Autowired
    private IVoteService voteService;

	public void validatePollForGetResults(long topicId) {
		Topic topic = topicService.findById(topicId);
        Poll poll = topic.getPoll();

        if (poll == null) {
        	throw new ClosedTopicException();
        } else if (isPollOpen(poll)) {
        	throw new OpenTopicException();
        }
	}

	public void validateForVote(long topicId, String username, VoteOption voteOption) {
        Topic topic = topicService.findById(topicId);

		if (topic == null) {
			throw new InvalidTopicException();
		}
		Poll poll = topic.getPoll();

		if (voteOption == null) {
			throw new InvalidVoteOptionException();
		} else if (poll == null) {
			throw new ClosedTopicException();
		} else if (isPollExpired(poll)) {
			throw new ExpiredTopicException();
		} else if (voteService.userAlreadyVote(username, topicId)) {
			throw new UserAlreadyVoteException();
		}
	}

	private boolean isPollOpen(Poll poll) {
		Instant now = Instant.now();
		Instant creation = poll.getCreatedOn().toInstant();
		long pollDuration = TimeUnit.SECONDS.convert(poll.getDurationInMinutes(), TimeUnit.MINUTES);

		return now.isBefore(creation.plusSeconds(pollDuration));
	}

	private boolean isPollExpired(Poll poll) {
		Instant now = Instant.now();
		Instant creation = poll.getCreatedOn().toInstant();
		long pollDuration = TimeUnit.SECONDS.convert(poll.getDurationInMinutes(), TimeUnit.MINUTES);

		return now.isAfter(creation.plusSeconds(pollDuration));
	}
}
