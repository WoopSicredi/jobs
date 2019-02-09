package br.com.sicred.voting.controller;

import br.com.sicred.voting.dto.TopicDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicController {

    private final TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicDto dto) {
        return ResponseEntity.ok(
                topicRepository.save(Topic.builder().description(dto.getDescription()).build())
        );
    }
}
