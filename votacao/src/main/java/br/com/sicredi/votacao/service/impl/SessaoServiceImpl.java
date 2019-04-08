package br.com.sicredi.votacao.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.votacao.dto.ResultadoSessaoDTO;
import br.com.sicredi.votacao.exception.BusinessException;
import br.com.sicredi.votacao.message.MessageKey;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.model.ValorVoto;
import br.com.sicredi.votacao.repository.PautaRepository;
import br.com.sicredi.votacao.repository.SessaoRepository;
import br.com.sicredi.votacao.repository.VotoRepository;
import br.com.sicredi.votacao.service.SessaoService;

@Service
public class SessaoServiceImpl implements SessaoService {
	
	private final SessaoRepository sessaoRepository;
	private final PautaRepository pautaRepository;
	private final VotoRepository votoRepository;

	@Autowired
	public SessaoServiceImpl(SessaoRepository sessaoRepository, 
			PautaRepository pautaRepository, 
			VotoRepository votoRepository) {
		this.sessaoRepository = sessaoRepository;
		this.pautaRepository = pautaRepository;
		this.votoRepository = votoRepository;
	}
	
	@Override
	@Transactional
	public Sessao save(Sessao sessao) {
		validate(sessao);
		return sessaoRepository.save(sessao);
	}

	private void validate(Sessao sessao) {
		if (!pautaRepository.existsById(sessao.getPauta().getId())) {
			throw new BusinessException(MessageKey.SESSAO_PAUTA_NONEXISTENT, sessao.getPauta().getId());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResultadoSessaoDTO> accountingSessoesByPauta(Pauta pauta) {
		return sessaoRepository.findByPauta(pauta)
				.stream()
				.map(this::buildResult)
				.collect(Collectors.toList());
	}
	
	private ResultadoSessaoDTO buildResult(Sessao sessao) {
		return ResultadoSessaoDTO.builder()
				.idSessao(sessao.getId())
				.votosSim(votoRepository.countBySessaoAndValorVoto(sessao, ValorVoto.SIM))
				.votosNao(votoRepository.countBySessaoAndValorVoto(sessao, ValorVoto.NAO))
				.build();
	}
	

}
