package br.com.sicredi.votacao.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacao.dto.PautaDTO;
import br.com.sicredi.votacao.dto.ResultadoPautaDTO;
import br.com.sicredi.votacao.dto.requestbody.PautaSaveDTO;
import br.com.sicredi.votacao.exception.ResourceNotFoundException;
import br.com.sicredi.votacao.message.MessageKey;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.service.PautaService;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {
	
	private final PautaService pautaService;
	
	public PautaController(PautaService pautaService) {
		this.pautaService = pautaService;
	}
	
	@GetMapping
	public ResponseEntity<List<PautaDTO>> findAll() {
		List<Pauta> pautas = pautaService.findAll();
		return ResponseEntity
				.ok(pautas.stream()
						.map(PautaDTO::parseDTO)
						.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PautaDTO> findById(@PathVariable("id") Long id) {
		Optional<Pauta> pauta = pautaService.findById(id);
		if (!pauta.isPresent()) {
			throw new ResourceNotFoundException(MessageKey.PAUTA_NOT_FOUND, id);
		}
		return ResponseEntity
				.ok(PautaDTO.parseDTO(pauta.get()));
	}
	
	@PostMapping
	public ResponseEntity<PautaDTO> insert(@Valid @RequestBody PautaSaveDTO pautaSaveDTO) {
		Pauta pautaCreated = pautaService.save(pautaSaveDTO.toModel());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(PautaDTO.parseDTO(pautaCreated));
	}
	
	@GetMapping("/{id}/resultados")
	public ResponseEntity<ResultadoPautaDTO> accountingById(@PathVariable("id") Long id) {
		Optional<Pauta> pauta = pautaService.findById(id);
		
		if (!pauta.isPresent()) {
			throw new ResourceNotFoundException(MessageKey.PAUTA_NOT_FOUND, id);
		}
		return ResponseEntity.ok(pautaService.accounting(pauta.get()));
	}
	
}
