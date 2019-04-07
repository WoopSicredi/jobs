package br.com.sicredi.votacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.repository.AssociadoRepository;
import br.com.sicredi.votacao.service.AssociadoService;

@Service
public class AssociadoServiceImpl extends BaseServiceImpl<Associado, Long> implements AssociadoService {

	@Autowired
	public AssociadoServiceImpl(AssociadoRepository repository) {
		super(repository);
	}
	
	@Override
	protected AssociadoRepository getRepository() {
		return (AssociadoRepository) super.getRepository();
	}

}
