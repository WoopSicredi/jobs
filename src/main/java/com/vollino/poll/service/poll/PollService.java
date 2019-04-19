package com.vollino.poll.service.poll;

import com.google.common.base.Preconditions;
import com.vollino.poll.service.exception.DataIntegrityException;
import com.vollino.poll.service.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

/**
 * @author Bruno Vollino
 */
@Service
@Validated
public class PollService {

    private final PollRepository pollRepository;
    private final TopicRepository topicRepository;
    private final Clock clock;

    @Autowired
    public PollService(
            PollRepository pollRepository,
            TopicRepository topicRepository,
            Clock clock) {
        this.pollRepository = pollRepository;
        this.topicRepository = topicRepository;
        this.clock = clock;
    }

    public Poll create(@Valid Poll poll) {
        Preconditions.checkArgument(poll.getId() == null, "A new Poll must have no ID on creation");
        if (poll.getEndDate() == null) {
            poll.setEndDate(clock.now().plus(Duration.ofMinutes(1)));
        }
        if (!topicRepository.existsById(poll.getTopicId())) {
            throw new DataIntegrityException(String.format("Topic with id=%d not found", poll.getTopicId()));
        }

        return pollRepository.save(poll);
    }

    public Optional<Poll> getPoll(Long pollId) {
        Preconditions.checkArgument(pollId != null, "pollId must not be null");

        Optional<Poll> poll = pollRepository.findById(pollId);
        if (poll.isPresent()) {
            poll.get().setResults(getPollResults(pollId));
        }

        return poll;
    }

    private List<VoteCount> getPollResults(Long pollId) {
        Preconditions.checkArgument(pollId != null, "pollId must not be null");

        return pollRepository.findVoteCountByPollGroupByOption(pollId);
    }

    public List<Poll> getPollsByTopic(Long topicId) {
        List<Poll> polls = pollRepository.findByTopicId(topicId);
        polls.forEach(poll -> poll.setResults(getPollResults(poll.getId())));

        return polls;
    }
}
