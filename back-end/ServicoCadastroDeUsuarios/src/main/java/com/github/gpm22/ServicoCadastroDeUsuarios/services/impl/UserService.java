package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private PasswordEncoder passwordEncoder = null;

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

			List<AdressEntity> adresses = adressService.getAll();
			int adressPosition = adresses.indexOf(user.getAdress());
			if (adressPosition > -1) {
				user.setAdress(adresses.get(adressPosition));
			}

			List<TelephoneEntity> telephonesAll = telephoneService.getAll();
			Set<TelephoneEntity> telephonesUser = new HashSet<>(user.getTelephones());

			int telCount = 1;

			for (TelephoneEntity telephone : telephonesAll) {
				if (telephonesUser.contains(telephone)) {
					user.getTelephones().remove(telephone);
					user.getTelephones().add(telephone);
					telCount++;
				}
				if (telCount == telephonesUser.size()) {
					break;
				}
			}

			return userRepository.insert(user);
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("PUBLIC.USERS(USER_CPF)")) {
				throw new DataIntegrityViolationException("O cpf \"" + user.getCpf() + "\" já foi utilizado");
			}

			if (e.getMessage().contains("PUBLIC.EMAILS(ADRESS_EMAIL)")) {

				Set<EmailEntity> emails = new HashSet<EmailEntity>(emailService.getAll());

				for (EmailEntity email : user.getEmails()) {
					if (emails.contains(email)) {
						throw new DataIntegrityViolationException("O email \"" + email.getEmail() + "\" já foi utilizado");
					}
				}

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
	public UserEntity remove(UserEntity object) {
		userRepository.remove(object);
		adressService.clean(object);
		telephoneService.clean(object);
		return object;
	}

	@Override
	public UserEntity update(UserEntity user) throws DataIntegrityViolationException {
		try {

			UserEntity userOriginal = userRepository.findById(user.getCpf()).get();

			if (user.equals(userOriginal)) {
				return user;
			}

			if (user.getEmails().size() < userOriginal.getEmails().size()) {

				Set<EmailEntity> emailsExcluded = new HashSet<>(userOriginal.getEmails());

				emailsExcluded.removeAll(user.getEmails());

				emailsExcluded.forEach((email) -> emailService.remove(email));

			}

			return userRepository.update(user);
		} catch (DataIntegrityViolationException e) {

			if (e.getMessage().contains("PUBLIC.EMAILS(ADRESS_EMAIL)")) {

				Set<EmailEntity> emails = new HashSet<EmailEntity>(emailService.getAll());

				for (EmailEntity email : user.getEmails()) {
					if (emails.contains(email)) {
						throw new DataIntegrityViolationException("O email \"" + email + "\" já foi utilizado");
					}
				}

			}

			throw new DataIntegrityViolationException(
					"O nome de usuário \"" + user.getUserName() + "\" já foi utilizado");
		}
	}

	public UserEntity parser(JSONObject json) {
		UserEntity user = new UserEntity();

		user.setCpf(json.getString("cpf"));
		user.setName(json.getString("name"));
		user.setUserName(json.getString("username"));
		user.setPassword(passwordEncoder.encode(json.getString("password")));
		user.setRole(json.getString("role"));

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

	@Override
	public boolean authenticateUser(String response) {
		JSONObject json = new JSONObject(response);
		return authenticateUser(json.getString("username"), json.getString("password"));
	}

	@Override
	public boolean authenticateUser(String userName, String password) {
		Optional<UserEntity> user = userRepository.findByUserName(userName);
		return passwordEncoder.matches(password, user.get().getPassword());
	}

}
