package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T> {
	
	public T insert(T object);
	public T remove(T object);
	public T update(T object);
	public Optional<T> getById(Object id);
	public List<T> getAll();

}
