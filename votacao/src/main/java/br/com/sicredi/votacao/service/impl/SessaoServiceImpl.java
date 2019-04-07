package br.com.sicredi.votacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.exception.BusinessException;
import br.com.sicredi.votacao.message.MessageKey;
import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.repository.SessaoRepository;
import br.com.sicredi.votacao.service.PautaService;
import br.com.sicredi.votacao.service.SessaoService;

@Service
public class SessaoServiceImpl extends BaseServiceImpl<Sessao, Long> implements SessaoService {
	
	private final PautaService pautaService;

	@Autowired
	public SessaoServiceImpl(SessaoRepository repository, 
			PautaService pautaService) {
		super(repository);
		this.pautaService = pautaService;
	}
	
	@Override
	protected SessaoRepository getRepository() {
		return (SessaoRepository) super.getRepository();
	}
	
	@Override
	public Sessao save(Sessao sessao) {
		validate(sessao);
		return super.save(sessao);
	}

	private void validate(Sessao sessao) {
		if (!pautaService.existsById(sessao.getPauta().getId())) {
			throw new BusinessException(MessageKey.SESSAO_PAUTA_INEXISTENTE, 
					sessao.getPauta().getId());
		}
	}

}
