package com.github.gpm22.ServicoCadastroDeUsuarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;

public interface EmailRepository extends JpaRepository<EmailEntity, Long>{

}
