package br.com.sicred.voting.controller;

import br.com.sicred.voting.dto.TopicDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.repository.TopicRepository;
import br.com.sicred.voting.service.VotingSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/pauta")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "REST API para criação de pautas de votação",
        basePath = "/pauta",
        consumes = "application/json",
        produces = "application/json")
public class TopicController {

    private final TopicRepository topicRepository;
    private final VotingSectionService votingSectionService;

    @PostMapping
    @ApiOperation(value="Cria uma pauta para votação")
    public ResponseEntity<Topic> createTopic(
            @RequestBody @Valid TopicDto dto) {
        return ResponseEntity.ok(
                topicRepository.save(Topic.builder().description(dto.getDescription()).build())
        );
    }
}
