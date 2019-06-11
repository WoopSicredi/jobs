package br.com.sicredi.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacao.dto.VotoDTO;
import br.com.sicredi.votacao.dto.requestbody.VotoSaveDTO;
import br.com.sicredi.votacao.model.Voto;
import br.com.sicredi.votacao.service.VotoService;

@RestController
@RequestMapping("/api/votos")
public class VotoController {
	
	private final VotoService votoService;

	@Autowired
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}

	@PostMapping
	public ResponseEntity<VotoDTO> insert(@Valid @RequestBody VotoSaveDTO votoSaveDTO) {
		Voto votoCreated = votoService.save(votoSaveDTO.toModel());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(VotoDTO.parseDTO(votoCreated));
	}
	
}