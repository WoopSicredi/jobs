package com.sicredi.test.web.validator;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.VoteOption;
import com.sicredi.test.persistence.service.ITopicPersistenceService;
import com.sicredi.test.persistence.service.IVotePersistenceService;
import com.sicredi.test.web.exception.ClosedTopicException;
import com.sicredi.test.web.exception.ExpiredTopicException;
import com.sicredi.test.web.exception.InvalidTopicException;
import com.sicredi.test.web.exception.InvalidVoteOptionException;
import com.sicredi.test.web.exception.OpenTopicException;
import com.sicredi.test.web.exception.PollAlreadyCreatedException;
import com.sicredi.test.web.exception.UserAlreadyVoteException;

/**
 * Validador de ações sobre pautas.
 */
@Component
public class TopicValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicValidator.class);
    @Autowired
    private ITopicPersistenceService topicService;
    @Autowired
    private IVotePersistenceService voteService;

    public void validatePollForGetResults(long topicId) {
        Topic topic = topicService.findById(topicId);
        Poll poll = getPollIfValid(topic);

        if (poll == null) {
            LOGGER.error("Topic is not open yet. Topic ID = {}", topic);
            throw new ClosedTopicException();
        } else if (isPollOpen(poll)) {
            LOGGER.error("Topic is not closed yet. Topic ID = {}, {}", topic, poll);
            throw new OpenTopicException();
        }
    }

    public void validateForVote(long topicId, String username, VoteOption voteOption) {
        Topic topic = topicService.findById(topicId);
        Poll poll = getPollIfValid(topic);

        if (voteOption == null) {
            LOGGER.error("Invalid vote option!");
            throw new InvalidVoteOptionException();
        } else if (poll == null) {
            LOGGER.error("Topic is not open yet. Topic ID = {}", topic);
            throw new ClosedTopicException();
        } else if (isPollExpired(poll)) {
            LOGGER.error("Poll has expired. {}", poll);
            throw new ExpiredTopicException();
        } else if (voteService.userAlreadyVote(username, topicId)) {
            LOGGER.error("User {} has already vote on this topic {}", username, topic);
            throw new UserAlreadyVoteException();
        }
    }

    public void validatePollIsNotCreated(Topic topic) {
        Poll poll = getPollIfValid(topic);

        if (poll != null) {
            LOGGER.error("A poll is already created for topic {}. {} -> ", topic.getName(), poll);
            throw new PollAlreadyCreatedException();
        }
    }

    public Poll getPollIfValid(Topic topic) {
        if (topic == null) {
            LOGGER.error("Invalid topic!!");
            throw new InvalidTopicException();
        }
        return topic.getPoll();
    }

    private boolean isPollOpen(Poll poll) {
        Instant now = Instant.now();
        Instant expiration = getExpirationInstant(poll);

        return now.isBefore(expiration);
    }

    private boolean isPollExpired(Poll poll) {
        Instant now = Instant.now();
        Instant expirationInstant = getExpirationInstant(poll);

        return now.isAfter(expirationInstant);
    }

    private Instant getExpirationInstant(Poll poll) {
        Instant creation = poll.getCreatedOn().toInstant();
        long pollDuration = TimeUnit.SECONDS.convert(poll.getDurationInMinutes(), TimeUnit.MINUTES);

        return creation.plusSeconds(pollDuration);
    }

}
