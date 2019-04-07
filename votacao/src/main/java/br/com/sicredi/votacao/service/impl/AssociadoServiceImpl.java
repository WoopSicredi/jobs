package br.com.sicredi.votacao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.repository.AssociadoRepository;
import br.com.sicredi.votacao.service.AssociadoService;

@Service
public class AssociadoServiceImpl implements AssociadoService {

	private final AssociadoRepository associadoRepository;
	
	@Autowired
	public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
		this.associadoRepository = associadoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Associado> findAll() {
		return associadoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Associado> findById(Long id) {
		return associadoRepository.findById(id);
	}

	@Override
	@Transactional
	public Associado save(Associado associado) {
		return associadoRepository.save(associado);
	}

}
