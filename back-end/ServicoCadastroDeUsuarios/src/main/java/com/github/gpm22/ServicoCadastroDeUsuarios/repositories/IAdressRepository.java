package com.github.gpm22.ServicoCadastroDeUsuarios.repositories;

import java.util.Optional;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;

public interface IAdressRepository extends IGenericRepository<AdressEntity>{

	Optional<AdressEntity> findExistingAdress(AdressEntity adress);

}
