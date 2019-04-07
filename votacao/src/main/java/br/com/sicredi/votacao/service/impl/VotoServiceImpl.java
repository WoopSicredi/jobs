package br.com.sicredi.votacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.model.Voto;
import br.com.sicredi.votacao.repository.VotoRepository;
import br.com.sicredi.votacao.service.VotoService;

@Service
public class VotoServiceImpl extends BaseServiceImpl<Voto, Long> implements VotoService {

	@Autowired
	public VotoServiceImpl(VotoRepository repository) {
		super(repository);
	}
	
	@Override
	protected VotoRepository getRepository() {
		return (VotoRepository) super.getRepository();
	}

}
