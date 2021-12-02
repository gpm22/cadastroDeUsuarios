package com.github.gpm22.ServicoCadastroDeUsuarios.repositories;

import java.util.List;
import java.util.Optional;

public interface IGenericRepository<T> {

	public T insert(T object);
	public T remove(T object);
	public T update(T object);
	public Optional<T> findById(Object id);
	public List<T> findAll();
}
