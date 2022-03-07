package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Set;

import org.json.JSONArray;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IEmailService extends IGenericService<EmailEntity>{

	Set<EmailEntity> parseAll(JSONArray jsonArray, UserEntity user);

}
