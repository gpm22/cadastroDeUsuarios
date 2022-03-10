package com.github.gpm22.ServicoCadastroDeUsuarios.services;

import java.util.Optional;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;

public interface IAdressService extends IGenericService<AdressEntity> {

	public void removeIfOrphan(AdressEntity object);
	Optional<AdressEntity> existingAdress(AdressEntity adress); 

}
