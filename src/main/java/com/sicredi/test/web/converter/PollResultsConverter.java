package com.sicredi.test.web.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.web.dto.PollResultDto;
import com.sicredi.test.web.dto.VoteCountDto;

@Component
public class PollResultsConverter {

	public PollResultDto convert(List<VoteCount> votes, long topicId) {
		PollResultDto result = new PollResultDto(topicId);
		
        if (votes != null) {
        	votes.forEach(vote -> {
        		result.getVotes().add(new VoteCountDto(vote.getVoteOption(), vote.getCount()));
        	});
        }
        return result;
	}
}