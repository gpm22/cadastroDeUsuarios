package com.github.gpm22.ServicoCadastroDeUsuarios.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("cadastro-de-usuarios")
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
public class Controller {

	@Autowired
	IUserService userService;

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/{cpf}", produces = "application/json")
	public ResponseEntity getUser(@PathVariable String cpf) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getById(cpf).get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/retornar-usuarios", produces = "application/json")
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/authenticate", produces = "application/json")
	public ResponseEntity authenticateUser(@RequestBody String response) {

		try {

			Optional<UserEntity> user = userService.authenticateUser(response);	

			if (user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha Incorreta!");
			}
			
			log.info("user: " + user.get());

			return ResponseEntity.status(HttpStatus.OK).body(user);

		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário Inexistente!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/create-user", consumes = "application/json")
	public ResponseEntity createUser(@RequestBody String response) {
		try {
			log.info(response);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(userService.insert(userService.parser(new JSONObject(response))));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@PutMapping(value = "/update-user/{cpf}", consumes = "application/json")
	public ResponseEntity updateUser(@RequestBody String response, @PathVariable String cpf) {

		try {

			UserEntity userUpdate = userService.parser(new JSONObject(response));

			if (userUpdate == null) {
				return ResponseEntity.status(HttpStatus.OK).body(userService.getById(cpf));
			}

			return ResponseEntity.status(HttpStatus.OK).body(userService.update(userUpdate));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário Inexistente!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/delete-user/{cpf}")
	public ResponseEntity deleteUser(@PathVariable String cpf) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.remove(userService.getById(cpf).get()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
