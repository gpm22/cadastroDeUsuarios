package com.github.gpm22.ServicoCadastroDeUsuarios.controllers;

import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IParser;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
public class Controller {

	@Autowired
	IUserService userService;

	@Autowired
	IParser parser;

	@GetMapping(produces = "application/json")
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> createUser(@RequestBody String response) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(userService.insert(parser.parseJsonToUser(new JSONObject(response))));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(value = "/authenticate", produces = "application/json")
	public ResponseEntity<?> authenticateUser(@RequestBody String response) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAuthenticatedUser(new JSONObject(response)));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{cpf}", produces = "application/json")
	public ResponseEntity<?> getUser(@PathVariable String cpf) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getById(cpf).get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping(value = "/{cpf}", consumes = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody String response, @PathVariable String cpf) {

		try {

			UserEntity userUpdate = parser.parseJsonToUser(new JSONObject(response));

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

	@DeleteMapping(value = "/{cpf}")
	public ResponseEntity<?> deleteUser(@PathVariable String cpf) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.remove(userService.getById(cpf).get()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
