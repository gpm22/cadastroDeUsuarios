package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IUserRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IAdressService;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IEmailService;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.ITelephoneService;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IAdressService adressService;

	@Autowired
	private ITelephoneService telephoneService;

	@Autowired
	private IEmailService emailService;

	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity insert(UserEntity user) throws DataIntegrityViolationException {
		try {
			return userRepository.insert(user);
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("PUBLIC.USERS(USER_CPF)")) {
				throw new DataIntegrityViolationException("O cpf \"" + user.getCpf() + "\" já foi utilizado");
			}

			throw new DataIntegrityViolationException(
					"O nome de usuário \"" + user.getUserName() + "\" já foi utilizado");
		}
	}

	@Override
	public Optional<UserEntity> getById(Object cpf) {
		return userRepository.findById((String) cpf);
	}

	@Override
	public boolean authenticateUser(String userName, String password) {
		Optional<UserEntity> user = userRepository.findByUserName(userName);
		return user.get().getPassword().equals(password);
	}

	@Override
	public UserEntity remove(UserEntity object) {
		return userRepository.remove(object);
	}

	@Override
	public UserEntity update(UserEntity user) throws DataIntegrityViolationException {
		try {
			return userRepository.update(user);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"O nome de usuário \"" + user.getUserName() + "\" já foi utilizado");
		}
	}

	public UserEntity parser(JSONObject json) {
		UserEntity user = new UserEntity();

		user.setCpf(json.getString("cpf"));
		user.setName(json.getString("name"));
		user.setUserName(json.getString("userName"));
		user.setPassword(json.getString("password"));
		user.setRole(json.getString("role"));

		return user;
	}

	@Override
	public UserEntity parserUpdate(JSONObject json, String cpf) {
		UserEntity user = parserInsert(json);
		UserEntity userOriginal = userRepository.findById(cpf).get();

		if (user.equals(userOriginal)) {
			return null;
		}

		if (user.getTelephones().size() < userOriginal.getTelephones().size()) {

		}

		if (user.getEmails().size() < userOriginal.getEmails().size()) {
			
			Set<EmailEntity> emailsExcluded = new HashSet<>(userOriginal.getEmails());
			emailsExcluded.removeAll(user.getEmails());
			
			emailsExcluded.forEach((email) -> emailService.remove(email));		

		}

		return user;
	}

	@Override
	public UserEntity parserInsert(JSONObject json) {

		UserEntity user = parser(json);

		AdressEntity adress = adressService.parser(json.getJSONObject("adress"));
		user.setAdress(adress);
		adress.getUsers().add(user);

		json.getJSONArray("telephones").forEach((telephone) -> {

			TelephoneEntity telephoneNew = telephoneService.parser((JSONObject) telephone);
			telephoneNew.getUsers().add(user);
			user.getTelephones().add(telephoneNew);
		});

		json.getJSONArray("emails").forEach((email) -> {
			EmailEntity emailNew = emailService.parser((JSONObject) email);
			emailNew.setUser(user);
			user.getEmails().add(emailNew);
		});

		return user;
	}

}
