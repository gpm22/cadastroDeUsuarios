package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.List;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IUserService extends IGenericService<UserEntity>{

	public List<UserEntity> getAll();

	public boolean authenticateUser(String userName, String password);
	
}
