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
public class VotingSectionResultDto {

    private VotingSession votingSection;
    private Double yesPercentage;
    private Double noPercentage;
}
