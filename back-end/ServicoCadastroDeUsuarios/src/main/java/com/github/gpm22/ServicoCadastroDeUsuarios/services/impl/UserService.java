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

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
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
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.insert(user);
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("PUBLIC.USERS(USER_CPF)")) {
				throw new DataIntegrityViolationException("O cpf \"" + user.getCpf() + "\" já foi utilizado");
			}

			if (e.getMessage().contains("PUBLIC.EMAILS(ADRESS_EMAIL)")) {

				Set<EmailEntity> emails = new HashSet<EmailEntity>(emailService.getAll());

				for (EmailEntity email : user.getEmails()) {
					if (emails.contains(email)) {
						throw new DataIntegrityViolationException(
								"O email \"" + email.getEmail() + "\" já foi utilizado");
					}
				}

			}

			throw new DataIntegrityViolationException(
					"O nome de usuário \"" + user.getUsername() + "\" já foi utilizado");
		}
	}
	
	@Override
	public UserEntity getUserByCpf(String cpf) {
		Optional<UserEntity> user = userRepository.findById((String) cpf);

		if (user.isEmpty()) {
			throw new IllegalArgumentException("O usuário com CPF " + cpf + " não existe!");
		}
		
		return user.get();
	}

	@Override
	public Optional<UserEntity> getById(Object cpf) {
		return userRepository.findById((String) cpf);
	}

	@Override
	public UserEntity remove(UserEntity object) {
		object.getAdress().getUsers().remove(object);
		object.getTelephones().forEach((telephone) -> telephone.getUsers().remove(object));
		userRepository.remove(object);
		adressService.clean(object);
		telephoneService.clean(object);
		return object;
	}

	@Override
	public UserEntity update(UserEntity user) throws DataIntegrityViolationException {
		try {

			UserEntity userOriginal = userRepository.findById(user.getCpf()).get();

			if (user.equals(userOriginal) && user.getTelephones().equals(userOriginal.getTelephones())
					&& user.getEmails().equals(userOriginal.getEmails())
					&& user.getAdress().equals(userOriginal.getAdress())) {
				return user;
			}

			if (!user.getPassword().equals(userOriginal.getPassword())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			if (user.getEmails().size() < userOriginal.getEmails().size()) {

				Set<EmailEntity> emailsExcluded = new HashSet<>(userOriginal.getEmails());

				emailsExcluded.removeAll(user.getEmails());

				emailsExcluded.forEach((email) -> {
					emailService.remove(email);
				});
				userOriginal.setEmails(user.getEmails());
			}

			return userRepository.update(user);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();

			if (e.getMessage().contains("PUBLIC.EMAILS(ADRESS_EMAIL)")) {

				Set<EmailEntity> emails = new HashSet<EmailEntity>(emailService.getAll());

				for (EmailEntity email : user.getEmails()) {
					if (emails.contains(email)) {
						throw new DataIntegrityViolationException("O email \"" + email + "\" já foi utilizado");
					}
				}

			}

			throw new DataIntegrityViolationException(
					"O nome de usuário \"" + user.getUsername() + "\" já foi utilizado");
		}
	}

	@Override
	public UserEntity getAuthenticatedUser(JSONObject json) {
		UserEntity user = getUserByUserName(json.getString("username"));
		authenticateUser(user.getPassword(), json.getString("password"));
		return user;
	}

	private void authenticateUser(String actualPassword, String receivedPassword) {
		if (!passwordEncoder.matches(receivedPassword, actualPassword)) {
			throw new IllegalArgumentException("Senha incorreta!");
		}
	}

	private UserEntity getUserByUserName(String userName) {
		Optional<UserEntity> user = userRepository.findByUserName(userName);

		if (user.isEmpty()) {
			throw new IllegalArgumentException("O usuário " + userName + " não existe!");
		}
		
		return user.get();
	}

}
