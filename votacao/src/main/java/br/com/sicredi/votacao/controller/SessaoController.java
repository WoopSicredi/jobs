package br.com.sicredi.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacao.dto.SessaoDTO;
import br.com.sicredi.votacao.dto.requestbody.SessaoSaveDTO;
import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.service.SessaoService;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {
	
	private final SessaoService sessaoService;

	@Autowired
	public SessaoController(SessaoService sessaoService) {
		this.sessaoService = sessaoService;
	}
	
	@PostMapping
	public ResponseEntity<SessaoDTO> insert(@Valid @RequestBody SessaoSaveDTO sessaoSaveDTO) {
		Sessao sessaoCreated = sessaoService.save(sessaoSaveDTO.toModel());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(SessaoDTO.parseDTO(sessaoCreated));
	}

}
