package br.com.sicredi.votacaoapi.domains.pauta.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacaoapi.domains.pauta.dto.AbrirSessaoRequest;
import br.com.sicredi.votacaoapi.domains.pauta.dto.AbrirSessaoResponse;
import br.com.sicredi.votacaoapi.domains.pauta.dto.CriarPautaRequest;
import br.com.sicredi.votacaoapi.domains.pauta.dto.CriarPautaResponse;
import br.com.sicredi.votacaoapi.domains.pauta.dto.VotoRequest;
import br.com.sicredi.votacaoapi.domains.pauta.dto.VotoResponse;
import br.com.sicredi.votacaoapi.domains.pauta.service.AbrirSessaoService;
import br.com.sicredi.votacaoapi.domains.pauta.service.CriarPautaService;
import br.com.sicredi.votacaoapi.domains.pauta.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("pauta")
@Api(tags = "TopicEndpoint")
public class TopicController {

	private AbrirSessaoService abrirSessaoService;

	private CriarPautaService criarPautaService;

	private VotoService votoService;
	
	@Autowired
	public TopicController(AbrirSessaoService abrirSessaoService, CriarPautaService criarPautaService, VotoService votoService) {
		this.abrirSessaoService = abrirSessaoService;
		this.criarPautaService = criarPautaService;
		this.votoService = votoService;
	}

	@ApiOperation(value = "Criar pauta")
	@PostMapping("/criar")
	@ResponseBody
	@Transactional
	public ResponseEntity<CriarPautaResponse> create(@Valid @RequestBody CriarPautaRequest request) {
		return ResponseEntity.ok().body(CriarPautaResponse.converterEmDTO(criarPautaService.criar(request.getNome())));
	}

	@ApiOperation(value = "Abrir sessão")
	@PutMapping("/abrirSessao")
	@ResponseBody
	@Transactional
	public ResponseEntity<AbrirSessaoResponse> openSession(@Valid @RequestBody AbrirSessaoRequest request) {
		return ResponseEntity.ok().body(AbrirSessaoResponse
				.converterEmDTO(abrirSessaoService.abrirSessao(request.getPautaId(), request.getDuracaoEmMinutos())));
	}

	@ApiOperation(value = "Votar em uma pauta")
	@PostMapping("/votar")
	@ResponseBody
	@Transactional
	public ResponseEntity<VotoResponse> vote(@Valid @RequestBody VotoRequest request) {
		return ResponseEntity.ok().body(votoService.votar(request.getPautaId(), request.getAssociado(), request.getDecisao()));
	}

}
