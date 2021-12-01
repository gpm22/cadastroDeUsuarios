package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Optional;

public interface GenericService<T> {
	
	public T insert(T object);
	public Optional<T> getById(Long id);

}
