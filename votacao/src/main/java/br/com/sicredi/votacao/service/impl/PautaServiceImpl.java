package br.com.sicredi.votacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.repository.PautaRepository;
import br.com.sicredi.votacao.service.PautaService;

@Service
public class PautaServiceImpl extends BaseServiceImpl<Pauta, Long> implements PautaService {

	@Autowired
	public PautaServiceImpl(PautaRepository repository) {
		super(repository);
	}
	
	@Override
	protected PautaRepository getRepository() {
		return (PautaRepository) super.getRepository();
	}

}
