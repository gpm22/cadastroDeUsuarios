package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Optional;

public interface IGenericService<T> {
	
	public T insert(T object);
	public Optional<T> getById(Object id);

}
