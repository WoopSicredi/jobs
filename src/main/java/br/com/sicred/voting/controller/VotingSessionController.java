package br.com.sicred.voting.controller;

import br.com.sicred.voting.dto.VotingSessionDto;
import br.com.sicred.voting.dto.VotingSessionResultDto;
import br.com.sicred.voting.entity.VotingSession;
import br.com.sicred.voting.service.VotingSessionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/sessao")
@Api(description = "REST API para manipulação de sessões de votação das pautas ",
        basePath = "/sessao",
        consumes = "application/json",
        produces = "application/json")
public class VotingSessionController {

    private final VotingSessionService votingSessionService;

    @PostMapping
    public ResponseEntity<VotingSession> createVotingSession(@RequestBody VotingSessionDto dto) {
        return ResponseEntity.ok(
                votingSessionService.createVotingSession(dto)
        );
    }

    @PutMapping("/{sessionId}/{participantId}/{vote}")
    @ResponseStatus(HttpStatus.OK)
    public void voteForSession(@PathVariable("sessionId") Long sessionId,
                               @PathVariable("participantId") Long participantId,
                               @PathVariable("vote") Boolean vote) {
        votingSessionService.voteForSession(sessionId, participantId, vote);
    }

    @GetMapping("/{sessionId}/results")
    public ResponseEntity<VotingSessionResultDto> getSessionResults(
            @PathVariable("sessionId") Long sessionId) {
        return ResponseEntity.ok(votingSessionService.getVotingSessionResult(sessionId));
    }
}
