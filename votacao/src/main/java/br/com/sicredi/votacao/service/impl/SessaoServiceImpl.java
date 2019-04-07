package br.com.sicredi.votacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.repository.SessaoRepository;
import br.com.sicredi.votacao.service.SessaoService;

@Service
public class SessaoServiceImpl extends BaseServiceImpl<Sessao, Long> implements SessaoService {

	@Autowired
	public SessaoServiceImpl(SessaoRepository repository) {
		super(repository);
	}
	
	@Override
	protected SessaoRepository getRepository() {
		return (SessaoRepository) super.getRepository();
	}

}
