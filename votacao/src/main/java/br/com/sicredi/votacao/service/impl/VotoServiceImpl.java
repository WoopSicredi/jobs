package br.com.sicredi.votacao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.votacao.exception.BusinessException;
import br.com.sicredi.votacao.message.MessageKey;
import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.model.Voto;
import br.com.sicredi.votacao.repository.AssociadoRepository;
import br.com.sicredi.votacao.repository.SessaoRepository;
import br.com.sicredi.votacao.repository.VotoRepository;
import br.com.sicredi.votacao.service.VotoService;

@Service
public class VotoServiceImpl implements VotoService {
	
	private final VotoRepository votoRepository;
	private final SessaoRepository sessaoRepository;
	private final AssociadoRepository associadoRepository;

	@Autowired
	public VotoServiceImpl(VotoRepository votoRepository, 
			SessaoRepository sessaoRepository, 
			AssociadoRepository associadoRepository) {
		this.votoRepository = votoRepository;
		this.sessaoRepository = sessaoRepository;
		this.associadoRepository = associadoRepository;
	}
	
	@Override
	@Transactional
	public Voto save(Voto voto) {
		validate(voto);
		return votoRepository.save(voto);
	}

	private void validate(Voto voto) {
		Optional<Sessao> sessao = sessaoRepository.findById(voto.getSessao().getId());
		
		if (!associadoRepository.existsById(voto.getAssociado().getId())) {
			throw new BusinessException(MessageKey.VOTO_ASSOCIADO_NONEXISTENT, voto.getAssociado().getId());
		}
		if (!sessao.isPresent()) {
			throw new BusinessException(MessageKey.VOTO_SESSAO_NONEXISTENT, voto.getSessao().getId());
		}
		if (sessao.get().isClosed()) {
			throw new BusinessException(MessageKey.VOTO_SESSAO_CLOSED);
		}
		if (votoRepository.existsBySessaoPautaAndAssociado(sessao.get().getPauta(), voto.getAssociado())) {
			throw new BusinessException(MessageKey.VOTO_ASSOCIADO_ALREADY_REGISTERED);
		}
	}

}
