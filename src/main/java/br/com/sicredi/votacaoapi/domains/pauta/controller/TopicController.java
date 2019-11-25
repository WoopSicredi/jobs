package br.com.sicredi.votacaoapi.domains.pauta.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacaoapi.domains.pauta.dto.CriarPautaRequest;
import br.com.sicredi.votacaoapi.domains.pauta.dto.CriarPautaResponse;
import br.com.sicredi.votacaoapi.domains.pauta.service.CriarPautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("pauta")
@Api(tags = "TopicEndpoint")
public class TopicController {

	private CriarPautaService criarPautaService;

	@Autowired
	public TopicController(CriarPautaService criarPautaService) {
		this.criarPautaService = criarPautaService;
	}

	@ApiOperation(value = "Criar pauta")
	@PostMapping("/criar")
	@ResponseBody
	@Transactional
	public ResponseEntity<CriarPautaResponse> create(@Valid @RequestBody CriarPautaRequest request) {
		return ResponseEntity.ok().body(CriarPautaResponse.converterEmDTO(criarPautaService.criar(request.getNome())));
	}

}
