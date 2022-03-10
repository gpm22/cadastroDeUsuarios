package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Set;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface ITelephoneService extends IGenericService<TelephoneEntity>{

	public int removeOrhans(UserEntity object);

	public void changeTelephonesForExistingTelephones(Set<TelephoneEntity> telephones);

}
