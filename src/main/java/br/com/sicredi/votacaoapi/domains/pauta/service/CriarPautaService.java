package br.com.sicredi.votacaoapi.domains.pauta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.repository.PautaRepository;

@Service
public class CriarPautaService {
	
	private PautaRepository pautaRepository;

	@Autowired
	public CriarPautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}
	
	public Pauta criar(String nome) {
		return pautaRepository.save(new Pauta(nome));
	}
	
}
