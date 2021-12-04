package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IUserService extends IGenericService<UserEntity>{

	public UserEntity authenticateUser(String response);	
}
