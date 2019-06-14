
package com.sicredi.voting.assessment;

import java.util.Set;

import com.sicredi.voting.model.Vote;

public class VoteTotalizer {

	public static boolean obtainResult(Set<Vote> votes) {
		int voteCount = votes.size();

		if (voteCount == 0) {
			return false;
		}

		long favorableVotes = votes
								.stream()
								.filter(v -> v.getValue())
								.count();

		return favorableVotes / voteCount > 0.5;
	}
}