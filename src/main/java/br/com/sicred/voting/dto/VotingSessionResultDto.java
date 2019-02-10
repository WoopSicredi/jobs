package br.com.sicred.voting.dto;

import br.com.sicred.voting.entity.VotingSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotingSessionResultDto {

    private VotingSession votingSession;
    private Double yesPercentage;
    private Double noPercentage;
}
