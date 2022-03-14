package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Set;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;

public interface ITelephoneService extends IGenericService<TelephoneEntity>{

	public int removeOrhans(Set<TelephoneEntity> object);

	public void changeNewlyInsertedTelephonesForExistingTelephones(Set<TelephoneEntity> telephones);

}
