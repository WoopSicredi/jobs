package br.com.sicredi.votacao.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {
	
	boolean existsById(ID id);
	
	Optional<T> findById(ID id);
	
	List<T> findAll();
	
	T save(T entity);

}
