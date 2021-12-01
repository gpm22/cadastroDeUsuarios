package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.List;
import java.util.Optional;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IUserService{

	public List<UserEntity> getAll();

	public UserEntity insert(UserEntity user);
	
	public Optional<UserEntity> getByCpf(String cpf);
	
	
}
