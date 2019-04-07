package br.com.sicredi.votacao.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacao.dto.AssociadoDTO;
import br.com.sicredi.votacao.dto.requestbody.AssociadoSaveDTO;
import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.service.AssociadoService;

@RestController
@RequestMapping("/api/associados")
public class AssociadoController {

	private final AssociadoService associadoService;

	@Autowired
	public AssociadoController(AssociadoService associadoService) {
		this.associadoService = associadoService;
	}
	
	@GetMapping
	public ResponseEntity<List<AssociadoDTO>> findAll() {
		List<Associado> associados = associadoService.findAll();
		return ResponseEntity
				.ok(associados.stream()
						.map(AssociadoDTO::parseDTO)
						.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDTO> findById(@PathVariable("id") Long id) {
		Optional<Associado> associado = associadoService.findById(id);
		if (!associado.isPresent()) {
			// TODO throws Exception
		}
		return ResponseEntity
				.ok(AssociadoDTO.parseDTO(associado.get()));
	}
	
	@PostMapping
	public ResponseEntity<AssociadoDTO> incluir(@Valid @RequestBody AssociadoSaveDTO associadoSaveDTO) {
		Associado associadoCreated = associadoService.save(associadoSaveDTO.toModel());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(AssociadoDTO.parseDTO(associadoCreated));
	}
	
}
