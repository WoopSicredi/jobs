package br.com.sicred.voting.controller;

import br.com.sicred.voting.dto.VotingSectionDto;
import br.com.sicred.voting.dto.VotingSectionResultDto;
import br.com.sicred.voting.entity.VotingSection;
import br.com.sicred.voting.service.VotingSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/secao")
public class VotingSectionController {

    private final VotingSectionService votingSectionService;

    @PostMapping
    public ResponseEntity<VotingSection> createVotingSection(@RequestBody VotingSectionDto dto) {
        return ResponseEntity.ok(
                votingSectionService.createVotingSection(dto)
        );
    }

    @PutMapping("/{sessionId}/{participantId}/{vote}")
    @ResponseStatus(HttpStatus.OK)
    public void voteForSection(@PathVariable("sessionId") Long sessionId,
                               @PathVariable("participantId") Long participantId,
                               @PathVariable("vote") Boolean vote) {
        votingSectionService.voteForSection(sessionId, participantId, vote);
    }

    @GetMapping("/{sessionId}/results")
    public ResponseEntity<VotingSectionResultDto> sectionResults(
            @PathVariable("sessionId") Long sessionId) {
        return ResponseEntity.ok(votingSectionService.getVotingSectionResult(sessionId));
    }
}
