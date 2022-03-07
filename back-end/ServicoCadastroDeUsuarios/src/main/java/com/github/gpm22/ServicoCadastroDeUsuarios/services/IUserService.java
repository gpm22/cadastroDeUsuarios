package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Optional;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IUserService extends IGenericService<UserEntity>{

	public Optional<UserEntity> authenticateUser(String response);	
}
