package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Optional;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

public interface IAdressService extends IGenericService<AdressEntity> {

	public void removeIfOrphan(UserEntity object);
	Optional<AdressEntity> existingAdress(AdressEntity adress); 

}
