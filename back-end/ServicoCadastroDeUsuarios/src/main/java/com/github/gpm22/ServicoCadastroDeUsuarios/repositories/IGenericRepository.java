package com.github.gpm22.ServicoCadastroDeUsuarios.repositories;

import java.util.Optional;

public interface IGenericRepository<T> {

	public T insert(T object);
	public Optional<T> findById(Object id);
}
