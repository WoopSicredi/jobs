package com.sicredi.voting.assessment;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.sicredi.voting.model.Topic;
import com.sicredi.voting.model.Vote;

@RunWith(SpringRunner.class)
public class VoteTotalizerTest {

	private Topic topic = new Topic("Teste");
	
	
	@Test
	public void noVotes() {

		Set<Vote> votes = new HashSet<>();
		
		boolean result = VoteTotalizer.obtainResult(votes);
		
		Assert.assertFalse(result);
	}

	@Test
	public void passed() {
		
		Set<Vote> votes = new HashSet<>();
		Vote vote = new Vote(topic, 1L, true);
		votes.add(vote);
		
		boolean result = VoteTotalizer.obtainResult(votes);
		
		Assert.assertTrue(result);
	}

	@Test
	public void notPassed() {
		
		Set<Vote> votes = new HashSet<>();
		Vote vote = new Vote(topic, 1L, false);
		votes.add(vote);
		
		boolean result = VoteTotalizer.obtainResult(votes);
		
		Assert.assertFalse(result);
	}
}
