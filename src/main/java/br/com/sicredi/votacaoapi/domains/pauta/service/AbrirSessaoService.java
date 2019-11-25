package br.com.sicredi.votacaoapi.domains.pauta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.repository.PautaRepository;

@Service
public class AbrirSessaoService {

	private PautaRepository pautaRepository;

	private RecuperarPautaService recuperarPautaService;

	@Autowired
	public AbrirSessaoService(RecuperarPautaService recuperarPautaService, PautaRepository pautaRepository) {
		this.recuperarPautaService = recuperarPautaService;
		this.pautaRepository = pautaRepository;
	}

	public Pauta abrirSessao(Long pautaId, Long duracaoEmMinutos) {
		Pauta pauta = recuperarPautaService.retornarPautaPorId(pautaId);
		pauta.abrirSessao(duracaoEmMinutos);
		return pautaRepository.save(pauta);
	}

}
