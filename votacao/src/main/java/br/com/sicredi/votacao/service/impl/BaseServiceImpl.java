package br.com.sicredi.votacao.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.votacao.service.BaseService;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	private final JpaRepository<T, ID> repository;
	
	public BaseServiceImpl(JpaRepository<T, ID> repository) {
		this.repository = repository;
	}
	
	protected JpaRepository<T, ID> getRepository() {
		return repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsById(ID id) {
		return repository.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public T save(T entity) {
		return repository.save(entity);
	}

}
