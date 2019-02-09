package br.com.sicred.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingSectionDto {
    private Long topicId;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
}
