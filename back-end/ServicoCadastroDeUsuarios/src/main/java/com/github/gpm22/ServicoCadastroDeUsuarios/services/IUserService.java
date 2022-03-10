package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import org.json.JSONObject;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IUserService extends IGenericService<UserEntity>{

	UserEntity getAuthenticatedUser(JSONObject json);	
}
