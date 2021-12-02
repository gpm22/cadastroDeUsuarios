package com.github.gpm22.ServicoCadastroDeUsuarios.controllers;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("cadastro-de-usuarios")
@Log4j2
public class Controller {
	
	@Autowired
	IUserService userService;
	
	@GetMapping(value="/{cpf}", produces = "application/json")
	public Optional<UserEntity> getUser(@PathVariable String cpf){
		userRepositoryTest();
		return userService.getById(cpf);
	}
	
	@GetMapping(value="/{userName}/{password}", produces = "application/json")
	public boolean getUser(@PathVariable String userName, @PathVariable String password){
		return userService.authenticateUser(userName, password);
	}
	
	@PutMapping(value = "/user", consumes = "application/json")
	public UserEntity createUser(@RequestBody String response) {
		JSONObject jsonObject = new JSONObject(response);
		return null;
	}
	

}
