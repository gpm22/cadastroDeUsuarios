package com.github.gpm22.ServicoCadastroDeUsuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

@RestController
@RequestMapping("cadastro-de-usuarios")
public class Controller {
	
	@Autowired
	IUserService userService;
	
	@GetMapping(value="/{cpf}", produces = "application/json")
	public Optional<UserEntity> getUser(@PathVariable String cpf){
		return userService.getByCpf(cpf);
	}
	
	@PutMapping(value = "/user", consumes = "application/json")
	public UserEntity createUser(@RequestBody String response) {
		return null;
	}

}
