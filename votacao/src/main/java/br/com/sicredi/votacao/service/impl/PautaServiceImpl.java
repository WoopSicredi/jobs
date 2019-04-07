package br.com.sicredi.votacao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.votacao.dto.ResultadoPautaDTO;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.repository.PautaRepository;
import br.com.sicredi.votacao.service.PautaService;
import br.com.sicredi.votacao.service.SessaoService;

@Service
public class PautaServiceImpl implements PautaService {
	
	private final PautaRepository pautaRepository;
	private final SessaoService sessaoService;

	@Autowired
	public PautaServiceImpl(PautaRepository pautaRepository, 
			SessaoService sessaoService) {
		this.pautaRepository = pautaRepository;
		this.sessaoService = sessaoService;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pauta> findAll() {
		return pautaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pauta> findById(Long id) {
		return pautaRepository.findById(id);
	}

	@Override
	@Transactional
	public Pauta save(Pauta pauta) {
		return pautaRepository.save(pauta);
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoPautaDTO accounting(Pauta pauta) {
		return ResultadoPautaDTO.builder()
				.nomePauta(pauta.getNome())
				.pergunta(pauta.getPergunta())
				.resultadosSessoes(sessaoService.accountingSessoesByPauta(pauta))
				.build();
	}


}
