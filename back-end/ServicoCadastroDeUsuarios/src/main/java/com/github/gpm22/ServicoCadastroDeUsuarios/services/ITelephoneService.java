package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface ITelephoneService extends IGenericService<TelephoneEntity>{

	public int clean(UserEntity object);

}
