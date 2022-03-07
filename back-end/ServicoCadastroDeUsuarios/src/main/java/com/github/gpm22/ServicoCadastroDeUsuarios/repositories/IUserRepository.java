package com.github.gpm22.ServicoCadastroDeUsuarios.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

@Repository
public interface IUserRepository extends IGenericRepository<UserEntity>{

	public Optional<UserEntity> findByUserName(String userName);
	
}
