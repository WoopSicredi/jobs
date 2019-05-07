package com.sicredi.test.web.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.web.dto.PollResultDto;
import com.sicredi.test.web.dto.VoteCountDto;

@Component
public class PollResultsConverter {

	public PollResultDto convert(List<VoteCount> votes, Topic topic) {
		PollResultDto result = new PollResultDto(topic.getId(), topic.getName());
		
        if (votes != null) {
        	votes.forEach(vote -> {
        		result.getVotes().add(new VoteCountDto(vote.getVoteOption(), vote.getCount()));
        	});
        }
        return result;
	}
}
