package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import org.json.JSONObject;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IUserService extends IGenericService<UserEntity>{

	public boolean authenticateUser(String userName, String password);

	public UserEntity parserUpdate(JSONObject jsonObject, String cpf);

	public UserEntity parserInsert(JSONObject jsonObject);
	
}
