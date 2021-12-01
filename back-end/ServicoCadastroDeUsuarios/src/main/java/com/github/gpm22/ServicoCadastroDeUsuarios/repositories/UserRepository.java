package com.github.gpm22.ServicoCadastroDeUsuarios.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	@Query("select cpf from UserEntity")
	Set<String> findAllCpfs();

}
