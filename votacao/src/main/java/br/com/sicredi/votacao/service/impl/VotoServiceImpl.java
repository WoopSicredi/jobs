package br.com.sicredi.votacao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.votacao.exception.BusinessException;
import br.com.sicredi.votacao.message.MessageKey;
import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.model.Voto;
import br.com.sicredi.votacao.repository.VotoRepository;
import br.com.sicredi.votacao.service.AssociadoService;
import br.com.sicredi.votacao.service.SessaoService;
import br.com.sicredi.votacao.service.VotoService;

@Service
public class VotoServiceImpl extends BaseServiceImpl<Voto, Long> implements VotoService {
	
	private final SessaoService sessaoService;
	private final AssociadoService associadoService;

	@Autowired
	public VotoServiceImpl(VotoRepository repository, 
			SessaoService sessaoService, 
			AssociadoService associadoService) {
		super(repository);
		this.sessaoService = sessaoService;
		this.associadoService = associadoService;
	}
	
	@Override
	protected VotoRepository getRepository() {
		return (VotoRepository) super.getRepository();
	}
	
	@Override
	@Transactional
	public Voto save(Voto voto) {
		validate(voto);
		return super.save(voto);
	}

	private void validate(Voto voto) {
		Optional<Sessao> sessao = sessaoService.findById(voto.getSessao().getId());
		
		if (!associadoService.existsById(voto.getAssociado().getId())) {
			throw new BusinessException(MessageKey.VOTO_ASSOCIADO_INEXISTENTE, voto.getAssociado().getId());
		}
		if (!sessao.isPresent()) {
			throw new BusinessException(MessageKey.VOTO_SESSAO_INEXISTENTE, voto.getSessao().getId());
		}
		if (sessao.get().isClosed()) {
			throw new BusinessException(MessageKey.VOTO_SESSAO_ENCERRADA);
		}
		if (getRepository().existsBySessaoPautaAndAssociado(sessao.get().getPauta(), voto.getAssociado())) {
			throw new BusinessException(MessageKey.VOTO_ASSOCIADO_JA_REGISTRADO);
		}
	}

}
